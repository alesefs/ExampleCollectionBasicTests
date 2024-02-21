package com.example.mylibrary.details.interactor

import com.example.mylibrary.commons.BasicAppException
import com.example.mylibrary.models.DataItemsResponseModel

object BasicAppInteractorState {
    sealed class DataItems {
        data class Success(val items: DataItemsResponseModel) : DataItems()
        data class Error(val exception: BasicAppException) : DataItems()
    }
}
