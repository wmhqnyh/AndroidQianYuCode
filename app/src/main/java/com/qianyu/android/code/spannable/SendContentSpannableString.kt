package com.qianyu.android.code.spannable

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import com.qianyu.android.code.ConstsKT
import com.qianyu.android.code.entity.BaseTailLight

class SendContentSpannableString(var source: CharSequence?) : BaseSpannableString(source) {

    override var weight: Int = ConstsKT.WEIGHT_SEND_CONTENT

    override var type: String = BaseTailLight.TailLightType.SEND_CONTENT

    override fun createSpannableString(ctx: Context): SpannableString? {
        val sendContentString = source ?: ""
        val sendContentSpannableString = SpannableString(sendContentString)
        sendContentSpannableString.setSpan(
            ForegroundColorSpan(Color.parseColor("#181E25")),
            0,
            sendContentString.length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        sendContentSpannableString.setSpan(
            StyleSpan(Typeface.BOLD),
            0,
            sendContentString.length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        return sendContentSpannableString
    }
}