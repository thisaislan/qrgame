package thisaislan.qrgame.base.view.dialog

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.WindowManager
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatDialog
import thisaislan.qrgame.base.R
import thisaislan.qrgame.base.databinding.DialogNeutralBinding

class NeutralDialog(
    context: Context,
    @StringRes private val message: Int,
    @DrawableRes private val image: Int = R.drawable.ic_baseline_error_outline,
    @StringRes private val confirmationText: Int = R.string.ok,
    private val confirmationClickListener: (() -> Unit)? = null
) : AppCompatDialog(context, R.style.Theme_QRGame_Dialog) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DialogNeutralBinding.inflate(LayoutInflater.from(context)).initializeViews()
        setCancelable(false)
    }

    override fun show() {
        window?.addFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE)
        super.show()
    }

    private fun DialogNeutralBinding.initializeViews() {
        setContentView(root)
        image.setImageResource(this@NeutralDialog.image)

        with(context) {
            message.text = getString(this@NeutralDialog.message)
            confirmationButton.text = getString(this@NeutralDialog.confirmationText)
        }

        confirmationButton.setOnClickListener {
            confirmationClickListener?.invoke()
            dismiss()
        }
    }

}