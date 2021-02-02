package thisaislan.qrgame.base.singletons

import android.content.Context
import android.media.MediaPlayer
import androidx.annotation.RawRes

object AudioPlayer {

    private const val MAX_STREAMS = 5

    private val playersInUse = mutableListOf<MediaPlayer>()

    private val mediaPlayerPool = mutableListOf<MediaPlayer>().also {
        for (i in 0..MAX_STREAMS) it += buildPlayer()
    }

    fun playAudio(context: Context, @RawRes rawResId: Int) {
        val assetFileDescriptor = context.applicationContext.resources.openRawResourceFd(rawResId) ?: return
        val mediaPlayer = requestPlayer() ?: return

        mediaPlayer.run {
            setDataSource(
                assetFileDescriptor.fileDescriptor,
                assetFileDescriptor.startOffset,
                assetFileDescriptor.declaredLength
            )
            prepareAsync()
        }
    }

    private fun buildPlayer() = MediaPlayer().apply {
        setOnPreparedListener { start() }
        setOnCompletionListener { recyclePlayer(it) }
    }

    private fun requestPlayer(): MediaPlayer? =
        if (mediaPlayerPool.isNotEmpty()) {
            mediaPlayerPool.removeAt(0).also {
                playersInUse += it
            }
        } else {
            null
        }

    private fun recyclePlayer(mediaPlayer: MediaPlayer) {
        mediaPlayer.reset()
        playersInUse -= mediaPlayer
        mediaPlayerPool += mediaPlayer
    }
}
