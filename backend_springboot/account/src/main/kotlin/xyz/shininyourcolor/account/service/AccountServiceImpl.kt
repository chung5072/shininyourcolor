package xyz.shininyourcolor.account.service

import com.google.firebase.messaging.BatchResponse
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.MulticastMessage
import org.springframework.stereotype.Service
import xyz.shininyourcolor.account.db.entity.Users
import xyz.shininyourcolor.account.db.repository.UserRepository
import xyz.shininyourcolor.account.dto.request.Activation
import xyz.shininyourcolor.account.dto.request.NewbieInfo
import java.time.LocalDateTime

@Service("accountService")
class AccountServiceImpl(
    val userRepository: UserRepository
) : AccountService {
    /**
     * 처음 설치한 유저의 정보를 저장
     *
     * @param newbieInfo 설치한 유저의 정보
     * - uuid: 유저 id
     * - fcmToken: fcm token
     */
    override fun saveNewbieInfo(newbieInfo: NewbieInfo) {
        // TODO - DB: USERS 테이블을 불러와서 user_uuid, user_token create
        val users = Users(
            uuid = newbieInfo.uuid,
            fcmToken = newbieInfo.fcmToken
        )

        userRepository.save(users)
    }

    /**
     * 계정 활성화 혹은 비활성화 버튼을 누른 경우
     *
     * @param activation 계정 활성화 혹은 비활성화 누른 유저의 정보
     * - uuid: 유저 id
     * - activate: 활성화 | 비활성화 여부
     */
    override fun getActivation(activation: Activation) {
        // TODO - fun: changeActivation()을 통해 해당 정보 update

        changeActivation(
            uuid = activation.uuid,
            activate = activation.activate
        )
    }

    /**
     * 특정 시간마다 해당 유저가 설치했는지 확인
     */
    override fun checkInstall() {
        // TODO - DB: USERS 테이블에 있는 정보들을 불러오기
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
                if (!(eachRes.isSuccessful)) {
                    println("유저 ${uuidList[index]}는 삭제한 것으로 보임")

                    changeActivation(
                        uuid = uuidList[index],
                        activate = false
                    )
                }
            }
        } catch (e: IllegalArgumentException) {
            println("현재 설치한 인원이 하나도 없음")
        }
    }

    /**
     * USERS 테이블에 각 id에 해당하는 활성화 | 비활성화 및 시간 정보 업데이트
     *
     * @param uuid 유저 id
     * @param activate 해당 유저의 활성화 | 비활성화 여부
     */
    private fun changeActivation(uuid: String, activate: Boolean) {
        // 현재 시간
        val curr = LocalDateTime.now()

        // TODO - DB: uuid와 activate정보를 update + 비활성화인 경우 시간 추가
    }
}