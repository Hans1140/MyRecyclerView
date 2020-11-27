package com.gustam.myrecyclerview

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Figure(
    var name: String,
    var description: String,
    var photo: String
) : Parcelable
