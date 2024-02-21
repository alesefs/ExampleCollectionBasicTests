package com.example.mylibrary.commons

open class Constants {
    companion object {
        const val EXTRA_ITEM_RETRIVE = "EXTRA_ITEM_RETRIVE"
        const val EXTRA_DEEPLINK = "EXTRA_DEEPLINK"
        const val EXTRA_DATA = "EXTRA_DATA"
        const val EXTRA_DATA_RETRIVE = "EXTRA_DATA_RETRIVE"
        const val EXTRA_NAME_RETRIVE = "EXTRA_NAME_RETRIVE"
        const val EXTRA_AGE_RETRIVE = "EXTRA_AGE_RETRIVE"
        const val BUNDLE_NAME = "BUNDLE_NAME"
        const val BUNDLE_AGE = "BUNDLE_AGE"
        const val IMAGE_TYPE = "image/jpeg"
        const val PHONE_MASK = "(##) #####-####"
        const val CPF_MASK = "###.###.###-##"
        const val DATE_FORMAT_MASK = "dd/MM/yyyy"

        const val SEG_KEY_ABC = "abc"
        const val SEG_KEY_XYZ = "xyz"
        const val SEG_KEY_KWY = "kwy"

        var STATE_PHONE_VALID = "empty"

        const val DATA_ITEMS_RESPONSE = "data_items_response.json"
    }

    class Path {
        companion object {
            const val GET_POSTS_HEADER = "posts"
            const val API1 = "https://jsonplaceholder.typicode.com"
        }
    }
}