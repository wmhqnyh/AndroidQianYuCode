package com.qianyu.android.code.spannable

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Rect
import android.graphics.drawable.BitmapDrawable
import android.text.Spannable
import android.text.SpannableString
import com.qianyu.android.code.ConstsKT
import com.qianyu.android.code.R
import com.qianyu.android.code.entity.BaseTailLight
import com.qianyu.android.code.util.CenterVerticalImageSpan

class GenderSpannableString : BaseSpannableString(source = "") {

    override var weight: Int = ConstsKT.WEIGHT_GENDER

    override var type: String = BaseTailLight.TailLightType.GENDER

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun createSpannableString(ctx: Context): SpannableString {
        val genderSpannableString = SpannableString(" ")
        val drawableGender = ctx.resources.getDrawable(R.drawable.ic_nv)
        drawableGender.bounds = Rect(
            0,
            0,
            ((drawableGender as BitmapDrawable).bitmap.width * 1.2).toInt(),
            ((drawableGender as BitmapDrawable).bitmap.height * 1.2).toInt()
        )
        genderSpannableString.setSpan(
            CenterVerticalImageSpan(
                drawableGender, CenterVerticalImageSpan.ALIGN_FONTCENTER, 10
            ), 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        return genderSpannableString
    }
}