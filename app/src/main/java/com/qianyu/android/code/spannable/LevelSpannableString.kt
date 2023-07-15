package com.qianyu.android.code.spannable

import android.content.Context
import android.text.Spannable
import android.text.SpannableString
import android.view.View
import android.widget.TextView
import com.qianyu.android.code.ConstsKT
import com.qianyu.android.code.R
import com.qianyu.android.code.entity.BaseTailLight
import com.qianyu.android.code.util.ViewSpan

class LevelSpannableString(var source: CharSequence?) : BaseSpannableString(source) {

    override var weight: Int = ConstsKT.WEIGHT_LEVEL

    override var type: String = BaseTailLight.TailLightType.LEVEL

    override fun createSpannableString(ctx: Context): SpannableString? {
        val levelSpannableString = SpannableString(" ")
        val levelView = View.inflate(ctx, R.layout.item_layout_level, null)
        val tvLevelCount = levelView.findViewById<TextView>(R.id.tv_level_count)
        tvLevelCount.text = source
        levelSpannableString.setSpan(
            ViewSpan(levelView), 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        return levelSpannableString
    }
}