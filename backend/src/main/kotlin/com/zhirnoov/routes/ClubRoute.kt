package com.zhirnoov.routes

import com.zhirnoov.db.DatabaseFactory
import com.zhirnoov.model.Club
import com.zhirnoov.model.ClubData
import com.zhirnoov.toClub
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction

fun Route.ClubRoute() {


}