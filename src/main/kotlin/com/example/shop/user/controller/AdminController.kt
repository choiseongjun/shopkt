package com.example.shop.user.controller

import com.example.shop.user.dto.ApiResponse
import com.example.shop.user.security.AdminAuthorize
import com.example.shop.user.service.AdminService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Tag(name = "관리자용 API")
@AdminAuthorize
@RestController
@RequestMapping("/admin")
class AdminController(private val adminService: AdminService) {
    @Operation(summary = "회원 목록 조회")
    @GetMapping("/members")
    fun getAllMembers() = ApiResponse.success(adminService.getMembers())

    @Operation(summary = "관리자 목록 조회")
    @GetMapping("/admins")
    fun getAllAdmins() = ApiResponse.success(adminService.getAdmins())
}