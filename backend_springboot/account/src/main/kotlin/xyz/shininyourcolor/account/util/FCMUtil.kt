package xyz.shininyourcolor.account.util

import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import xyz.shininyourcolor.account.service.AccountService

@Component
class FCMUtil(
    val accountService: AccountService
) {
    /**
     * 스케줄러에 의해 일정 시간마다 실행되어 유저가 설치했는지 확인
     */
    @Scheduled(fixedDelay = 10_000L)
    fun checkInstall() {
        println("확인 - 스케줄러 실행")
        accountService.checkInstall()
    }
}