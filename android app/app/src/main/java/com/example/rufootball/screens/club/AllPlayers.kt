package com.example.rufootball.screens.club

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.rufootball.data.Player

@Composable
fun AllPlayers(players: List<Player>) {

    LazyColumn(modifier = Modifier.padding(8.dp)) {
        items(players) { player ->
            PlayerItem(player = player)
        }
    }

}

@Composable
fun PlayerItem(player: Player) {

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            modifier = Modifier
                .size(height = 90.dp, width = 90.dp),
            model = player.photo,
            contentDescription = null
        )

        Column {
            Text(text = player.name, fontSize = 18.sp, fontWeight = FontWeight.W500)
            Text(text = player.position, fontSize = 16.sp, fontWeight = FontWeight.W400)
            Text(
                text = player.year.toString(),
                fontSize = 14.sp,
                fontWeight = FontWeight.W300,
                color = Color.DarkGray
            )
        }
    }

}
