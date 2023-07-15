package com.qianyu.android.code

import android.os.Bundle
import android.text.SpannableStringBuilder
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.qianyu.android.code.spannable.BaseSpannableString
import com.qianyu.android.code.spannable.ElderSpannableString
import com.qianyu.android.code.spannable.GenderSpannableString
import com.qianyu.android.code.spannable.LevelSpannableString
import com.qianyu.android.code.spannable.NickNameSpannableString
import com.qianyu.android.code.spannable.SendContentSpannableString
import com.qianyu.android.code.spannable.TestInVisibleSpannableString
import java.util.Collections


/**
 * @author : wang.mh
 * @date : 2023/7/15 22:30
 *
 * @desc 对于该功能的基本实现，通过原生的TextView封装SpannableStringBuilder
 * (暂时成昵称之后的样式为尾灯)
 *
 * 该示例目的在于实现该功能样式
 * @link d4d445ae4f34e9dd8751378994ea611.png
 *
 * 1.具体公屏聊天弹幕内容具体要结合具体app功能与服务端数据接口相匹配，具体代码设计仍需要改进；
 * 2.app实际场景中可能会出现多种类型的尾灯样式，目前考虑的是权重判断优先级，是否显示，尾灯类型；
 * (以上需要根据实际项目改动)
 *
 * 3.要处理的地方，RTL多语言的镜像问题，对于字幕的换行问题；
 *
 * 4.另外该项目目前中文测试数据在string.xml具体需要在实际区分多语言string；
 *
 * 5.最后尾灯还可以通过基类实现自定义View的方式实现，然后添加第二条中的考虑方面，额外需要处理的是部分场景下的网络图片或者动画加载，可能因为实时性弹幕，需要预加载。
 */
class MainActivity : AppCompatActivity() {

    private var spannableStringList: ArrayList<BaseSpannableString> = arrayListOf()
    private val comparator =
        Comparator { o1: BaseSpannableString, o2: BaseSpannableString -> o1.weight - o2.weight }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        /**
         * 或者该地方可以使用ActivityMainBinding 获取元素
         * ActivityMainBinding.inflate(layoutInflater)
         */
        val tvContent = findViewById<TextView>(R.id.tv_content)

        //用户昵称
        val nickNameString = getString(R.string.qianyu_username_test)
        //等级
        val levelValueString = getString(R.string.level_value_test)
        //长老
        val elderString = getString(R.string.elder_test)
        //用户发送的内容
        val sendContentString =
            getString(R.string.label_test) + getString(R.string.qianyu_user_content_test)

        createSpannableStringData(elderString, levelValueString, sendContentString, nickNameString)
        Collections.sort(spannableStringList, comparator)
        tvContent.text = handleSpannableResult(targetList = spannableStringList)
    }

    /**
     * 创建测试数据
     */
    private fun createSpannableStringData(
        elderString: String,
        levelValueString: String,
        sendContentString: String,
        nickNameString: String
    ) {

//        正常的顺序
//        spannableStringList.add(GenderSpannableString())
//        spannableStringList.add(LevelSpannableString(getString(R.string.level_value_test)))
//        spannableStringList.add(ElderSpannableString(getString(R.string.elder_test)))

        /**
         * 错乱的顺序
         */
        spannableStringList.add(ElderSpannableString(elderString))
        spannableStringList.add(LevelSpannableString(levelValueString))
        spannableStringList.add(GenderSpannableString())
        spannableStringList.add(SendContentSpannableString(sendContentString))
        spannableStringList.add(NickNameSpannableString(nickNameString))
        spannableStringList.add(TestInVisibleSpannableString())
    }

    private fun handleSpannableResult(targetList: ArrayList<BaseSpannableString>?): SpannableStringBuilder? {
        if (targetList.isNullOrEmpty()) return null
        val resultSsb = SpannableStringBuilder()
        resultSsb.apply {
            targetList.forEach {
                if (it.isNeedShow) {
                    this.append(it.createSpannableString(this@MainActivity))
                }
            }
        }
        return resultSsb
    }
}