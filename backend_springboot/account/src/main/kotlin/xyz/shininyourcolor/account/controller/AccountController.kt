package xyz.shininyourcolor.account.controller

import org.springframework.web.bind.annotation.*
import xyz.shininyourcolor.account.dto.request.Activation
import xyz.shininyourcolor.account.dto.request.NewbieInfo
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
     * - uuid: 유저 id
     * - fcmToken: fcm token 값
     */
    @PostMapping("/newbie")
    fun saveInstallInfo(@RequestBody newbieInfo: NewbieInfo) {
        println(newbieInfo.uuid)
        println(newbieInfo.fcmToken)

        accountService.saveNewbieInfo(newbieInfo)
    }

    /**
     * 계정 활성화 혹은 비활성화 버튼을 누른 경우
     *
     * @param activation
     * - uuid: 유저의 id
     * - activate: 해당 유저의 활성화 | 비활성화 여부
     */
    @PostMapping("/activate")
    fun changeActivation(@RequestBody activation: Activation) {
        println(activation.uuid)
        println(activation.activate)
    }
}