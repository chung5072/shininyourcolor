package xyz.shininyourcolor.account.dto.response

/**
 * Android <- Spring Boot
 * 단순한 응답만을 위한 data class
 */
data class BaseResponseBody(
    var statusCode: Int,
    var message: String
)
