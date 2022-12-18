package com.zhirnoov.model

import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.Table


object Players : Table(name = "players") {
    val clubID = integer("clubID")
    val id = integer("id").autoIncrement()
    val name = varchar("name", 100)
    val position = varchar("position", 100)
    val year = integer("year")
    val nationality = varchar("nationality", 100)
    val photo = varchar("photo", 100)

    override val primaryKey = PrimaryKey(id)
}

@Serializable
data class Player(
    val clubID : Int,
    val id : Int,
    val name : String,
    val position : String,
    val year : Int,
    val nationality : String,
    val photo : String,
)