package thisaislan.qrgame.game.view.dialog

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.WindowManager
import androidx.appcompat.app.AppCompatDialog
import thisaislan.qrgame.base.R
import thisaislan.qrgame.game.databinding.DialogMenuBinding

class MenuDialog(
    context: Context,
    private val resumeClickListener: () -> Unit,
    private val exitClickListener: () -> Unit
) : AppCompatDialog(context, R.style.Theme_QRGame_Dialog) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DialogMenuBinding.inflate(LayoutInflater.from(context)).initializeViews()
        setCancelable(false)
    }

    override fun show() {
        window?.addFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE)
        super.show()
    }

    private fun DialogMenuBinding.initializeViews() {
        setContentView(root)

        resumeButton.setOnClickListener {
            invokeClickListener(resumeClickListener)
        }

        exitButton.setOnClickListener {
            invokeClickListener(exitClickListener)
        }
    }

    private fun invokeClickListener(clickListener: () -> Unit) {
        clickListener.invoke()
        dismiss()
    }

}