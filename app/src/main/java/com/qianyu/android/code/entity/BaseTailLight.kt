package com.qianyu.android.code.entity

import android.content.Context
import android.os.Parcel
import android.os.Parcelable
import android.text.style.ImageSpan
import androidx.annotation.StringDef


open class BaseTailLight() : Parcelable {

    open var type: String? = ""

    open var weight: Int = 0

    constructor(parcel: Parcel) : this() {
        type = parcel.readString()
        weight = parcel.readInt()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(type)
        parcel.writeInt(weight)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<BaseTailLight> {
        override fun createFromParcel(parcel: Parcel): BaseTailLight {
            return BaseTailLight(parcel)
        }

        override fun newArray(size: Int): Array<BaseTailLight?> {
            return arrayOfNulls(size)
        }
    }

    open fun createImageSpan(ctx: Context): ImageSpan? {
        return null
    }

    open fun isNeedShowTailLight(): Boolean {
        return true
    }

    @StringDef(
        TailLightType.GENDER,
        TailLightType.LEVEL,
        TailLightType.ELDER,
        TailLightType.NICKNAME,
        TailLightType.SEND_CONTENT,
        TailLightType.UNKNOWN
    )
    @Retention(AnnotationRetention.SOURCE)
    @Target(AnnotationTarget.VALUE_PARAMETER)
    annotation class TailLightType {
        companion object {
            const val GENDER = "Gender"
            const val LEVEL = "Level"
            const val ELDER = "Elder"
            const val NICKNAME = "Nickname"
            const val SEND_CONTENT = "SendContent"
            const val UNKNOWN = "UnKnown"
        }
    }
}