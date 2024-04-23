package com.example.shop.user.controller

import com.example.shop.user.dto.ApiResponse
import com.example.shop.user.dto.MemberUpdateRequest
import com.example.shop.user.security.UserAuthorize
import com.example.shop.user.service.MemberService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.core.userdetails.User
import org.springframework.web.bind.annotation.*
import java.util.*

@Tag(name = "로그인 후 사용할 수 있는 API")
@UserAuthorize
@RestController
@RequestMapping("/member")
class MemberController(private val memberService: MemberService) {
    @Operation(summary = "회원 정보 조회")
    @GetMapping
    fun getMemberInfo(@AuthenticationPrincipal user: User) =
        ApiResponse.success(memberService.getMemberInfo(UUID.fromString(user.username)))

    @Operation(summary = "회원 탈퇴")
    @DeleteMapping
    fun deleteMember(@AuthenticationPrincipal user: User) =
        ApiResponse.success(memberService.deleteMember(UUID.fromString(user.username)))

    @Operation(summary = "회원 정보 수정")
    @PutMapping
    fun updateMember(@AuthenticationPrincipal user: User, @RequestBody request: MemberUpdateRequest) =
        ApiResponse.success(memberService.updateMember(UUID.fromString(user.username), request))
}