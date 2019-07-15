package com.example.rahul.social_media.Model

import android.os.Parcel
import android.os.Parcelable

data class Order(val type:Char,val material: Int,val cost:String,val qty:String,val username:String):Parcelable{
    constructor(parcel: Parcel) : this(
            parcel.readInt().toChar(),
            parcel.readInt(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString()) {
    }

    override fun writeToParcel(p0: Parcel?, p1: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun describeContents(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    companion object CREATOR : Parcelable.Creator<Order> {
        override fun createFromParcel(parcel: Parcel): Order {
            return Order(parcel)
        }

        override fun newArray(size: Int): Array<Order?> {
            return arrayOfNulls(size)
        }
    }

}