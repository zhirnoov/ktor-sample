package com.zhirnoov.db

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import com.zhirnoov.model.*
import com.zhirnoov.toClub
import com.zhirnoov.toPlayer
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SchemaUtils.create
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.transactions.transaction

object DatabaseFactory {

    var db: Database? = null

    fun init() {
        db = Database.connect(hikari())
        transaction {
            create(Clubs, Players)
        }
    }

    fun getAllClubs(): List<Club> = transaction(db) {
        Clubs.selectAll().map { toClub(it) }
    }

    fun getAllPlayersById(id: Int): List<Player> = transaction(db) {
       Players.selectAll().map { toPlayer(it) }.filter { it.clubID == id }
    }

    fun insertClub(clubData: ClubData) = transaction(db) {
        Clubs.insert {
            it[name] = clubData.name ?: ""
            it[games] = clubData.games ?: 0
            it[score] = clubData.score ?: 0
            it[position] = clubData.position ?: 0
            it[logo] = clubData.logo ?: ""
        }
    }

    fun deleteClub(id: Int): Boolean = transaction(db) {
        Clubs.deleteWhere { Clubs.id eq id } > 0
    }

    /*fun editClub(id: Int, clubData: ClubData): Boolean = transaction(db) {
        Clubs.update({ Clubs.id eq id }) {
            it[name] = clubData.name ?: ""
            it[games] = clubData.games ?: 0
            it[score] = clubData.score ?: 0
            it[position] = clubData.position ?: 0
            it[logo] = clubData.logo ?: ""
        } > 0
    }*/

    //Player

    fun insertPlayer(player: Player) = transaction(db) {
        Players.insert {
            it[clubID] = player.clubID
            it[name] = player.name
            it[position] = player.position
            it[year] = player.year
            it[nationality] = player.nationality
            it[photo] = player.photo
        }

    }

    fun deletePlayer(id: Int): Boolean = transaction(db) {
        Players.deleteWhere { Players.id eq id } > 0
    }


    private fun hikari(): HikariDataSource {
        val config = HikariConfig().apply {
            driverClassName = "org.h2.Driver"
            jdbcUrl = "jdbc:h2:./rufootball"
            maximumPoolSize = 3
            isAutoCommit = false
            transactionIsolation = "TRANSACTION_REPEATABLE_READ"
            validate()
        }
        return HikariDataSource(config)
    }
}