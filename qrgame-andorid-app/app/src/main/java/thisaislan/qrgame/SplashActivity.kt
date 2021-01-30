package thisaislan.qrgame

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Handler
import androidx.core.content.ContextCompat
import thisaislan.qrgame.base.BaseActivity
import thisaislan.qrgame.base.extesions.getIntegerAsLong
import thisaislan.qrgame.base.extesions.startFadeInAnimation
import thisaislan.qrgame.base.extesions.visible
import thisaislan.qrgame.base.singletons.AudioPlayer
import thisaislan.qrgame.databinding.ActivitySplashBinding

class SplashActivity : BaseActivity() {

    private val biding: ActivitySplashBinding by lazy(LazyThreadSafetyMode.NONE) {
        ActivitySplashBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        with(biding) {
            setContentView(root)

            logo.startFadeInAnimation(
                onAnimationStart = {
                    AudioPlayer.playAudio(this@SplashActivity, R.raw.dash)
                },
                onAnimationEnd = {
                    title.visible()
                    AudioPlayer.playAudio(this@SplashActivity, R.raw.ding)

                    Handler().postDelayed({
                        checkPermissions()
                    }, resources.getIntegerAsLong(R.integer.animation_medium_duration))
                })
        }
    }

    private fun checkPermissions() {
        if (ContextCompat.checkSelfPermission(this@SplashActivity, Manifest.permission.CAMERA)
            != PackageManager.PERMISSION_GRANTED
        ) {
            // Call scene to ask permissions
        } else {
            // Call scene to read
        }
    }
}