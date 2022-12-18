package com.example.rufootball.screens.table

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.rufootball.MainViewModel
import com.example.rufootball.screens.all_clubs.AllClubs
import com.example.rufootball.utils.UIState

@Composable
fun TableScreen() {
    val viewModel: MainViewModel = hiltViewModel()
    val state = viewModel.uiState.collectAsState()

    when (state.value) {
        is UIState.Loading -> LoadingProcess()
        is UIState.Error -> ErrorMessage()
        is UIState.Success -> AllClubs(clubs = (state.value as UIState.Success).clubs.toList())
    }
}

@Composable
fun LoadingProcess(modifier: Modifier = Modifier) {
    Column(
        modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        CircularProgressIndicator(
            modifier = modifier
                .size(100.dp)
        )
    }

}

@Composable
fun ErrorMessage(modifier: Modifier = Modifier) {
    Column(
        modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Не получилось загрузить данные. Попробуйте снова")
    }
}