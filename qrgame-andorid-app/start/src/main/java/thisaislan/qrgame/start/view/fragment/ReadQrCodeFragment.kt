package thisaislan.qrgame.start.view.fragment

import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.budiyev.android.codescanner.AutoFocusMode
import com.budiyev.android.codescanner.CodeScanner
import com.budiyev.android.codescanner.DecodeCallback
import com.budiyev.android.codescanner.ErrorCallback
import com.budiyev.android.codescanner.ScanMode
import com.google.zxing.Result
import thisaislan.qrgame.base.view.dialog.NeutralDialog
import thisaislan.qrgame.start.R
import thisaislan.qrgame.start.databinding.FragmentReadQrCodeBinding

class ReadQrCodeFragment : Fragment(R.layout.fragment_read_qr_code) {

    private lateinit var codeScanner: CodeScanner

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        FragmentReadQrCodeBinding.bind(view).initializeViews()
        scanSettings()
    }

    override fun onResume() {
        super.onResume()
        codeScanner.startPreview()
    }

    override fun onPause() {
        codeScanner.releaseResources()
        super.onPause()
    }

    private fun FragmentReadQrCodeBinding.initializeViews() {
        codeScanner = CodeScanner(requireContext(), scannerView)
        back.setOnClickListener { navigateBack() }
    }

    private fun scanSettings() =
        with(codeScanner) {
            camera = CodeScanner.CAMERA_BACK
            formats = CodeScanner.ALL_FORMATS

            autoFocusMode = AutoFocusMode.SAFE
            scanMode = ScanMode.SINGLE
            isAutoFocusEnabled = true
            isFlashEnabled = false

            decodeCallback = DecodeCallback { runOnMainThread { successfulReading(it) } }
            errorCallback = ErrorCallback { runOnMainThread { errorWhileReading() } }
        }

    private fun runOnMainThread(unitToRun: () -> Unit) =
        ContextCompat.getMainExecutor(requireContext()).execute { unitToRun.invoke() }

    private fun successfulReading(result: Result) {
        val action = ReadQrCodeFragmentDirections
            .actionReadQRCodeFragmentToProcessingFragment(result.text)

        Navigation.findNavController(requireView()).navigate(action)
    }

    private fun errorWhileReading() =
        NeutralDialog(
            context = requireContext(),
            message = R.string.error_while_reading_qr_code
        ) { navigateBack() }.show()

    private fun navigateBack() =
        Navigation.findNavController(requireView())
            .navigate(R.id.action_readQRCodeFragment_to_welcomeFragment)

}