package xyz.shininyourcolor.account.dto.request

import com.fasterxml.jackson.annotation.JsonProperty
import org.jetbrains.annotations.NotNull

/**
 * Android -> Spring Boot
 * 처음 설치했을 때 넘어오는 정보
 */
data class NewbieInfo(
    @JsonProperty("uuid")
    @NotNull
    val uuid: String,
    @JsonProperty("fcmToken")
    @NotNull
    val fcmToken: String
)
