package xyz.shininyourcolor.positivetalk.dto.response

import com.fasterxml.jackson.annotation.JsonProperty
import org.jetbrains.annotations.NotNull

/**
 * Spring boot <- FastAPI
 */
data class TalkCategory(
    @JsonProperty("category")
    @NotNull
    val category: String
)
