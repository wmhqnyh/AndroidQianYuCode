package com.qianyu.android.code

import android.R.color
import android.graphics.Color
import android.graphics.Rect
import android.graphics.Typeface
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.BackgroundColorSpan
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.qianyu.android.code.databinding.ActivityMainBinding
import com.qianyu.android.code.entity.BaseTailLight
import com.qianyu.android.code.entity.GenderTailLight
import com.qianyu.android.code.util.CenterVerticalImageSpan
import com.qianyu.android.code.util.RoundBackGroundSpan
import com.qianyu.android.code.util.ViewSpan


/**
 * @author : wang.mh
 *
 * @desc 对于该功能的基本实现，通过原生的TextView封装SpannableStringBuilder
 */
class MainActivityBackUp : AppCompatActivity() {

    private var tailLightList: List<BaseTailLight> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //使用ActivityMainBinding 获取元素
        ActivityMainBinding.inflate(layoutInflater)

        val tvContent = findViewById<TextView>(R.id.tv_content)

        //val genderTailLight = GenderTailLight().createImageSpan(this@MainActivity)

        //用户昵称
        val nickNameString = getString(R.string.qianyu_username_test)
        val nickNameSpannableString = SpannableString(nickNameString)
        nickNameSpannableString.setSpan(
            ForegroundColorSpan(Color.parseColor("#A4A9B3")),
            0,
            nickNameString.length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        //性别
        val genderSpannableString = SpannableString(" ")
        val drawableGender = resources.getDrawable(R.drawable.ic_nv)
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

        //等级
        val levelSpannableString = SpannableString(" ")
        val levelView = View.inflate(this@MainActivityBackUp, R.layout.item_layout_level, null)
        val tvLevelCount = levelView.findViewById<TextView>(R.id.tv_level_count)
        tvLevelCount.text = getString(R.string.level_value_test)
        levelSpannableString.setSpan(
            ViewSpan(levelView), 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        //长老
        val elderSpannableString = SpannableString(" ")
        val elderView = View.inflate(this@MainActivityBackUp, R.layout.item_layout_zhanglao, null)
        val tvElderName = elderView.findViewById<TextView>(R.id.tv_elder_name)
        tvElderName.text = resources.getString(R.string.elder_test)
        elderSpannableString.setSpan(
            ViewSpan(elderView), 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        //用户发送的内容
        val sendContentString =
            getString(R.string.label_test) + getString(R.string.qianyu_user_content_test)
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

        val resultSsb = SpannableStringBuilder()
        resultSsb.apply {
            append(nickNameSpannableString)
            append(genderSpannableString)
            append(levelSpannableString)
            append(elderSpannableString)
            append(sendContentSpannableString)
        }
        tvContent.text = resultSsb
    }


    companion object {
        const val LABEL = ":"
    }

}