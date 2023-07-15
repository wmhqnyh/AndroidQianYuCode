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

class ElderSpannableString(var source: CharSequence?) : BaseSpannableString(source) {

    override var weight: Int = ConstsKT.WEIGHT_ELDER

    override var type: String = BaseTailLight.TailLightType.ELDER

    override fun createSpannableString(ctx: Context): SpannableString? {
        //长老
        val elderSpannableString = SpannableString(" ")
        val elderView = View.inflate(ctx, R.layout.item_layout_zhanglao, null)
        val tvElderName = elderView.findViewById<TextView>(R.id.tv_elder_name)
        tvElderName.text = source
        elderSpannableString.setSpan(
            ViewSpan(elderView), 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        return elderSpannableString
    }
}