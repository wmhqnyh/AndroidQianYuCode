package com.qianyu.android.code.spannable

import android.content.Context
import android.graphics.Color
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import com.qianyu.android.code.ConstsKT
import com.qianyu.android.code.entity.BaseTailLight

class NickNameSpannableString(var source: CharSequence?) : BaseSpannableString(source) {

    override var weight: Int = ConstsKT.WEIGHT_NICKNAME

    override var type: String = BaseTailLight.TailLightType.NICKNAME

    override fun createSpannableString(ctx: Context): SpannableString? {
        val nickNameString = source ?: ""
        val nickNameSpannableString = SpannableString(source)
        nickNameSpannableString.setSpan(
            ForegroundColorSpan(Color.parseColor("#A4A9B3")),
            0,
            nickNameString.length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        return nickNameSpannableString
    }
}