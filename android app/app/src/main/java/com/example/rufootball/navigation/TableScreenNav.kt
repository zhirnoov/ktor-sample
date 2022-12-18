package com.example.rufootball.navigation

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import com.example.rufootball.screens.table.TableScreen

class TableScreenNav : Screen {

    @Composable
    override fun Content() {
        TableScreen()
    }
}