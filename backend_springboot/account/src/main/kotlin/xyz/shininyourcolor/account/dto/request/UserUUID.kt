package xyz.shininyourcolor.account.dto.request

import com.fasterxml.jackson.annotation.JsonProperty
import org.jetbrains.annotations.NotNull

/**
 * Android -> Spring Boot
 * 앱을 실행할 때 넘어오는 정보
 */
data class UserUUID (
    /**
     * 유저의 안드로이드 id
     */
    @JsonProperty("uuid")
    @NotNull
    val uuid: String
)