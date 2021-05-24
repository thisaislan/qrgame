package thisaislan.qrgame.game.view

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.os.Bundle
import android.view.View
import android.webkit.*
import thisaislan.qrgame.base.BaseActivity
import thisaislan.qrgame.base.extesions.getHexColor
import thisaislan.qrgame.base.extesions.gone
import thisaislan.qrgame.base.extesions.startFadeOutAnimation
import thisaislan.qrgame.base.util.Constants
import thisaislan.qrgame.base.view.dialog.NeutralDialog
import thisaislan.qrgame.game.R
import thisaislan.qrgame.game.databinding.ActivityGameBinding
import thisaislan.qrgame.game.view.dialog.MenuDialog

class GameActivity : BaseActivity() {

    private val qrGameHtmlFilePath = "file:///android_asset/qrgame.html"
    private val javaScriptInterfaceName = "AndroidInterface"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ActivityGameBinding.inflate(layoutInflater).initializeViews()
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun ActivityGameBinding.initializeViews() {
        setContentView(root)

        with(webView) {
            settings.javaScriptEnabled = true
            isHapticFeedbackEnabled = false
            isLongClickable = false

            webViewClient = object : WebViewClient() {

                override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                    startGoneAnimation(backgroundView)
                    startGoneAnimation(logo)
                }

                override fun onReceivedError(
                    view: WebView?,
                    request: WebResourceRequest?,
                    error: WebResourceError?
                ) {
                    errorOnWebViewRendering()
                }
            }

            addJavascriptInterface(
                JavaScriptInterface(
                    getDir(Constants.FileNames.QR_GAME_SCRIPT_FOLDER, MODE_PRIVATE).path +
                            "/${Constants.FileNames.QR_GAME_SCRIPT}",
                    resources.getHexColor(this@GameActivity, R.attr.colorPrimary),
                    resources.getHexColor(this@GameActivity, R.attr.colorSecondary),
                    resources.getHexColor(this@GameActivity, R.attr.colorSecondaryVariant)
                ) { openMenu(this) },
                javaScriptInterfaceName
            )

            runOnUiThread {
                loadUrl(qrGameHtmlFilePath)
            }
        }
    }

    private fun openMenu(webView: WebView) {
        MenuDialog(
            this@GameActivity,
            { runOnUiThread { webView.loadUrl("javascript:closeMenu()") } },
            { runOnUiThread { backToReadAgain() } }
        ).show()
    }

    private fun startGoneAnimation(view: View) {
        view.startFadeOutAnimation(onAnimationEnd = { view.gone() })
    }

    private fun errorOnWebViewRendering() = NeutralDialog(
        context = this,
        message = R.string.error_while_webview_reading_qr_code
    ) { backToReadAgain() }.show()

    private fun backToReadAgain() = finish()

    class JavaScriptInterface(
        private val corePath: String,
        private val colorPrimary: String,
        private val colorSecondary: String,
        private val colorSecondaryVariant: String,
        private val openMenuAction: () -> Unit
    ) {
        @JavascriptInterface
        fun getCorePath(): String = corePath

        @JavascriptInterface
        fun getColorPrimary(): String = colorPrimary

        @JavascriptInterface
        fun getColorSecondary(): String = colorSecondary

        @JavascriptInterface
        fun getColorSecondaryVariant(): String = colorSecondaryVariant

        @JavascriptInterface
        fun openMenu() = openMenuAction()
    }

}