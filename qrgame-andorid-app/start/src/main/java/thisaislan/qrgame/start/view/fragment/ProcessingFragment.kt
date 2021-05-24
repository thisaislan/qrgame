package thisaislan.qrgame.start.view.fragment

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import thisaislan.qrgame.base.extesion.getIntegerAsLong
import thisaislan.qrgame.base.extesion.startFadeInAnimation
import thisaislan.qrgame.base.extesion.startFadeOutAnimation
import thisaislan.qrgame.base.view.dialog.NeutralDialog
import thisaislan.qrgame.game.view.activity.GameActivity
import thisaislan.qrgame.start.R
import thisaislan.qrgame.start.databinding.FragmentProcessingBinding
import thisaislan.qrgame.start.viewModel.QrValueProcessingViewModel

class ProcessingFragment : Fragment(R.layout.fragment_processing) {

    private val args: ProcessingFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        FragmentProcessingBinding.bind(view).initializeViews()
        ViewModelProvider(this).get(QrValueProcessingViewModel::class.java).initializeViewModel()
    }

    private fun FragmentProcessingBinding.initializeViews() {
        startFadeOutFadeInLoopAnimation(image)
        startFadeOutFadeInLoopAnimation(message)
    }

    private fun QrValueProcessingViewModel.initializeViewModel() {
        getIsSuccessful().observe(viewLifecycleOwner, Observer { isSuccessful ->
            if (isSuccessful) {
                successOnQrCodeValueProcessing()
            } else {
                errorOnQrCodeValueProcessing()
            }
        })

        processingQrGame(args.qrValue)
    }

    private fun startFadeOutFadeInLoopAnimation(view: View) {
        view.startFadeOutAnimation(
            onAnimationEnd = {
                view.startFadeInAnimation(
                    onAnimationEnd = { startFadeOutFadeInLoopAnimation(view) }
                )
            }
        )
    }

    private fun successOnQrCodeValueProcessing() {
        startActivity(Intent(requireContext(), GameActivity::class.java))

        Handler().postDelayed({
            navigateBack()
        }, resources.getIntegerAsLong(R.integer.animation_xtiny_duration))
    }

    private fun errorOnQrCodeValueProcessing() = NeutralDialog(
        context = requireContext(),
        message = R.string.while_processing_qr_code
    ) { navigateBack() }.show()

    private fun navigateBack() =
        Navigation.findNavController(requireView())
            .navigate(R.id.action_processingFragment_to_welcomeFragment)

}