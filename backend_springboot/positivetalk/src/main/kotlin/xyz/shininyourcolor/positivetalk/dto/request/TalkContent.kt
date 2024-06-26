package xyz.shininyourcolor.positivetalk.dto.request

import com.fasterxml.jackson.annotation.JsonProperty
import org.jetbrains.annotations.NotNull

/**
 * Spring Boot -> FastAPI
 */
data class TalkContent(
    @JsonProperty("content")
    @NotNull
    val content: String
)
