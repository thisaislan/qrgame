package thisaislan.qrgame.base.extesions

import android.Manifest
import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat

val Context.sharedPreferences: SharedPreferences
    get() = getSharedPreferences(
        "${this.packageName}_${this.javaClass.simpleName}",
        MODE_PRIVATE
    )

fun Context.isCameraPermissionGranted(): Boolean =
    ContextCompat.checkSelfPermission(
        this,
        Manifest.permission.CAMERA
    ) == PackageManager.PERMISSION_GRANTED