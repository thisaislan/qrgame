package thisaislan.qrgame.view.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import thisaislan.qrgame.R
import thisaislan.qrgame.base.view.activity.BaseActivity
import thisaislan.qrgame.base.extesion.getIntegerAsLong
import thisaislan.qrgame.base.extesion.startFadeInAnimation
import thisaislan.qrgame.base.extesion.visible
import thisaislan.qrgame.base.util.AudioPlayer
import thisaislan.qrgame.databinding.ActivitySplashBinding
import thisaislan.qrgame.start.view.activity.StartActivity

class SplashActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        with(ActivitySplashBinding.inflate(layoutInflater)) {
            setContentView(root)

            logo.startFadeInAnimation(
                onAnimationStart = {
                    AudioPlayer.playAudio(this@SplashActivity, R.raw.dash)
                },
                onAnimationEnd = {
                    title.visible()
                    AudioPlayer.playAudio(this@SplashActivity, R.raw.ding)

                    Handler().postDelayed({
                        startActivity(Intent(this@SplashActivity, StartActivity::class.java))
                    }, resources.getIntegerAsLong(R.integer.animation_medium_duration))
                })
        }
    }

}