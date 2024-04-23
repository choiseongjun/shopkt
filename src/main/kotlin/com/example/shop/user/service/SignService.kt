package com.example.shop.user.service

import com.example.shop.user.dto.SignInRequest
import com.example.shop.user.dto.SignInResponse
import com.example.shop.user.dto.SignUpRequest
import com.example.shop.user.dto.SignUpResponse
import com.example.shop.user.entity.Member
import com.example.shop.user.repository.MemberRepository
import com.example.shop.user.security.TokenProvider
import com.example.shop.utils.flushOrThrow
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class SignService(
    private val memberRepository: MemberRepository,
    private val tokenProvider: TokenProvider,
    private val encoder: PasswordEncoder
) {
    @Transactional
    fun registMember(request: SignUpRequest) = SignUpResponse.from(
        memberRepository.flushOrThrow(IllegalArgumentException("이미 사용중인 아이디입니다.")) { save(Member.from(request, encoder)) }
    )

    @Transactional
    fun signIn(request: SignInRequest): SignInResponse {
        val member = memberRepository.findByAccount(request.account)
            ?.takeIf { encoder.matches(request.password, it.password) } ?: throw IllegalArgumentException("아이디 또는 비밀번호가 일치하지 않습니다.")
        val token = tokenProvider.createToken("${member.id}:${member.type}")
        return SignInResponse(member.name, member.type, token)
    }
}