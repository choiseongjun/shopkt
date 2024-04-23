package com.example.shop.user.repository

import com.example.shop.user.common.MemberType
import com.example.shop.user.entity.Member
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface MemberRepository : JpaRepository<Member, UUID> {
    fun findByAccount(account: String): Member?
    fun findAllByType(type: MemberType): List<Member>
}