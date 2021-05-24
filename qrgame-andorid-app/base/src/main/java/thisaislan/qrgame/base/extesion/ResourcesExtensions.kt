package thisaislan.qrgame.base.extesion

import android.content.Context
import android.content.res.Resources
import android.util.TypedValue
import androidx.annotation.AttrRes
import androidx.annotation.IntegerRes
import androidx.core.content.res.ResourcesCompat

fun Resources.getIntegerAsLong(@IntegerRes id: Int) =
    getInteger(id).toLong()

fun Resources.getHexColor(context: Context, @AttrRes attributeColorId: Int): String {
    val currentTheme = context.theme
    val typedValue = TypedValue()

    currentTheme.resolveAttribute(attributeColorId, typedValue, true)

    val colorRes = typedValue.resourceId
    val colorInt = ResourcesCompat.getColor(this, colorRes, currentTheme)

    return String.format("#%06X", 0xFFFFFF and colorInt)
}
