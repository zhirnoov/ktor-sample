package com.example.rufootball.screens.all_clubs

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import coil.compose.AsyncImage
import com.example.rufootball.data.Club
import com.example.rufootball.navigation.ClubScreenNav

@Composable
fun AllClubs(clubs: List<Club>) {

    LazyColumn(modifier = Modifier.padding(8.dp)) {
        item {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 8.dp, bottom = 8.dp)
            ) {
                Text(modifier = Modifier.weight(1f), text = "Клуб")
                Text(text = "Очки")
            }
            Divider(thickness = 1.dp)
        }
        items(clubs) { club ->
            ClubItem(club = club)
            Divider(thickness = 1.dp)
        }
    }

}

@Composable
fun ClubItem(club: Club) {

    val navigator = LocalNavigator.currentOrThrow

    Row(
        modifier = Modifier
            .fillMaxSize()
            .clickable {
                navigator.push(ClubScreenNav(id = club.id))
            },
        verticalAlignment = Alignment.CenterVertically
    ) {

        Text(modifier = Modifier.width(20.dp), text = club.position.toString())

        AsyncImage(
            modifier = Modifier
                .size(50.dp)
                .padding(start = 4.dp),
            model = club.logo,
            contentDescription = null
        )
        Text(
            modifier = Modifier.padding(start = 4.dp),
            text = club.name
        )
        Text(
            text = club.score.toString(),
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.End
        )
    }
}
