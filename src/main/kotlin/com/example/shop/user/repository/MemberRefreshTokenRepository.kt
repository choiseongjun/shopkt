package com.example.shop.user.repository

import com.example.shop.user.entity.MemberRefreshToken
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface MemberRefreshTokenRepository : JpaRepository<MemberRefreshToken, UUID> {
    fun findByMemberIdAndReissueCountLessThan(id: UUID, count: Long): MemberRefreshToken?
}