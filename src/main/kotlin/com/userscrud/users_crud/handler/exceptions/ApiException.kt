package com.userscrud.users_crud.handler.exceptions

class ApiException(apiError: ApiError) : Exception(apiError.message) {
    val statusCode = apiError.httpStatusCode.statusCode
    val errorCode = apiError.errorCode
    val apiError = apiError
}
