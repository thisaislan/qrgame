package thisaislan.qrgame.start.view.fragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import thisaislan.qrgame.base.extesions.clear
import thisaislan.qrgame.base.extesions.isCameraPermissionGranted
import thisaislan.qrgame.base.extesions.sharedPreferences
import thisaislan.qrgame.start.R
import thisaislan.qrgame.start.databinding.FragmentNeedPermissionBinding

class NeedPermissionFragment : Fragment(R.layout.fragment_need_permission) {

    companion object {
        const val REQUEST_PERMISSION_SETTING = 15348
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        FragmentNeedPermissionBinding.bind(view).initializeViews()
    }

    override fun onResume() {
        super.onResume()

        with(requireContext()) {
            if (isCameraPermissionGranted()) {
                sharedPreferences.clear()

                Navigation.findNavController(requireView())
                    .navigate(R.id.action_needPermissionFragment_to_welcomeFragment)
            }
        }
    }

    private fun FragmentNeedPermissionBinding.initializeViews() =
        confirmationButton.setOnClickListener { callSettingsScreen() }

    private fun callSettingsScreen() {
        val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)

        val uri: Uri =
            Uri.fromParts(
                "package",
                requireContext().applicationContext.packageName,
                null
            )

        intent.data = uri
        startActivityForResult(intent, REQUEST_PERMISSION_SETTING)
    }

}