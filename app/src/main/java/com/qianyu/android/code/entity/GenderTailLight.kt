package com.qianyu.android.code.entity

import android.annotation.SuppressLint
import android.content.Context
import android.text.style.ImageSpan
import com.qianyu.android.code.ConstsKT
import com.qianyu.android.code.R
import com.qianyu.android.code.util.CenterVerticalImageSpan

class GenderTailLight : BaseTailLight() {

    override var type: String? = TailLightType.GENDER

    override var weight: Int = ConstsKT.WEIGHT_GENDER

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun createImageSpan(ctx: Context): ImageSpan? {
        return CenterVerticalImageSpan(
            ctx.resources.getDrawable(R.drawable.ic_nv),
            CenterVerticalImageSpan.ALIGN_FONTCENTER,
            10
        )
    }
}