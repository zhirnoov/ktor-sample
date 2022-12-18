package com.zhirnoov.plugins

import com.zhirnoov.db.DatabaseFactory
import com.zhirnoov.model.Club
import com.zhirnoov.model.ClubData
import com.zhirnoov.model.Clubs
import io.ktor.server.routing.*
import io.ktor.http.*
import io.ktor.server.http.content.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.request.*
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction

fun Application.configureRouting() {
}
