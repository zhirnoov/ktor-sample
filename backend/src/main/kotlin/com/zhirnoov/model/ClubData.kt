package com.zhirnoov.model

import kotlinx.serialization.Serializable

@Serializable
data class ClubData(
    val name: String?,
    val games : Int?,
    val score : Int?,
    val position: Int?,
    val logo: String?
)
