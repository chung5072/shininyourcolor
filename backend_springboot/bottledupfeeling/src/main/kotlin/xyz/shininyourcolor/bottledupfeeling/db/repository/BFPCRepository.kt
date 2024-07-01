package xyz.shininyourcolor.bottledupfeeling.db.repository

import org.springframework.data.jpa.repository.JpaRepository
import xyz.shininyourcolor.bottledupfeeling.db.entity.BottledupFeelingPageContents

interface BFPCRepository : JpaRepository<BottledupFeelingPageContents, Long> {
}