package com.example.rufootball.utils

import androidx.compose.runtime.snapshots.SnapshotStateList
import com.example.rufootball.data.Club
import com.example.rufootball.data.Player

sealed class UIState {
    object Loading : UIState()
    object Error : UIState()
    data class Success(val clubs : SnapshotStateList<Club>) : UIState()
}

sealed class UIStatePlayer {
    object Loading : UIStatePlayer()
    object Error : UIStatePlayer()
    data class Success(val player : SnapshotStateList<Player>) : UIStatePlayer()
}