package com.example.mylibrary.commons

import android.text.Editable
import android.text.TextWatcher
import com.google.android.material.textfield.TextInputEditText
import java.lang.StringBuilder
import java.lang.ref.WeakReference
import kotlin.math.max

data class PhoneNumberFormat(val editTextPhone: WeakReference<TextInputEditText>) : TextWatcher {

    private var formatting = false
    private var clearFlag = false
    private var lastStartLocation = 0
    private lateinit var lastBeforeText: String
    private var weakEditText: WeakReference<TextInputEditText> = editTextPhone
    private var maxSizePhone = 11
    private var phoneNumber: String? = null

    override fun beforeTextChanged(char: CharSequence?, start: Int, count: Int, after: Int) {
        if (after == 0 && char.toString() == "(") {
            clearFlag = true
        }
        lastStartLocation = start
        lastBeforeText = char.toString()
    }

    override fun onTextChanged(char: CharSequence?, start: Int, before: Int, count: Int) {
        //TODO("Not yet implemented")
    }

    override fun afterTextChanged(text: Editable?) {
        if (!formatting) {
            formatting = true
            val curPos = lastStartLocation
            val beforeValue = lastBeforeText
            val currentValue = text.toString()
            val formatValue: String = formatPhone(text)

            if (currentValue.length > beforeValue.length) {
                val setCursorPosEnd = beforeValue.length - curPos
                val setCursorPos = (formatValue.length - setCursorPosEnd)
                weakEditText.get()?.setSelection(if (setCursorPos < 0) 0 else setCursorPos)
            } else {
                val setCursorPosEnd = currentValue.length - curPos
                var setCursorPos = (formatValue.length - setCursorPosEnd)
                if (setCursorPos > 0 && !Character.isDigit(formatValue[setCursorPos - 1])) {
                    setCursorPos--
                }
                weakEditText.get()?.setSelection(if (setCursorPos < 0) 0 else setCursorPos)
            }
            formatting = false
        }

        stylePhoneField(phoneNumber.toString())
    }

    private fun formatPhone(text: Editable?): String {
        val formattedString = StringBuilder()

        var position = 0
        if (!text.isNullOrBlank()) {
            while (position < text.length) {
                val ch: Char = text[position]
                if (!Character.isDigit(ch)) {
                    text.delete(position, position + 1)
                } else {
                    position++
                }
            }
        }

        val allDigitsString = text.toString()
        val totalDigitCount = allDigitsString.length

        cleanPhoneBase(totalDigitCount, text, allDigitsString)

        var alreadyPlacedDigitCount = 0

        if (totalDigitCount - alreadyPlacedDigitCount > 2/*DDD*/) {
            val alreadyDigited = alreadyPlacedDigitCount + 2
            formattedString.append("(" + allDigitsString.substring(alreadyPlacedDigitCount, alreadyDigited) + ") ")
            alreadyPlacedDigitCount += 2
        }

        if (totalDigitCount - alreadyPlacedDigitCount > 5) {
            maxSizePhone = 11
            formattedString.append(allDigitsString.substring(alreadyPlacedDigitCount, alreadyPlacedDigitCount + 5) + "-")
            alreadyPlacedDigitCount += 5
        }

        if (totalDigitCount > alreadyPlacedDigitCount) {
            formattedString.append(allDigitsString.substring(alreadyPlacedDigitCount))
        }

        deleteNumbersAfterMaxSize(totalDigitCount, text, formattedString)

        text?.clear()
        text?.append(formattedString.toString())
        isPhoneValid(formattedString.toString(), maxSizePhone)

        phoneNumber = formattedString.toString().replace(Regex("[^\\d]"),"")

        return formattedString.toString()
    }

    private fun deleteNumbersAfterMaxSize(
        totalDigitCount: Int,
        text: Editable?,
        formattedString: StringBuilder
    ) {
        if (totalDigitCount > maxSizePhone && text != null) {
            formattedString.delete(text.length - 1, text.length)
        }
    }

    private fun cleanPhoneBase(totalDigitCount: Int, text: Editable?, allDigitString: String = "") : String {
        if (totalDigitCount == 0) {
            text?.clear()
            text?.append(allDigitString)
            return allDigitString
        }
        return allDigitString
    }

    private fun stylePhoneField(
        phoneNumber: String
    ): String {
        Constants.STATE_PHONE_VALID = if (phoneNumber.isNullOrEmpty() || phoneNumber.length == 1) {
            Utils.PhoneValidate.EMPTY.status
        } else if (isPhoneValid(phoneNumber, maxSizePhone)) {
            Utils.PhoneValidate.VALID.status
        } else {
            Utils.PhoneValidate.INCORRECT.status
        }

        return Constants.STATE_PHONE_VALID
    }

    private fun isPhoneValid(phoneNumber: String, maxSizePhone: Int): Boolean =
        Utils.phoneFormat(phoneNumber, maxSizePhone)

}