package xyz.shininyourcolor.positivetalk.dto.request

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonProperty
import org.jetbrains.annotations.NotNull
import org.springframework.format.annotation.DateTimeFormat
import java.time.LocalDateTime

/**
 * Android -> Spring Boot
 */
data class UncategorizedTalk(
    @JsonProperty("uuid")
    @NotNull
    val uuid: String,
    @JsonProperty("time")
    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    val time: LocalDateTime,
    @JsonProperty("content")
    @NotNull
    val content: String
)
