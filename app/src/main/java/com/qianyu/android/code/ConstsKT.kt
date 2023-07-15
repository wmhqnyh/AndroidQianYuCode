package com.qianyu.android.code

/**
 * @author : wang.mh
 * @date : 2023/7/15 22:22
 *
 * 尾灯权重类
 */
object ConstsKT {
    //默认
    const val WEIGHT_DEFAULT = -10000

    //用户昵称
    const val WEIGHT_NICKNAME = -1000

    //性别
    const val WEIGHT_GENDER = 10

    //用户等级
    const val WEIGHT_LEVEL = 20

    //长老
    const val WEIGHT_ELDER = 30

    //用户发送的正文
    const val WEIGHT_SEND_CONTENT = 1000

    //兜底未知
    const val WEIGHT_UNKNOWN = 10000
}