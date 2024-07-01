package xyz.shininyourcolor.account.db.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import xyz.shininyourcolor.account.dto.request.NewbieInfo
import xyz.shininyourcolor.account.db.entity.Users

interface UserRepository : JpaRepository<Users, Long> {

    /**
     * users 테이블에서 유저의 안드로이드 id를 기반으로 정보를 가져옴
     * 
     * @param uuid 유저의 안드로이드 id
     * 
     * @return Users 유저의 정보
     */
    fun findByUuid(uuid: String): Users

    /**
     * users 테이블에서 유저의 안드로이드 id와 token값을 가져옴
     *
     * @return List<NewbieInfo>
     *     - uuid: 유저의 안드로이드 id
     *     - fcmToken: 유저의 Firebase Cloud Message Token
     */
    @Query(value = "select " +
            "new xyz.shininyourcolor.account.dto.request.NewbieInfo(" +
            "uuid, fcmToken" +
            ") from Users")
    fun findUUIDAndFCMToken(): List<NewbieInfo>
    
    /**
     * TODO 비활성화할 때 글 공유 중지를 요청한 경우
     */
}