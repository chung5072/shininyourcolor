package xyz.shininyourcolor.bottledupfeeling.dto.response

import com.fasterxml.jackson.annotation.JsonProperty
import org.jetbrains.annotations.NotNull

/**
 * Spring boot <- FastAPI
 */
data class TalkCategory(
    /**
     * 유저의 입력한 내용의 분류
     */
    @JsonProperty("category")
    @NotNull
    val category: String
)
