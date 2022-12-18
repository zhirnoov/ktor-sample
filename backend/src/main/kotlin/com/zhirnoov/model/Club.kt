package com.zhirnoov.model

import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.Table

object Clubs : Table("clubs") {
    val id = integer("id").autoIncrement()
    val name = varchar("name", 100)
    val games = integer("games")
    val score = integer("score")
    val position = integer("position")
    val logo = varchar("logo", 100)

    override val primaryKey = PrimaryKey(id)
}

@Serializable
data class Club(
    val id: Int,
    val name: String,
    val games : Int,
    val score : Int,
    val position: Int,
    val logo: String
)