package xyz.shininyourcolor.account.service

import com.google.firebase.messaging.BatchResponse
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.MulticastMessage
import org.springframework.stereotype.Service
import xyz.shininyourcolor.account.db.entity.Users
import xyz.shininyourcolor.account.db.repository.UserRepository
import xyz.shininyourcolor.account.dto.request.NewbieInfo
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit
import kotlin.math.abs

@Service("accountService")
class AccountServiceImpl(
    val userRepository: UserRepository
) : AccountService {
    /**
     * 처음 설치한 유저의 정보를 저장
     *
     * @param newbieInfo 설치한 유저의 정보
     * - uuid: 유저의 안드로이드 id
     * - fcmToken: fcm token
     */
    override fun saveNewbieInfo(newbieInfo: NewbieInfo) {
        // USERS 테이블을 불러와서 user_uuid, user_token create
        val users = Users(
            uuid = newbieInfo.uuid,
            fcmToken = newbieInfo.fcmToken
        )

        userRepository.save(users)
    }

    /**
     * TODO 비활성화할 때 글 공유 중지를 요청한 경우
     */

    /**
     * 특정 시간마다 해당 유저가 설치했는지 확인
     * 
     * @exception IllegalArgumentException 
     * 아직 설치한 유저가 한 명도 없을 경우 해당 내용 출력
     */
    override fun checkInstall() {
        // USERS 테이블에 있는 정보들을 불러오기
        val allUsersIdAndToken = userRepository.findUUIDAndFCMToken()
        // uuid 값으로 리스트 생성
        val uuidList = allUsersIdAndToken.map { eachInfo ->
            eachInfo.uuid
        }
        // fcmToken 값으로 리스트 생성
        val registrationTokens = allUsersIdAndToken.map { eachInfo ->
            eachInfo.fcmToken
        }

        try {
            // FCM 메세지 전송
            val message: MulticastMessage = MulticastMessage.builder()
                .addAllTokens(registrationTokens)
                .build()

            // 전송 후 응답값
            val response: BatchResponse = FirebaseMessaging.getInstance().sendEachForMulticast(message)
            // 응답값으로 어떤 토큰이 현재 살아있지 않은지 체크
            val responses = response.responses

            for ((index, eachRes) in responses.withIndex()) {
                // 전송을 실패했을 때
                if (!(eachRes.isSuccessful)) {
                    val exceptionMessage = eachRes.exception.message
                    
                    // 에러 메세지에 따라서 다른 처리를 진행
                    if (exceptionMessage?.contains("not found") == true) {
                        println("체크 - 유저 ${uuidList[index]}는 삭제한 것으로 보임")

                        changeInstallStatus(uuid = uuidList[index])
                    } else {
                        println("에러 - 설정 파일 확인")
                    }
                }
            }
        } catch (e: IllegalArgumentException) {
            println("에러 - 현재 설치한 인원이 하나도 없음")
        }
    }

    /**
     * USERS 테이블에 각 안드로이드 ID 해당하는 유저가 삭제했을 때
     *
     * @param uuid 유저의 안드로이드 id
     */
    private fun changeInstallStatus(uuid: String) {
        // 현재 시간
        val curr = LocalDateTime.now()
        // 해당 안드로이드 ID 유저의 정보를 가져옴
        val user = userRepository.findByUuid(uuid)
        println("체크 - 유저의 설치 상태: ${user.installStatus}" +
                "유저의 첫 삭제 감시 날짜: ${user.uninstallInitDate} +" +
                "유저의 최근 삭제 감지 날짜: ${user.uninstallRecentDate} +" +
                "유저의 공유 상태: ${user.shareStatus}")

        when {
            // 공유 상태가 중지되어있으면 바로 종료
            user.shareStatus == 0 -> return
            // 공유 상태가 중지가 아니고, 처음 삭제한 것으로 감지된 상태이면
            user.uninstallInitDate == null -> {
                // 초기 삭제이므로 설치 상태를 삭제로 변경
                user.installStatus = 0
                // 처음 삭제가 감지된 시간 및 가장 최근에 삭제를 감지한 시간을
                // 현재 서버가 감지한 시간으로 저장
                user.uninstallInitDate = curr // 처음 삭제가 감지된 시간
                user.uninstallRecentDate = curr // 가장 최근에 삭제를 감지한 시간
            }
            // 공유 상태가 중지도 아니고, 이미 삭제했다고 감지된 상태면
            else -> {
                // 가장 최근에 감지된 시간를 현재 시간으로 변경
                user.uninstallRecentDate = curr

                // 일정 시간이 많이 지나있다면 공유 상태를 중지하는 것으로 변경
                if (curr.differentSeconds(user.uninstallInitDate!!) > 30) {
                    user.shareStatus = 0
                }
            }
        }

        // 변경 사항을 저장
        userRepository.save(user)
    }

    /**
     * 두 시간의 차이를 계산하는 함수
     * 해당 함수를 부르는 함수는 마지막 시간
     * 
     * @param initDate 초기 시간
     * 
     * @return long 두 시간의 차이
     */
    private fun LocalDateTime.differentSeconds(initDate: LocalDateTime): Long {
        return abs(ChronoUnit.SECONDS.between(this, initDate))
    }
}