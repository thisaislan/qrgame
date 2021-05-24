package thisaislan.qrgame.start.view.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import thisaislan.qrgame.start.R
import thisaislan.qrgame.start.databinding.FragmentWelcomeBinding

class WelcomeFragment : Fragment(R.layout.fragment_welcome) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        FragmentWelcomeBinding.bind(view).initializeViews()
    }

    private fun FragmentWelcomeBinding.initializeViews() =
        confirmationButton.setOnClickListener {
            Navigation.findNavController(requireView())
                .navigate(R.id.action_welcomeFragment_to_readQRCodeFragment)
        }

}