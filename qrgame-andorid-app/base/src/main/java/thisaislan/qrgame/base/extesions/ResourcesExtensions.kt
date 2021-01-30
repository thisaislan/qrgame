package thisaislan.qrgame.base.extesions

import android.content.res.Resources
import androidx.annotation.IntegerRes

fun Resources.getIntegerAsLong(@IntegerRes id: Int) =
    getInteger(id).toLong()
