package thisaislan.qrgame

import android.content.ContextWrapper
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.webkit.JavascriptInterface
import android.webkit.WebSettings
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import thisaislan.qrgame.databinding.ActivityGameBinding
import java.io.File
import java.io.FileOutputStream
import java.io.IOException


class GameActivity : AppCompatActivity() {

    private val biding: ActivityGameBinding by lazy(LazyThreadSafetyMode.NONE) {
        ActivityGameBinding.inflate(layoutInflater)
    }

    val tagList = arrayListOf(
        "_ap",
        "_bp",
        "_ar",
        "_br",
        "_bd",
        "_bk",
        "_bu",
        "_pd",
        "_pk",
        "_pu",
        "_ad",
        "_ak",
        "_au",
        "_ud",
        "_uk",
        "_uu",
        "_rd",
        "_rk",
        "_rd",
        "_rk",
        "_ru",
        "_dd",
        "_dk",
        "_du",
        "_ld",
        "_lk",
        "_lu",
        "_qd",
        "_qk",
        "_qu",
        "_hd",
        "_hk",
        "_hu",
        "_sg",
        "_ac",
        "_bc"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(biding.root)

        hideSystemUI()

        window.decorView.setOnSystemUiVisibilityChangeListener { visibility ->

            if (visibility and View.SYSTEM_UI_FLAG_FULLSCREEN == 0) {
                Handler().postDelayed(Runnable {
                    hideSystemUI()
                }, 3000) //time in milisecond
            }
        }

        // Optimistic code
        intent.extras?.getString(QR_DATA)?.let { assemblyGame(it) }

        with(biding) {
            val webSetting: WebSettings = webView.settings
            webSetting.javaScriptEnabled = true
            webView.isHapticFeedbackEnabled = false
            webView.webViewClient = WebViewClient()
            webView.isLongClickable = false
            webView.addJavascriptInterface(
                JavaScriptInterface(
                    //                   "/storage/emulated/0/Download/src.js"
                    "/data/user/0/thisaislan.qrgame/app_mydir/myfile.js"
                ), "AndroidInterface"
            )
            webView.loadUrl("file:///android_asset/qrgame.html")
        }
    }

    private fun hideSystemUI() {
        // Set the IMMERSIVE flag.
        // Set the content to appear under the system bars so that the content
        // doesn't resize when the system bars hide and show.
        window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // hide nav bar
                or View.SYSTEM_UI_FLAG_FULLSCREEN // hide status bar
                or View.SYSTEM_UI_FLAG_IMMERSIVE)
    }

    private fun assemblyGame(str: String) {
        val cw = ContextWrapper(this)
        val mydir: File = cw.getDir("mydir", MODE_PRIVATE) //Creating an internal dir;

        val file = File(mydir, "myfile.js") //Getting a file within the dir.
        file.delete();
        if (file.exists()) {
            file.canonicalFile.delete();
            if (file.exists()) {
                applicationContext.deleteFile(file.name);
            }
        }

        val file2 = File(mydir, "myfile.js")

        val out =
            FileOutputStream(file2, true) //Use the stream as usual to write into the file.

        val map = Regex("(?<=[A-Z][A-Z0-9]~)|(?=[A-Z][A-Z0-9]~)")
                .split(str).filter { it.isNotBlank() }
                .chunked(2)
                .associate { it.first() to it.last() }

        map.forEach {
            if (it.key != "SC~") {
                write(it.key, it.value, out)
            }
        }

        tagList.removeAll(map.keys.map { "_" + it.replace("~", "").toLowerCase() })
        writeVoidMethods(out)
        map["SC~"]?.toByteArray()?.let { write(out, it) }
        out.close()
    }

    private fun write(key: String, value: String, out: FileOutputStream) {
        write(out, "function ${"_" + key.replace("~", "").toLowerCase()}() {\r\n $value \r\n}\r\n\r\n".toByteArray())
    }

    private fun writeVoidMethods(out: FileOutputStream) {
        tagList.forEach {
            write(out, "function ${it}() {\r\n }\r\n\r\n".toByteArray())
        }
    }

    private fun write(out: FileOutputStream, byteArray: ByteArray) {
        try {
            out.write(byteArray)
        } catch (e: IOException) {
            Log.e("Exception", "File write failed: " + e.toString())
        }
    }

    private fun createDir() {

    }

    companion object {
        const val QR_DATA = "qrData"
    }
}

class JavaScriptInterface(private val path: String) {

    @JavascriptInterface
    fun getSrcPath(): String = path
}