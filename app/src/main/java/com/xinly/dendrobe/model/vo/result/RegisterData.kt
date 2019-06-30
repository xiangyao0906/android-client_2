package com.xinly.dendrobe.model.vo.result

/**
 * Created by zm on 2019-06-28.
 */
data class RegisterData(
    val member: Member,
    val token: String
)

data class Member(
    val code: Int
)