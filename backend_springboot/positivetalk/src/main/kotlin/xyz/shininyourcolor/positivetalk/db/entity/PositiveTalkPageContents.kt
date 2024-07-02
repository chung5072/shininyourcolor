package xyz.shininyourcolor.positivetalk.db.entity

import jakarta.persistence.*
import java.time.LocalDateTime

@Table(name = "positive_talk_page_contents")
@Entity
data class PositiveTalkPageContents(
    /**
     * 유저의 안드로이드 id
     */
    @Column(name = "uuid", nullable = false)
    var uuid: String,

    /**
     * 유저가 내용을 올린 날짜
     */
    @Column(name = "type_date", nullable = true)
    var typeDate: LocalDateTime?,

    /**
     * 유저가 입력한 내용
     */
    @Column(name = "type_content", nullable = false)
    var typeContent: String
) {

    /**
     * positive_talk_page_contents 테이블의 ID
     */
    @Id
    @Column(name = "ptpc_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var ptpcId: Long = 0L

    /**
     * 유저가 입력한 내용의 classification ID
     */
    @Column(name = "classification_id", nullable = true)
    var classificationId: Int = 0
}
