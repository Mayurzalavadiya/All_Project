package com.example.listviewdemo.task4.pojo

import android.os.Parcel
import android.os.Parcelable

data class ProductItemData(val image: String?) : Parcelable {
    constructor(parcel: Parcel) : this(parcel.readString()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(image)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ProductItemData> {
        override fun createFromParcel(parcel: Parcel): ProductItemData {
            return ProductItemData(parcel)
        }

        override fun newArray(size: Int): Array<ProductItemData?> {
            return arrayOfNulls(size)
        }
    }
}
