package thisaislan.qrgame.base.view.button

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatButton

class SoundButton @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : AppCompatButton(context, attrs, defStyle), IButtonSound {

    override fun performClick(): Boolean {
        performButtonSound(context)
        return super.performClick()
    }

}