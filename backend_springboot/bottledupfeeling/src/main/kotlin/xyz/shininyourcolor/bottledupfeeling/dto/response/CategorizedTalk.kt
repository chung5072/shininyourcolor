package xyz.shininyourcolor.bottledupfeeling.dto.response

import com.fasterxml.jackson.annotation.JsonProperty
import org.jetbrains.annotations.NotNull

/**
 * Android <- Spring Boot
 */
data class CategorizedTalk(
    /**
     * 유저의 안드로이드 id
     */
    @JsonProperty("uuid")
    @NotNull
    val uuid: String,
    /**
     * 유저의 입력한 내용의 분류
     */
    @JsonProperty("category")
    @NotNull
    val category: String,
    /**
     * 유저가 입력한 내용
     */
    @JsonProperty("content")
    @NotNull
    val content: String
)
