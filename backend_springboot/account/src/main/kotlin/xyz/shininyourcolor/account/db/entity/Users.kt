package xyz.shininyourcolor.account.db.entity

import jakarta.persistence.*
import java.time.LocalDateTime

@Table(name = "users")
@Entity
data class Users(
    @Column(name = "uuid", nullable = false)
    var uuid: String,

    @Column(name = "fcm_token")
    var fcmToken: String
) {
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var userId: Long = 0L

    @Column(name = "activate", nullable = true)
    var activate: Int = 1

    @Column(name = "uninstall_init_date", nullable = true)
    var uninstallInitDate: LocalDateTime? = null

    @Column(name = "uninstall_last_date", nullable = true)
    var uninstallLastDate: LocalDateTime? = null
}