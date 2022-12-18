package com.example.rufootball.data

import retrofit2.http.GET
import retrofit2.http.Path

interface RuFootballAPI {

    @GET("/table")
    suspend fun getTable() : List<Club>

    @GET("/table/club/{id}")
    suspend fun getClub(@Path("id") id: Int) : List<Player>
}