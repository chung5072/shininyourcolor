package xyz.shininyourcolor.account.db.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import xyz.shininyourcolor.account.dto.request.NewbieInfo
import xyz.shininyourcolor.account.db.entity.Users

interface UserRepository : JpaRepository<Users, Long> {

    /**
     * users 테이블에서 유저의 id와 token값을 가져옴
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
}