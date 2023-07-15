package com.qianyu.android.code.util

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import android.text.style.ReplacementSpan
import kotlin.math.roundToInt

open class RoundBackGroundSpan(
    val backGroundColor: Int,
    val textColor: Int,
    val radius: Int = DEFAULT_ROUND_RADIUS
) : ReplacementSpan() {

    override fun getSize(
        paint: Paint,
        chq: CharSequence?,
        start: Int,
        end: Int,
        pf: Paint.FontMetricsInt?
    ): Int {
        return paint.measureText(chq, start, end).roundToInt()
    }

    override fun draw(
        canavs: Canvas,
        csq: CharSequence?,
        start: Int,
        end: Int,
        x: Float,
        top: Int,
        y: Int,
        bottom: Int,
        paint: Paint
    ) {
        csq ?: return
        val fm = paint.fontMetrics
        val textLength = paint.measureText(csq, start, end)
        val rectF = RectF(x, top.toFloat(), x + textLength, fm.bottom - fm.top)
        setBackGroundFillStyle(paint)
        paint.color = backGroundColor
        canavs.drawRoundRect(rectF, radius.toFloat(), radius.toFloat(), paint)
        paint.style = Paint.Style.FILL
        paint.color = textColor
        canavs.drawText(csq, start, end, x, y.toFloat(), paint)
    }

    open fun setBackGroundFillStyle(paint: Paint) = Unit

    companion object {
        private const val DEFAULT_ROUND_RADIUS = 8
    }

    class StrokeRoundBackGroundSpan(
        val bgColor: Int,
        val tColor: Int,
        val roundCorners: Int = DEFAULT_ROUND_RADIUS
    ) : RoundBackGroundSpan(bgColor, tColor, roundCorners) {
        override fun setBackGroundFillStyle(paint: Paint) {
            paint.style = Paint.Style.STROKE
            paint.strokeWidth = 1f
        }
    }
}