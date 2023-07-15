package com.qianyu.android.code.spannable

import android.content.Context
import android.text.SpannableString
import com.qianyu.android.code.ConstsKT
import com.qianyu.android.code.R
import com.qianyu.android.code.entity.BaseTailLight

class TestInVisibleSpannableString : BaseSpannableString(source = "") {

    override var weight: Int = ConstsKT.WEIGHT_UNKNOWN

    override var type: String = BaseTailLight.TailLightType.UNKNOWN

    override var isNeedShow: Boolean = false

    override fun createSpannableString(ctx: Context): SpannableString? {
        return SpannableString(ctx.getString(R.string.invisible_test))
    }
}