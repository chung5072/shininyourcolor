package xyz.shininyourcolor.positivetalk.dto.response

import com.fasterxml.jackson.annotation.JsonProperty
import org.jetbrains.annotations.NotNull

/**
 * Android <- Spring Boot
 */
data class CategorizedTalk(
    @JsonProperty("uuid")
    @NotNull
    val uuid: String,
    @JsonProperty("category")
    @NotNull
    val category: String,
    @JsonProperty("content")
    @NotNull
    val content: String
)
