package com.example.rufootball

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rufootball.data.Club
import com.example.rufootball.data.RuFootballAPI
import com.example.rufootball.utils.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val api: RuFootballAPI) : ViewModel() {

    var clubs = mutableStateListOf<Club>()
    private val _uiState = MutableStateFlow<UIState>(UIState.Loading)
    val uiState = _uiState.asStateFlow()

    init {
        getAllClubs()
    }

    fun getAllClubs() {
        viewModelScope.launch {
            val table = api.getTable()
            if (table.isEmpty()) {
                _uiState.value = UIState.Error
            } else {
                clubs.clear()
                clubs.addAll(table)
                _uiState.value = UIState.Success(clubs)
            }
        }
    }
}