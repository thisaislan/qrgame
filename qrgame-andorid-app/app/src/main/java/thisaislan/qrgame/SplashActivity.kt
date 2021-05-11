package thisaislan.qrgame

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import thisaislan.qrgame.base.BaseActivity
import thisaislan.qrgame.base.extesions.getIntegerAsLong
import thisaislan.qrgame.base.extesions.startFadeInAnimation
import thisaislan.qrgame.base.extesions.visible
import thisaislan.qrgame.base.singletons.AudioPlayer
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
                    }, resources.getIntegerAsLong(R.integer.animation_medium_duration))
                })
        }
    }

}