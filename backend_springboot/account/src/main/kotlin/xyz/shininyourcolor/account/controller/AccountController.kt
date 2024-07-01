package xyz.shininyourcolor.account.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import xyz.shininyourcolor.account.dto.request.NewbieInfo
import xyz.shininyourcolor.account.dto.response.BaseResponseBody
import xyz.shininyourcolor.account.service.AccountService

@RestController
@RequestMapping("/account")
class AccountController(
    val accountService: AccountService
) {
    /**
     * 서버가 잘 연결됐는지 테스트용
     *
     * @return String 서버에 대한 정보
     */
    @GetMapping("/welcome")
    fun welcome(): String {
        return "계정 관리 서버"
    }

    /**
     * 처음 앱을 설치한 경우
     *
     * @param newbieInfo
     * - uuid: 유저의 안드로이드 ID
     * - fcmToken: fcm token 값
     */
    @PostMapping("/newbie")
    fun saveInstallInfo(@RequestBody newbieInfo: NewbieInfo): ResponseEntity<BaseResponseBody> {
        println(newbieInfo.uuid)
        println(newbieInfo.fcmToken)

        accountService.saveNewbieInfo(newbieInfo)
        println("성공 - 첫 설치 유저의 안드로이드 ID 및 fcm token")

        return ResponseEntity.ok(BaseResponseBody(
            statusCode = 200, message =  "Data Save Success"
        ))
    }

    /**
     * TODO 비활성화할 때 글 공유 중지를 요청한 경우
     */
}