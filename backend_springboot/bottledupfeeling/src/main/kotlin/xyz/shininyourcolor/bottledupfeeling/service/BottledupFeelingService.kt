package xyz.shininyourcolor.bottledupfeeling.service

import xyz.shininyourcolor.bottledupfeeling.dto.request.UncategorizedTalk

interface BottledupFeelingService {
    // 입력한 문장에 대한 결과를 반환
    fun categorizeContent(uncategorizedTalk: UncategorizedTalk): String
}
