package xyz.shininyourcolor.positivetalk.db.entity

import jakarta.persistence.*
import java.time.LocalDateTime

@Table(name = "positive_talk_page_contents")
@Entity
data class PositiveTalkPageContents(
    @Column(name = "uuid", nullable = false)
    var uuid: String,

    @Column(name = "type_date", nullable = true)
    var typeDate: LocalDateTime?,

    @Column(name = "type_content", nullable = false)
    var typeContent: String
) {
    @Id
    @Column(name = "ptpc_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var ptpcId: Long = 0L

    @Column(name = "classification_id", nullable = true)
    var classificationId: Int = 0
}
