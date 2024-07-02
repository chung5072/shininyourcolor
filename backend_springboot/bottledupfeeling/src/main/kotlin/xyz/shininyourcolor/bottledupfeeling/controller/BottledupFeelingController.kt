package xyz.shininyourcolor.bottledupfeeling.controller

import org.springframework.web.bind.annotation.*
import xyz.shininyourcolor.bottledupfeeling.dto.request.UncategorizedTalk
import xyz.shininyourcolor.bottledupfeeling.dto.response.CategorizedTalk
import xyz.shininyourcolor.bottledupfeeling.service.BottledupFeelingService

@RestController
@RequestMapping("/bottledupfeeling")
class BottledupFeelingController(
    val bottledupFeelingService: BottledupFeelingService
) {
    /**
     * 서버가 잘 연결됐는지 테스트용
     *
     * @return 서버에 대한 정보
     */
    @GetMapping("/welcome")
    fun welcome(): String {
        return "속에 쌓아둔 말을 처리하는 서버"
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

        val categorizationResult = bottledupFeelingService.categorizeContent(uncategorizedTalk)

        val categorizedTalk = CategorizedTalk(
            uuid = uncategorizedTalk.uuid,
            category = categorizationResult,
            content = uncategorizedTalk.content
        )

        return categorizedTalk
    }
}