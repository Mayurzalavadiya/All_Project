package com.myapplication.activity

import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.zxing.BarcodeFormat
import com.google.zxing.WriterException
import com.google.zxing.common.BitMatrix
import com.google.zxing.integration.android.IntentIntegrator
import com.google.zxing.integration.android.IntentResult
import com.google.zxing.qrcode.QRCodeWriter
import com.journeyapps.barcodescanner.CaptureActivity
import com.myapplication.R

class QRCodeActivity : AppCompatActivity() {

    private var editText: EditText? = null
    private var generateButton: Button? = null
    private var scanButton: Button? = null
    private var qrCodeImageView: ImageView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_qrcode)

        editText = findViewById(R.id.editText)
        generateButton = findViewById(R.id.generateButton)
        scanButton = findViewById(R.id.scanButton)
        qrCodeImageView = findViewById(R.id.qrCodeImageView)

        generateButton!!.setOnClickListener {
            val data = editText!!.text.trim().toString()
            if (data.isEmpty()) {
                editText!!.error = "enter any value";

            } else {
                try {
                    val bitmap = generateQRCode(data)
                    qrCodeImageView!!.setImageBitmap(bitmap)
                } catch (e: WriterException) {
                    e.printStackTrace()
                }
            }
        }


        scanButton!!.setOnClickListener {
            startQRCodeScanning()
        }
    }

    private fun generateQRCode(data: String): Bitmap {
        val width = 400
        val height = 400

        val bitMatrix: BitMatrix = QRCodeWriter().encode(data, BarcodeFormat.QR_CODE, width, height)
        return createBitmapFromBitMatrix(bitMatrix)
    }

    private fun createBitmapFromBitMatrix(bitMatrix: BitMatrix): Bitmap {
        val width: Int = bitMatrix.width
        val height: Int = bitMatrix.height
        val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        for (x in 0 until width) {
            for (y in 0 until height) {
                bitmap.setPixel(x, y, if (bitMatrix[x, y]) BLACK else WHITE)
            }
        }
        return bitmap
    }

    private fun startQRCodeScanning() {
        val integrator = IntentIntegrator(this)
        integrator.setOrientationLocked(false) // Allow scanning in both portrait and landscape
        integrator.setCaptureActivity(CaptureActivity::class.java) // Use custom CaptureActivity for full-screen scanning
        integrator.initiateScan()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val result: IntentResult? = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
        if (result != null) {
            val scannedData: String? = result.contents
            if (!scannedData.isNullOrEmpty()) {
                Toast.makeText(this, "Scanned Data: $scannedData", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Scanning canceled", Toast.LENGTH_SHORT).show()
            }
        }
    }

    companion object {
        private const val BLACK = -0x1000000
        private const val WHITE = -0x1
    }
}