package com.example.listviewdemo.task5.pojo

import android.os.Parcel
import android.os.Parcelable

data class MultiSelectData(
    val image: String?,
    val name: String?,
    val des: String?,
    var isSelected: Boolean = false,
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readByte() != 0.toByte()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(image)
        parcel.writeString(name)
        parcel.writeString(des)
        parcel.writeByte(if (isSelected) 1 else 0)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<MultiSelectData> {
        override fun createFromParcel(parcel: Parcel): MultiSelectData {
            return MultiSelectData(parcel)
        }

        override fun newArray(size: Int): Array<MultiSelectData?> {
            return arrayOfNulls(size)
        }
    }


}
