package xyz.shininyourcolor.account.dto.request

import com.fasterxml.jackson.annotation.JsonProperty
import org.jetbrains.annotations.NotNull

/**
 * Android -> Spring Boot
 * 직접 활성화 | 비활성화 버튼을 눌렀을 때
 */
data class Activation(
    /**
     * 유저의 안드로이드 id
     */
    @JsonProperty("uuid")
    @NotNull
    val uuid: String,
    /**
     * 유저의 활성화 정보
     */
    @JsonProperty("activate")
    @NotNull
    val activate: Boolean
)
