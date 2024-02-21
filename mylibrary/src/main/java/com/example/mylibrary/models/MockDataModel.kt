package com.example.mylibrary.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MockDataModel(
    var name: String,
    var age: Int,
    val mail: String? = "",
    val phone: String? = "",
    val favouriteColor: String? = "",
    val isFirstLogin: Boolean
) : Parcelable
