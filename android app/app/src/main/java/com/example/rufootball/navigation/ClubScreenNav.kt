package com.example.rufootball.navigation

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import com.example.rufootball.screens.club.ClubScreen

data class ClubScreenNav(val id : Int) : Screen {

    @Composable
    override fun Content() {
        ClubScreen(id = id)
    }


}