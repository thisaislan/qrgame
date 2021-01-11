package thisaislan.qrgame

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.budiyev.android.codescanner.*
import rebus.permissionutils.PermissionEnum
import rebus.permissionutils.PermissionManager
import rebus.permissionutils.PermissionUtils
import thisaislan.qrgame.databinding.ActivityStartBinding

class StartActivity : AppCompatActivity() {

    private val binding: ActivityStartBinding by lazy( LazyThreadSafetyMode.NONE) {
        ActivityStartBinding.inflate(layoutInflater)
    }

    private lateinit var codeScanner: CodeScanner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        with(binding) {
            setContentView(root)

            scannerView.setOnClickListener { codeScanner.startPreview() }
            codeScanner = CodeScanner(this@StartActivity, scannerView)
        }

        if (isPermissionsGranted()) {
            startReading()
        } else {
            askForPermission()
        }

    }

    override fun onResume() {
        super.onResume()
        codeScanner.startPreview()
    }

    override fun onPause() {
        codeScanner.releaseResources()
        super.onPause()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String?>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        PermissionManager.handleResult(this, requestCode, permissions, grantResults)
    }

    private fun askForPermission() {
        val permissionEnumArrayList = arrayListOf(
            PermissionEnum.WRITE_EXTERNAL_STORAGE,
            PermissionEnum.READ_EXTERNAL_STORAGE,
            PermissionEnum.CAMERA
        )

        PermissionManager.Builder()
            .permissions(permissionEnumArrayList)
            .callback { _, _, _, _ -> startReading() } // Optimistic code
            .ask(this)
    }

    private fun startReading() {
        codeScanner.camera = CodeScanner.CAMERA_BACK
        codeScanner.formats = CodeScanner.ALL_FORMATS

        codeScanner.autoFocusMode = AutoFocusMode.SAFE
        codeScanner.scanMode = ScanMode.SINGLE
        codeScanner.isAutoFocusEnabled = true
        codeScanner.isFlashEnabled = false

        codeScanner.decodeCallback = DecodeCallback {
            runOnUiThread {
                startActivity(Intent(this@StartActivity, GameActivity::class.java).putExtra(
                    GameActivity.QR_DATA,
                    it.text
                ))
            }
        }
        codeScanner.errorCallback = ErrorCallback {
            runOnUiThread {
                // Optimistic code
            }
        }
    }

    private fun isPermissionsGranted() =
        PermissionUtils.isGranted(
            this@StartActivity,
            PermissionEnum.WRITE_EXTERNAL_STORAGE,
            PermissionEnum.READ_EXTERNAL_STORAGE,
            PermissionEnum.CAMERA
        )
}