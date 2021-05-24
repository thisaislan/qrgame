package thisaislan.qrgame.base.extesion

import android.view.View
import android.view.animation.*
import android.view.animation.Animation.AnimationListener
import androidx.annotation.IntegerRes
import thisaislan.qrgame.base.R

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.gone() {
    this.visibility = View.GONE
}

fun View.startFadeInAnimation(
    @IntegerRes animationDurationId: Int = R.integer.animation_medium_duration,
    interpolator: BaseInterpolator = AccelerateDecelerateInterpolator(),
    onAnimationEnd: (() -> Unit)? = null,
    onAnimationRepeat: (() -> Unit)? = null,
    onAnimationStart: (() -> Unit)? = null,
) {
    startAlphaAnimation(
        0f,
        1f,
        animationDurationId,
        interpolator,
        onAnimationEnd,
        onAnimationRepeat,
        onAnimationStart
    )
}

fun View.startFadeOutAnimation(
    @IntegerRes animationDurationId: Int = R.integer.animation_medium_duration,
    interpolator: BaseInterpolator = AccelerateDecelerateInterpolator(),
    onAnimationEnd: (() -> Unit)? = null,
    onAnimationRepeat: (() -> Unit)? = null,
    onAnimationStart: (() -> Unit)? = null,
) {
    startAlphaAnimation(
        1f,
        0f,
        animationDurationId,
        interpolator,
        onAnimationEnd,
        onAnimationRepeat,
        onAnimationStart
    )
}

private fun View.startAlphaAnimation(
    fromAlpha: Float,
    toAlpha: Float,
    @IntegerRes animationDurationId: Int,
    interpolator: Interpolator,
    onAnimationEnd: (() -> Unit)? = null,
    onAnimationRepeat: (() -> Unit)? = null,
    onAnimationStart: (() -> Unit)? = null
) {
    val alphaAnimation: Animation = AlphaAnimation(fromAlpha, toAlpha)

    startAnimation(
        alphaAnimation,
        animationDurationId,
        interpolator,
        onAnimationEnd,
        onAnimationRepeat,
        onAnimationStart
    )
}

private fun View.startAnimation(
    animation: Animation,
    @IntegerRes animationDurationId: Int,
    interpolator: Interpolator,
    onAnimationEnd: (() -> Unit)? = null,
    onAnimationRepeat: (() -> Unit)? = null,
    onAnimationStart: (() -> Unit)? = null
) {
    animation.duration = resources.getIntegerAsLong(animationDurationId)
    animation.interpolator = interpolator

    animation.setAnimationListener(object : AnimationListener {
        override fun onAnimationEnd(animation: Animation) {
            onAnimationEnd?.invoke()
        }

        override fun onAnimationRepeat(animation: Animation) {
            onAnimationRepeat?.invoke()
        }

        override fun onAnimationStart(animation: Animation) {
            onAnimationStart?.invoke()
        }
    })

    this.startAnimation(animation)
}