package com.example.rufootball.screens.club

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.rufootball.ClubViewModel
import com.example.rufootball.screens.table.ErrorMessage
import com.example.rufootball.screens.table.LoadingProcess
import com.example.rufootball.utils.UIStatePlayer

@Composable
fun ClubScreen(id: Int) {
    val viewModel : ClubViewModel = hiltViewModel()

    LaunchedEffect(Unit) {
        viewModel.getPlayers(id)
    }
    val state = viewModel.uiState.collectAsState()

    when(state.value) {
     is UIStatePlayer.Loading -> LoadingProcess()
     is UIStatePlayer.Error -> ErrorMessage()
     is UIStatePlayer.Success -> AllPlayers(players = (state.value as UIStatePlayer.Success).player.toList())
    }

}