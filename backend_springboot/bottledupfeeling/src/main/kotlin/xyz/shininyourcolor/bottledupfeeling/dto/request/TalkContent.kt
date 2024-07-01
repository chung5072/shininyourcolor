package xyz.shininyourcolor.bottledupfeeling.dto.request

import com.fasterxml.jackson.annotation.JsonProperty
import org.jetbrains.annotations.NotNull

/**
 * Spring Boot -> FastAPI
 */
data class TalkContent(
    /**
     * 유저의 입력한 내용
     */
    @JsonProperty("content")
    @NotNull
    val content: String
)
