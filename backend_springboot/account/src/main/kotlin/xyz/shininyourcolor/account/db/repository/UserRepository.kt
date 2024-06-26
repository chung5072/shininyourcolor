package xyz.shininyourcolor.account.db.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import xyz.shininyourcolor.account.dto.request.NewbieInfo
import xyz.shininyourcolor.account.db.entity.Users

interface UserRepository : JpaRepository<Users, Long> {

    @Query(value = "select " +
            "new xyz.shininyourcolor.account.dto.request.NewbieInfo(" +
            "uuid, fcmToken" +
            ") from Users")
    fun findUUIDAndFCMToken(): List<NewbieInfo>
}