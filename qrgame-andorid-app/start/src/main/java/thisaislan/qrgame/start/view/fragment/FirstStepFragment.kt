package thisaislan.qrgame.start.view.fragment

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import thisaislan.qrgame.base.extesion.cameraPermissionDenied
import thisaislan.qrgame.base.extesion.sharedPreferences
import thisaislan.qrgame.start.R
import thisaislan.qrgame.start.databinding.FragmentFirstStepBinding

class FirstStepFragment : Fragment(R.layout.fragment_first_step) {

    companion object {
        const val CAMERA_REQUEST = 15348
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        FragmentFirstStepBinding.bind(view).initializeViews()
    }

    private fun FragmentFirstStepBinding.initializeViews() =
        confirmationButton.setOnClickListener {
            requestPermissions(arrayOf(Manifest.permission.CAMERA), CAMERA_REQUEST)
        }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == CAMERA_REQUEST) {

            if (grantResults.isNotEmpty()) {
                var destination = R.id.action_firstStepFragment_to_welcomeFragment

                if (grantResults[0] == PackageManager.PERMISSION_DENIED) {
                    destination = R.id.action_firstStepFragment_to_needPermissionFragment
                    requireContext().sharedPreferences.cameraPermissionDenied()
                }
                Navigation.findNavController(requireView()).navigate(destination)
            }
        }
    }

}