package xyz.shininyourcolor.account.dto.response

import com.fasterxml.jackson.annotation.JsonProperty
import org.jetbrains.annotations.NotNull

/**
 * Android <- Spring Boot
 * 유저의 안드로이드 ID에 해당하는 활성화 | 비활성화 정보
 */
data class UserActivation(
    /**
     * 유저의 활성화 정보
     */
    @JsonProperty("activate")
    @NotNull
    val activate: Int
)
