package com.example.rufootball

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rufootball.data.Player
import com.example.rufootball.data.RuFootballAPI
import com.example.rufootball.utils.UIStatePlayer
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ClubViewModel @Inject constructor(private val api : RuFootballAPI) : ViewModel() {
    
    var players = mutableStateListOf<Player>()
    private val _uiState = MutableStateFlow<UIStatePlayer>(UIStatePlayer.Loading)
    val uiState = _uiState.asStateFlow()
    
    fun getPlayers(id : Int) {
        viewModelScope.launch {
            viewModelScope.launch {
                val playersList = api.getClub(id)
                if (playersList.isEmpty()) {
                    _uiState.value = UIStatePlayer.Error
                } else {
                    players.clear()
                    players.addAll(playersList)
                    _uiState.value = UIStatePlayer.Success(players)
                }
            }
        }
    }
}