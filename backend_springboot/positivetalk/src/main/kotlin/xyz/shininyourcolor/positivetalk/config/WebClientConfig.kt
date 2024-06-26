package xyz.shininyourcolor.positivetalk.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.env.Environment
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.client.WebClient

@Configuration
class WebClientConfig(
    val env: Environment
) {
    @Bean
    fun webClient(): WebClient {
        return WebClient.builder()
            .baseUrl(env.getProperty("fastapi-url")!!)
            .defaultHeaders { httpHeaders ->
                httpHeaders.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            }
            .build()
    }
}