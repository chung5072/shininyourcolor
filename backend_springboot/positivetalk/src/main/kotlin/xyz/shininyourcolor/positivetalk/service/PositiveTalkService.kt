package xyz.shininyourcolor.positivetalk.service

import xyz.shininyourcolor.positivetalk.dto.request.UncategorizedTalk

interface PositiveTalkService {
    // 입력한 문장에 대한 결과를 반환
    fun categorizeContent(uncategorizedTalk: UncategorizedTalk): String
}