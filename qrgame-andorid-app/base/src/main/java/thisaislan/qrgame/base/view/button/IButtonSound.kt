package thisaislan.qrgame.base.view.button

import android.content.Context
import thisaislan.qrgame.base.R
import thisaislan.qrgame.base.singletons.AudioPlayer

interface IButtonSound {

    fun performButtonSound(context: Context) =
        AudioPlayer.playAudio(context, R.raw.button)

}