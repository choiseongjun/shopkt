package com.example.shop.user.service

import com.example.shop.user.common.MemberType
import com.example.shop.user.dto.MemberInfoResponse
import com.example.shop.user.repository.MemberRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AdminService(private val memberRepository: MemberRepository) {
    @Transactional(readOnly = true)
    fun getMembers(): List<MemberInfoResponse> = memberRepository.findAllByType(MemberType.USER).map(MemberInfoResponse::from)

    @Transactional(readOnly = true)
    fun getAdmins(): List<MemberInfoResponse> = memberRepository.findAllByType(MemberType.ADMIN).map(MemberInfoResponse::from)
}