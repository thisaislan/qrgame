package thisaislan.qrgame.base.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import thisaislan.qrgame.base.R

open class BaseActivity : AppCompatActivity() {

    private val exitToast: Toast by lazy(LazyThreadSafetyMode.NONE) {
        Toast.makeText(this@BaseActivity, getString(R.string.try_exit), Toast.LENGTH_SHORT)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        requestFullScreen()
        super.onCreate(savedInstanceState)
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if (hasFocus) {
            requestFullScreen()
        }
    }

    override fun onBackPressed() {
        if (exitToast.view.windowToken == null)
            exitToast.show()
        else {
            finishAffinity()
            exitToast.cancel()
        }
    }

    private fun requestFullScreen() {
        window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_FULLSCREEN
                or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY)
    }

}