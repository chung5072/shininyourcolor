package xyz.shininyourcolor.account.service

import xyz.shininyourcolor.account.dto.request.Activation
import xyz.shininyourcolor.account.dto.request.NewbieInfo

interface AccountService {
    // 처음 설치한 유저의 정보를 저장
    fun saveNewbieInfo(newbieInfo: NewbieInfo)
    // 계정 활성화 / 비활성화 버튼을 눌렀을 때
    fun getActivation(activation: Activation)
    // 해당 유저들이 설치했는지 확인
    fun checkInstall()
}