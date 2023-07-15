package com.qianyu.android.code.spannable

import android.content.Context
import android.text.SpannableString
import com.qianyu.android.code.ConstsKT
import com.qianyu.android.code.entity.BaseTailLight

abstract class BaseSpannableString(source: CharSequence?) : SpannableString(source) {

    open var type: String = BaseTailLight.TailLightType.UNKNOWN
    open var weight: Int = ConstsKT.WEIGHT_DEFAULT
    open var isNeedShow: Boolean = true

    open fun createSpannableString(ctx: Context): SpannableString? {
        return null
    }

}
