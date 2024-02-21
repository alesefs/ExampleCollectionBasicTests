package com.example.mylibrary.models

import android.os.Parcelable
import androidx.annotation.RestrictTo
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@RestrictTo(RestrictTo.Scope.LIBRARY)
@Parcelize
data class DataItemsResponseModel(
    @SerializedName("name") val name: String,
    @SerializedName("age") val age: Int,
    @SerializedName("mail") val mail: String? = "",
    @SerializedName("phone") val phone: String? = "",
    @SerializedName("favourite_color") val favouriteColor: String? = "",
) : Parcelable
