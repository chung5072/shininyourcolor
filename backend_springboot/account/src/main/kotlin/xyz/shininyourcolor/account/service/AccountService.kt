package xyz.shininyourcolor.account.service

import xyz.shininyourcolor.account.dto.request.NewbieInfo

interface AccountService {
    // 처음 설치한 유저의 정보를 저장
    fun saveNewbieInfo(newbieInfo: NewbieInfo)
    // TODO 비활성화할 때 글 공유 중지를 요청한 경우
    // 해당 유저들이 설치했는지 확인
    fun checkInstall()
}