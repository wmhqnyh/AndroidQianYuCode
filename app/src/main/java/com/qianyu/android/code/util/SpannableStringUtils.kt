package com.qianyu.android.code.util

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.drawable.Drawable
import android.text.SpannableStringBuilder
import android.text.style.DynamicDrawableSpan
import android.text.style.ImageSpan

object SpannableStringUtils {

    /**
     * 替换ImageSpan为CenterVerticalImageSpan
     * 使图片能在图文混排中垂直居中显示
     */
    fun convert2CenterVerticalImageSpan(spanned: SpannableStringBuilder?) {
        if (spanned == null) {
            return
        }
        try {
            val imageSpans = spanned.getSpans(0, spanned.length, ImageSpan::class.java);
            if (imageSpans == null || imageSpans.size == 0) {
                return
            }
            for (imageSpan in imageSpans) {
                val start = spanned.getSpanStart(imageSpan)
                val end = spanned.getSpanEnd(imageSpan)
                val d = imageSpan.drawable
                val newImageSpan = CenterVerticalImageSpan(d, CenterVerticalImageSpan.ALIGN_FONTCENTER)
                spanned.removeSpan(imageSpan);
                spanned.setSpan(newImageSpan, start, end, ImageSpan.ALIGN_BASELINE);
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}

open class CenterVerticalImageSpan : ImageSpan {

    companion object {
        //自定义对齐方式--与文字中间线对齐
        const val ALIGN_FONTCENTER = 2
    }

    private var paddingHorizontal = 0

    constructor(drawable: Drawable, verticalAlignment: Int) : this(drawable, verticalAlignment, 0)

    constructor(drawable: Drawable, verticalAlignment: Int, paddingHorizontal: Int = 0) : super(drawable, verticalAlignment) {
        this.paddingHorizontal = paddingHorizontal
    }

    override fun draw(canvas: Canvas, text: CharSequence, start: Int, end: Int, x: Float, top: Int, y: Int, bottom: Int,
                      paint: Paint) {

        val drawable = drawable
        canvas.save()
        val fm = paint.fontMetricsInt
        var transY = bottom - drawable.bounds.bottom
        if (mVerticalAlignment == DynamicDrawableSpan.ALIGN_BASELINE) {
            transY -= fm.descent
        } else if (mVerticalAlignment == ALIGN_FONTCENTER) {
            transY = (y + fm.descent + (y + fm.ascent)) / 2 - drawable.bounds.bottom / 2
        }

        canvas.translate(x + paddingHorizontal, transY.toFloat())
        drawable.draw(canvas)
        canvas.restore()
    }

    override fun getSize(paint: Paint, text: CharSequence, start: Int, end: Int, fm: Paint.FontMetricsInt?): Int {
        val d = drawable
        val rect = d.bounds
        if (fm != null) {
            val fmPaint = paint.fontMetricsInt
            val fontHeight = fmPaint.bottom - fmPaint.top
            val drHeight = rect.bottom - rect.top

            val top = drHeight / 2 - fontHeight / 4
            val bottom = drHeight / 2 + fontHeight / 4

            fm.ascent = -bottom
            fm.top = -bottom
            fm.bottom = top
            fm.descent = top
        }
        return rect.right + paddingHorizontal * 2
    }
}