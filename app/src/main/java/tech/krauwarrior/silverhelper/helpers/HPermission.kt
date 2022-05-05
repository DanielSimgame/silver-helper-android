package tech.krauwarrior.silverhelper.helpers
import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat


class HPermission {
    // Permissions Strings
    companion object {
        const val PERMISSION_CAMERA = Manifest.permission.CAMERA
        const val PERMISSION_WRITE_EXTERNAL_STORAGE = Manifest.permission.WRITE_EXTERNAL_STORAGE
        const val PERMISSION_READ_EXTERNAL_STORAGE = Manifest.permission.READ_EXTERNAL_STORAGE
        const val PERMISSION_ACCESS_FINE_LOCATION = Manifest.permission.ACCESS_FINE_LOCATION
        const val PERMISSION_ACCESS_COARSE_LOCATION = Manifest.permission.ACCESS_COARSE_LOCATION
        const val PERMISSION_RECORD_AUDIO = Manifest.permission.RECORD_AUDIO
        const val PERMISSION_READ_PHONE_STATE = Manifest.permission.READ_PHONE_STATE
        const val PERMISSION_CALL_PHONE = Manifest.permission.CALL_PHONE
        const val PERMISSION_SEND_SMS = Manifest.permission.SEND_SMS
        const val PERMISSION_RECEIVE_SMS = Manifest.permission.RECEIVE_SMS
        const val PERMISSION_READ_SMS = Manifest.permission.READ_SMS
        const val PERMISSION_RECEIVE_MMS = Manifest.permission.RECEIVE_MMS
        const val PERMISSION_RECEIVE_WAP_PUSH = Manifest.permission.RECEIVE_WAP_PUSH
        const val PERMISSION_BODY_SENSORS = Manifest.permission.BODY_SENSORS
        const val PERMISSION_READ_CALENDAR = Manifest.permission.READ_CALENDAR
        const val PERMISSION_WRITE_CALENDAR = Manifest.permission.WRITE_CALENDAR
        const val Request_CAMERA = 1
        const val Request_WRITE_EXTERNAL_STORAGE = 2
        const val Request_READ_EXTERNAL_STORAGE = 3
        const val Request_ACCESS_FINE_LOCATION = 4
        const val Request_ACCESS_COARSE_LOCATION = 5
        const val Request_RECORD_AUDIO = 6
        const val Request_READ_PHONE_STATE = 7
        const val Request_CALL_PHONE = 8
        const val Request_SEND_SMS = 9
        const val Request_RECEIVE_SMS = 10
        const val Request_READ_SMS = 11
        const val Request_RECEIVE_MMS = 12
        const val Request_RECEIVE_WAP_PUSH = 13
        const val Request_BODY_SENSORS = 14
        const val Request_READ_CALENDAR = 15
        const val Request_WRITE_CALENDAR = 16
    }

    // permission checker
    fun checkPermission(context: Context, permission: String): Boolean {
        return ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED
    }

    // request permission
    fun requestPermission(activity: Activity, permission: String, requestCode: Int) {
        ActivityCompat.requestPermissions(activity, arrayOf(permission), requestCode)
    }

    // check if permission is granted
    fun hasPermission(context: Context, permission: String): Boolean {
        return ActivityCompat.shouldShowRequestPermissionRationale(context as Activity, permission)
    }
}