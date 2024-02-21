package com.example.mylibrary.details.utils

import com.example.mylibrary.commons.BasicAppException
import com.example.mylibrary.commons.BasicAppExceptionCode
import com.example.mylibrary.models.DataItemsResponseModel

object MockDataItemsResponseModel {
    fun mockDataModel() = DataItemsResponseModel(
        name = "alessandro emanuel",
        age = 32,
        mail = "aefs@gmail.com",
        phone = "966663333",
        favouriteColor = "orange red"
    )

    fun mockErrorDefault() = BasicAppException(
        code = BasicAppExceptionCode.UNKNOWN,
        message = "Não foi possivel carregar as informações"
    )

    fun mockErrorBusiness() = BasicAppException(
        code = BasicAppExceptionCode.BUSINESS,
        message = "erro do backend"
    )
}