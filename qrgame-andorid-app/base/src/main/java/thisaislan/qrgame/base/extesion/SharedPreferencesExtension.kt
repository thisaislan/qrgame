package thisaislan.qrgame.base.extesion

import android.Manifest
import android.content.SharedPreferences

fun SharedPreferences.hasPreviousDenialCameraPermission(): Boolean =
    getInt(Manifest.permission.CAMERA, 0) != 0

fun SharedPreferences.cameraPermissionDenied() {
    edit().putInt(Manifest.permission.CAMERA, 1).apply()
}

fun SharedPreferences.clear() {
    edit().clear().apply()
}