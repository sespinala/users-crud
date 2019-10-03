package co.com.psl.lighthouse.admin.api.handlers.error.exceptions

class ApiException(apiError: ApiError) : Exception(apiError.message) {
    val statusCode = apiError.httpStatusCode.statusCode
    val errorCode = apiError.errorCode
    val apiError = apiError
}
