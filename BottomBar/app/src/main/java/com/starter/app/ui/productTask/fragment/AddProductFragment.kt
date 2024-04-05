package com.starter.app.ui.productTask.fragment

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.provider.OpenableColumns
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.widget.addTextChangedListener
import com.bumptech.glide.Glide
import com.starter.app.R
import com.starter.app.databinding.FragmentAddProductBinding
import com.starter.app.exception.ApplicationException
import com.starter.app.ui.Const
import com.starter.app.ui.base.BaseFragment
import com.starter.app.ui.productTask.pojo.MyProduct
import com.starter.app.utils.Validator
import dagger.hilt.android.AndroidEntryPoint
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import javax.inject.Inject


@AndroidEntryPoint
class AddProductFragment : BaseFragment<FragmentAddProductBinding>() {

    private var isImageSelect = false

    private var calendar = Calendar.getInstance()
    private var isStartDateSet = false

    private var productItem: MyProduct? = null
    var position: String? = null

    // Declare fromDate as a class-level variable
    private var fromDate: Long = 0

    @Inject
    lateinit var validator: Validator

    private var filePath: Uri? = null

    private var sSelectedPath: String? = null

    private val isValid: Boolean
        get() {
            return try {

                if (!isImageSelect) {
                    throw ApplicationException(requireContext().getString(R.string.validation_please_select_image))
                }

                validator.submit(binding.edittextCourseName)
                    .checkEmpty()
                    .errorMessage(requireContext().getString(R.string.validation_please_enter_course_name))
                    .check()

                validator.submit(binding.edittextStartDate)
                    .checkEmpty()
                    .errorMessage(requireContext().getString(R.string.validation_please_enter_start_date))
                    .check()

                validator.submit(binding.edittextEndDate)
                    .checkEmpty()
                    .errorMessage(requireContext().getString(R.string.validation_please_enter_end_date))
                    .check()
                validator.submit(binding.edittextDescription)
                    .checkEmpty()
                    .errorMessage(requireContext().getString(R.string.validation_please_enter_a_description))
                    .check()

                true
            } catch (e: ApplicationException) {
                showMessage(e)
                false
            }
        }

    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        attachToRoot: Boolean
    ): FragmentAddProductBinding {
        return FragmentAddProductBinding.inflate(inflater, container, attachToRoot)
    }


    override fun bindData() {

        requestStoragePermission()

        getArgument()
        setData()
        setClickListener()
    }

    private fun getArgument() {
        productItem = arguments?.getParcelable(Const.PRODUCT) as MyProduct?
        position = arguments?.getString(Const.POSITION)
    }

    private fun setData() = with(binding) {
        productItem?.let {
            buttonAddCourse.setText(R.string.update)
            isImageSelect = true
            isStartDateSet = true
            Log.e("TAG", "setData: ${it.image}")
//            imageViewImage.visibility = View.VISIBLE
//            constraintImage.visibility = View.GONE
            Glide.with(imageViewImage).load(it.image).into(imageViewImage)
//            imageViewImage.setImageResource(it.image)
            edittextCourseName.setText(it.courseName)
            edittextStartDate.setText(it.startDate)
            edittextEndDate.setText(it.endDate)
            edittextDescription.setText(it.description)
            edittextPrice.setText(it.price)

            // Set the calendar and fromDate based on existing data
            val dateFormat = SimpleDateFormat(getString(R.string.dd_mmm_yyyy), Locale.US)

            // Parse the existing start date and set the calendar and fromDate
            try {
                it.startDate?.let { date ->
                    val startDate = dateFormat.parse(date)
                    startDate?.let {
                        calendar.time = startDate
                        fromDate = startDate.time
                    }
                }
            } catch (e: ParseException) {
                // Handle parsing exception if necessary
            }
        }
    }


    @SuppressLint("ClickableViewAccessibility")
    private fun setClickListener() = with(binding) {

        edittextPrice.addTextChangedListener {
            if (edittextPrice.isFocusable) {
                edittextPrice.text?.let { text ->
                    if (text.length < 2) {
                        edittextPrice.setText(getString(R.string.dollar))
                        edittextPrice.setSelection(2)
                    }
                }
            }
        }

//        constraintImage.setOnClickListener {
//            isImageSelect = true
//            constraintImage.visibility = View.GONE
//            imageViewImage.visibility = View.VISIBLE
//        }

        imageViewImage.setOnClickListener {
            val intent = Intent()
            intent.setType("image/*")
            intent.setAction(Intent.ACTION_GET_CONTENT)
            startActivityForResult(Intent.createChooser(intent, "Select Image"), Const.IMAGE_CODE)
        }

        imageViewBack.setOnClickListener {
            requireActivity().finish()
        }


        //start Date
        edittextStartDate.setOnClickListener {
            hideKeyBoard()
            click(true)
        }

        edittextEndDate.setOnClickListener {
            hideKeyBoard()
            click(false)
        }


        buttonAddCourse.setOnClickListener {
            if (isValid) {
                val myProduct = MyProduct(
                    position,
                    sSelectedPath,
                    edittextCourseName.text.toString(),
                    edittextStartDate.text.toString(),
                    edittextEndDate.text.toString(),
                    edittextDescription.text.toString(),
                    edittextPrice.text.toString()
                )

                val intent = Intent()
                intent.putExtra(Const.DATA, myProduct)
                requireActivity().setResult(Activity.RESULT_OK, intent)
                requireActivity().finish()
            }
        }
    }

    private fun click(click: Boolean) {
        if (click) {
            showDatePickerDialog(click)
        } else {

            if (isStartDateSet) {
                showDatePickerDialog(click)
            } else {
                showMessage(getString(R.string.please_select_start_date_first))
            }
        }
    }

    private fun showDatePickerDialog(isStartDate: Boolean) = with(binding) {
        val currentDate = Calendar.getInstance()
        val maxDate = Calendar.getInstance()
        maxDate.add(Calendar.MONTH, 1)

        if (isStartDate) {
            calendar = Calendar.getInstance()
        }

        val date =
            OnDateSetListener { _, i, i1, i2 ->
                calendar.set(Calendar.YEAR, i)
                calendar.set(Calendar.MONTH, i1)
                calendar.set(Calendar.DAY_OF_MONTH, i2)
                val sDateFormat = SimpleDateFormat(getString(R.string.dd_mmm_yyyy), Locale.US)

                if (isStartDate) {

                    if (sDateFormat.format(calendar.time) != edittextStartDate.text.toString()) {
                        edittextEndDate.setText("")
                    }
                    isStartDateSet = true
                    fromDate = calendar.timeInMillis
                    edittextStartDate.setText(sDateFormat.format(calendar.time))
                    click(false)
                } else {
                    edittextEndDate.setText(sDateFormat.format(calendar.time))
                }
            }

        val datePickerDialog = DatePickerDialog(
            requireActivity(), R.style.DatePickerDialog, date, calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)
        )

        if (isStartDate) {
            // Set minimum date
            datePickerDialog.datePicker.minDate = currentDate.timeInMillis
            // Set maximum date
            datePickerDialog.datePicker.maxDate = maxDate.timeInMillis
        } else {

            // Set minimum date
            datePickerDialog.datePicker.minDate = fromDate
            // Set maximum date
            datePickerDialog.datePicker.maxDate = maxDate.timeInMillis
        }

        datePickerDialog.show()
    }


    private fun requestStoragePermission() {
        if (ContextCompat.checkSelfPermission(
                requireActivity(),
                Manifest.permission.READ_EXTERNAL_STORAGE
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            return
        }
        if (ActivityCompat.shouldShowRequestPermissionRationale(
                requireActivity(),
                Manifest.permission.READ_EXTERNAL_STORAGE
            )
        ) {
        }
        ActivityCompat.requestPermissions(
            requireActivity(),
            arrayOf<String>(Manifest.permission.READ_EXTERNAL_STORAGE),
            Const.STORAGE_CODE
        )
    }

    @Deprecated("Deprecated in Java")
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String?>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == Const.STORAGE_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                showMessage("Permission Granted")
            } else {
                showMessage("You Just Denied Permission")
            }
        }
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == Const.IMAGE_CODE && resultCode == Activity.RESULT_OK && data != null) {
//            binding.imageViewImage.visibility = View.VISIBLE
//            binding.constraintImage.visibility = View.GONE
            filePath = data.data
            isImageSelect = true
            binding.imageViewImage.setImageURI(filePath)
            sSelectedPath = getRealPathFromURI(filePath)
            Log.d("RESPONSE_URI", filePath.toString())
        }
    }

    private fun getRealPathFromURI(uri: Uri?): String? {
        val returnCursor =
            uri?.let { requireActivity().contentResolver.query(it, null, null, null, null) }
        val nameIndex = returnCursor!!.getColumnIndex(OpenableColumns.DISPLAY_NAME)
        val sizeIndex = returnCursor.getColumnIndex(OpenableColumns.SIZE)
        returnCursor.moveToFirst()
        val name = returnCursor.getString(nameIndex)
        val size = returnCursor.getLong(sizeIndex).toString()
        val file = File(requireActivity().filesDir, name)
        try {
            val inputStream: InputStream? = requireActivity().contentResolver.openInputStream(uri)
            val outputStream = FileOutputStream(file)
            var read = 0
            val maxBufferSize = 1 * 1024 * 1024
            val bytesAvailable: Int = inputStream?.available() ?: 0
            //int bufferSize = 1024;
            val bufferSize = Math.min(bytesAvailable, maxBufferSize)
            val buffers = ByteArray(bufferSize)
            while (inputStream?.read(buffers).also {
                    if (it != null) {
                        read = it
                    }
                } != -1) {
                outputStream.write(buffers, 0, read)
            }
            Log.e("File Size", "Size " + file.length())
            inputStream?.close()
            outputStream.close()
            Log.e("File Path", "Path " + file.path)

        } catch (e: java.lang.Exception) {
            Log.e("Exception", e.message!!)
        }
        return file.path
    }


}