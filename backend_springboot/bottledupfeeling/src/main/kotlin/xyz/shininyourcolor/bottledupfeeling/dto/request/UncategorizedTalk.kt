package xyz.shininyourcolor.bottledupfeeling.dto.request

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonProperty
import org.jetbrains.annotations.NotNull
import java.time.LocalDateTime

/**
 * Android -> Spring Boot
 */
data class UncategorizedTalk(
    /**
     * 유저의 안드로이드 id
     */
    @JsonProperty("uuid")
    @NotNull
    val uuid: String,
    /**
     * 유저가 내용을 올린 날짜
     */
    @JsonProperty("time")
    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    val time: LocalDateTime,
    /**
     * 유저가 입력한 내용
     */
    @JsonProperty("content")
    @NotNull
    val content: String
)