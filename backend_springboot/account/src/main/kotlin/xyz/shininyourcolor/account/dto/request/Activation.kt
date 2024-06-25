package xyz.shininyourcolor.account.dto.request

import com.fasterxml.jackson.annotation.JsonProperty
import org.jetbrains.annotations.NotNull

/**
 * Android -> Spring Boot
 * 직접 활성화 | 비활성화 버튼을 눌렀을 때
 */
data class Activation(
    @JsonProperty("uuid")
    @NotNull
    val uuid: String,
    @JsonProperty("activate")
    @NotNull
    val activate: Boolean
)
