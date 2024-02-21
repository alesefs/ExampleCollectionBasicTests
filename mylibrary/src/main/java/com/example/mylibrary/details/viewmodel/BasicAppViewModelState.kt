package com.example.mylibrary.details.viewmodel

import com.example.mylibrary.commons.BasicAppException
import com.example.mylibrary.models.DataItemsResponseModel

object BasicAppViewModelState {
    sealed class DataItems {
        data class Success(val items: DataItemsResponseModel) : DataItems()
        data class Error(val exception: BasicAppException) : DataItems()
        object ShowShimmer : DataItems()
        object StopShimmer : DataItems()
    }
}
