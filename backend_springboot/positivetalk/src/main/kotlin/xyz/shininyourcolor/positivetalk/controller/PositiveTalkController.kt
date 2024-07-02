package xyz.shininyourcolor.positivetalk.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import xyz.shininyourcolor.positivetalk.dto.request.UncategorizedTalk
import xyz.shininyourcolor.positivetalk.dto.response.CategorizedTalk
import xyz.shininyourcolor.positivetalk.service.PositiveTalkService

@RestController
@RequestMapping("/positivetalk")
class PositiveTalkController(
    val positiveTalkService: PositiveTalkService
) {

    /**
     * 서버가 잘 연결됐는지 테스트용
     *
     * @return 서버에 대한 정보
     */
    @GetMapping("/welcome")
    fun welcome(): String {
        return "긍정적인 말을 처리하는 서버"
    }

    /**
     * 처음 앱을 설치한 경우
     *
     * @param uncategorizedTalk
     * - uuid: 유저 안드로이드 id
     * - time: 내용을 전송한 시간
     * - content: 분류가 되지 않은 내용
     *
     * @return CategorizedTalk 입력한 문장과 분류 결과
     */
    @PostMapping("/send")
    fun categorizeTalk(@RequestBody uncategorizedTalk: UncategorizedTalk)
    : CategorizedTalk {
        println("전송한 유저: ${uncategorizedTalk.uuid} " +
                "전송한 내용: ${uncategorizedTalk.content}")

        val categorizationResult = positiveTalkService.categorizeContent(uncategorizedTalk)

        val categorizedTalk = CategorizedTalk(
            uuid = uncategorizedTalk.uuid,
            category = categorizationResult,
            content = uncategorizedTalk.content
        )

        return categorizedTalk
    }
}