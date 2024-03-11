package com.starter.app.utils

import android.Manifest
import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.provider.Settings
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.starter.app.R

class PermissionUtil {
    /* ------------ Permission required  ----------------- */
    val REQUEST_ALL_PERMISSION = 1
//    val REQUEST_FINE_LOCATION_CODE = 2
//    val REQUEST_PHONE_STATE_CODE = 3
//    val REQUEST_READ_CONTACT = 4
//    val REQUEST_CAMERA = 5
//    val REQUEST_READ_STORAGE = 6
//    val REQUEST_WRITE_STORAGE = 7
//    val ACCESS_FINE_LOCATION = Manifest.permission.ACCESS_FINE_LOCATION
//    val ACCESS_COARSE_LOCATION = Manifest.permission.ACCESS_COARSE_LOCATION
//    val ACCESS_READ_PHONE_STATE = Manifest.permission.READ_PHONE_STATE
//    val ACCESS_READ_CONTACTS = Manifest.permission.READ_CONTACTS
//    val ACCESS_CAMEARA = Manifest.permission.CAMERA
    val ACCESS_READ_STORAGE = Manifest.permission.READ_EXTERNAL_STORAGE
    val ACCESS_WRITE_STORAGE = Manifest.permission.WRITE_EXTERNAL_STORAGE
    fun checkAllPermission(activity: Activity): Boolean {
        return checkPermissionForAccess(
            activity, arrayOf(
                ACCESS_READ_STORAGE,
                ACCESS_WRITE_STORAGE
            ), REQUEST_ALL_PERMISSION
        )
    }

    fun checkPermissionForAccess(
        activity: Activity,
        requirePermission: Array<String>,
        requestCode: Int
    ): Boolean {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val permissionArray = ArrayList<String>()
            for (aRequirePermission in requirePermission) {
                if (ContextCompat.checkSelfPermission(activity, aRequirePermission)
                    != PackageManager.PERMISSION_GRANTED
                ) {
                    permissionArray.add(aRequirePermission)
                }
            }
            if (permissionArray.size > 0) {
                val requestPermission: Array<String> = permissionArray.toTypedArray<String>()
                activity.requestPermissions(requestPermission, requestCode)
            } else {
//                Toast.makeText(MainActivity.this, "Permission Granted M", Toast.LENGTH_SHORT).show();
                return true
            }
        } else {
//            Toast.makeText(MainActivity.this, "Permission Granted", Toast.LENGTH_SHORT).show();
            return true
        }
        return false
    }

    fun checkPermissionForAccessShould(
        activity: Activity,
        requirePermission: String,
        requestCode: Int,
        infoMessage: String?
    ): Boolean {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(
                    activity,
                    requirePermission
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(
                        activity,
                        requirePermission
                    )
                ) {
                    val alertBuilder = AlertDialog.Builder(activity)
                    alertBuilder.setCancelable(true)
                    alertBuilder.setTitle("Permission necessary")
                    alertBuilder.setMessage(infoMessage)
                    alertBuilder.setPositiveButton(
                        android.R.string.yes
                    ) { _, _ ->
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            activity.requestPermissions(
                                arrayOf(requirePermission),
                                requestCode
                            )
                        }
                    }
                    val alert = alertBuilder.create()
                    alert.show()
                } else {
                    activity.requestPermissions(arrayOf(requirePermission), requestCode)
                }
            } else {
//                Toast.makeText(MainActivity.this, "Permission Granted M", Toast.LENGTH_SHORT).show();
                return true
            }
        } else {
//            Toast.makeText(MainActivity.this, "Permission Granted", Toast.LENGTH_SHORT).show();
            return true
        }
        return false
    }

    fun ifNotGrantedWithDontAsk(activity: Activity, requirePermission: String?) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (!requirePermission?.let {
                    ActivityCompat.shouldShowRequestPermissionRationale(
                        activity,
                        it
                    )
                }!!) {
                showPermissionDailog(activity)
            }
        }
    }

    fun showPermissionDailog(activity: Activity) {
        val alertBuilder = AlertDialog.Builder(activity)
        alertBuilder.setCancelable(true)
        alertBuilder.setTitle("Permission necessary")
        alertBuilder.setMessage(R.string.msg_permission_step)
        alertBuilder.setPositiveButton(android.R.string.yes
        ) { _, _ ->
            val intent = Intent()
            intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
            val uri = Uri.fromParts("package", activity.packageName, null)
            intent.setData(uri)
            activity.startActivity(intent)
            /*startActivityForResult(intent, REQUEST_PERMISSION_SETTING);*/
        }
        val alert = alertBuilder.create()
        alert.show()
    }
}
