package xyz.shininyourcolor.account.db.entity

import jakarta.persistence.*
import java.time.LocalDateTime

@Table(name = "users")
@Entity
data class Users(
    /**
     * 유저의 안드로이드 id
     */
    @Column(name = "uuid", nullable = false)
    var uuid: String,

    /**
     * 유저의 Firebase Cloud Message Token
     */
    @Column(name = "fcm_token")
    var fcmToken: String
) {
    /**
     * 유저 테이블의 ID
     */
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var userId: Long = 0L

    /**
     * 해당 유저의 설치 정보
     */
    @Column(name = "install_status", nullable = true)
    var installStatus: Int = 1

    /**
     * 유저가 앱을 지운 것이 확인된 첫 날짜
     */
    @Column(name = "uninstall_init_date", nullable = true)
    var uninstallInitDate: LocalDateTime? = null

    /**
     * 유저가 앱을 지운 것이 확인된 가장 최근 날짜
     */
    @Column(name = "uninstall_recent_date", nullable = true)
    var uninstallRecentDate: LocalDateTime? = null

    /**
     * 비활성화시 글을 보여줄 것인지 설정 - 앱에서 직접 설정
     */
    @Column(name = "share_status", nullable = true)
    var shareStatus: Int = 1
}