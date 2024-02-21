package com.example.mylibrary.commons

import java.util.*
import kotlin.random.Random
import kotlin.random.nextInt

data class BasicAppException(
    val code: BasicAppExceptionCode,
    override val message: String
) : Throwable()

enum class BasicAppExceptionCode {
    BUSINESS,
    UNKNOWN;

    companion object {
        private const val BUSINESS_CODE = "422"

        fun newInstance(statusCode: String) =
            when (statusCode) {
                BUSINESS_CODE -> {
                    BUSINESS
                }
                else -> {
                    UNKNOWN
                }
            }
    }
}

open class BaseException {

    fun analyseBackEndException(
        exception: ExceptionMock
    ) : BasicAppException = showErrorBackend(exception)

    private fun showErrorBackend(
        exception: ExceptionMock
    ): BasicAppException = if (exception.statusCode in 400..499) {
        val code = BasicAppExceptionCode.newInstance(exception.statusCode.toString())
        val message = exception.message

        BasicAppException(code, message)
    } else {
        analyseBackEndExceptionDefault()
    }

    private fun analyseBackEndExceptionDefault(): BasicAppException {
        val code = BasicAppExceptionCode.UNKNOWN
        val message = "Não foi possivel carregar as informações"

        return BasicAppException(code, message)
    }
}

open class ExceptionMock : Throwable() {
    val statusCode: Int = Random.nextInt(400, 510)
    override val message: String = "erro do backend"
}
