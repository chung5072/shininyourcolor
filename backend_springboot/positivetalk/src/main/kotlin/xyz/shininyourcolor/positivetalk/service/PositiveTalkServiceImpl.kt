package xyz.shininyourcolor.positivetalk.service

import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono
import xyz.shininyourcolor.positivetalk.db.entity.PositiveTalkPageContents
import xyz.shininyourcolor.positivetalk.db.repository.PTPCRepository
import xyz.shininyourcolor.positivetalk.dto.request.TalkContent
import xyz.shininyourcolor.positivetalk.dto.request.UncategorizedTalk
import xyz.shininyourcolor.positivetalk.dto.response.TalkCategory
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


@Service("positiveTalkService")
class PositiveTalkServiceImpl(
    val webClient: WebClient,
    val ptpcRepository: PTPCRepository
) : PositiveTalkService {
    /**
     * 유저가 입력한 문장을 분류
     *
     * @param content 유저가 입력한 문장
     *
     * @return String 입력한 문장의 분류 결과
     */
    override fun categorizeContent(uncategorizedTalk: UncategorizedTalk): String {
        val categorizationResult = categorizeByModel(uncategorizedTalk.content).block()

        // 테스트 용 분류 결과
//        val category = "어디에도 속하지 않는 문장"

        saveCategorizedTalk(
            uncategorizedTalk = uncategorizedTalk,
            category = categorizationResult!!.category
        )

        return categorizationResult.category
    }

    /**
     * 유저가 입력한 문장을 분류
     *
     * @param content 유저가 입력한 문장
     *
     * @exception RuntimeException
     * FastAPI에서 응답을 받을 수 없을 경우
     *
     * @return String 입력한 문장의 분류 결과
     */
    private fun categorizeByModel(content: String): Mono<TalkCategory> {

        val talkContent = TalkContent(content)

        return webClient.post()
            .uri("/categorize")
            .bodyValue(talkContent)
            .exchangeToMono { response ->
                if (response.statusCode().is2xxSuccessful) {
                    response.bodyToMono(TalkCategory::class.java)
                } else {
                    Mono.error(
                        RuntimeException("Request failed with status: ${response.statusCode()}")
                    )
                }
            }
    }

    /**
     * 분류된 문장을 DB에 저장
     *
     * @param uncategorizedTalk 유저의 정보 및 입력한 문장
     * @param category 유저가 입력한 문장의 분류 결과
     */
    private fun saveCategorizedTalk(
        uncategorizedTalk: UncategorizedTalk, category: String
    ) {
        val positiveTalkPageContents = PositiveTalkPageContents(
            uuid = uncategorizedTalk.uuid,
            typeDate = uncategorizedTalk.time,
            typeContent = uncategorizedTalk.content
        )
        positiveTalkPageContents.classificationId = when (category) {
            "none" -> 1
            "compare" -> 2
            "offensive" -> 3
            "specific_praise" -> 4
            "specific_cheer" -> 5
            "unspecific_praise" -> 6
            else -> 7 // 불특정 인물에 대한 응원
        }
        ptpcRepository.save(positiveTalkPageContents)
    }
}