package co.com.psl.lighthouse.admin.api.handlers.error.exceptions

enum class HttpStatusCode(val statusCode: Int) {

  NOT_FOUND(404),
  FORBIDDEN(403),
  BAD_REQUEST(400),
  INTERNAL_SERVER_ERROR(500),
  UNAUTHORIZED(401),
  SERVICE_NOT_AVAILABLE(503)
}
