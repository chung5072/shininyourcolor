package xyz.shininyourcolor.positivetalk.db.repository

import org.springframework.data.jpa.repository.JpaRepository
import xyz.shininyourcolor.positivetalk.db.entity.PositiveTalkPageContents

interface PTPCRepository : JpaRepository<PositiveTalkPageContents, Long> {
}