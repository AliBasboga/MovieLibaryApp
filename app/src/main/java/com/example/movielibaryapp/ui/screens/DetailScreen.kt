package com.example.movielibaryapp.ui.screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.movielibaryapp.data.model.MovieItem
import com.example.movielibaryapp.data.model.getDummyMovie
import com.example.movielibaryapp.ui.componets.MovieRectangleImage
import com.example.movielibaryapp.ui.theme.Pink42


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    movieId: String?
) {
    val context = LocalContext.current
    val detailedMovie = getDummyMovie().first {it.movieImdbID == movieId}

    Scaffold (
        topBar = {
            TopAppBar(
                title = { Text(text = detailedMovie.movieTitle) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Pink42,
                    titleContentColor = Color.Black
                ),
                navigationIcon = {
                   IconButton(onClick = {navController.popBackStack()}) {
                       Icon(imageVector = Icons.AutoMirrored.Default.ArrowBack, tint = Color.Black,  contentDescription = "Back")
                   }
                },
                actions = {
                    IconButton(onClick = { Toast.makeText(context, "${detailedMovie.movieTitle} added to favorites", Toast.LENGTH_SHORT).show()}) {
                        Icon(imageVector = Icons.Filled.Favorite, tint = Color.Red,  contentDescription = "Favorite")
                    }
                }
            )
        }
    ){
        Column (
            modifier = Modifier
                .fillMaxSize()
                .background(color = Pink42)
                .padding(it),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ){
            MainContent(movieItem = detailedMovie)
        }
    }
}

@Composable
fun MainContent(movieItem: MovieItem) {
    MovieDetailData(movieItem = movieItem)
    MovieDetailImages(movieItem = movieItem)

}


@Composable
fun MovieDetailImages(movieItem: MovieItem) {
    LazyRow {
        items(movieItem.movieImages) {image ->
            ElevatedCard(
                modifier = Modifier
                    .wrapContentSize()
                    .width(LocalConfiguration.current.screenWidthDp.dp)
                    .padding(10.dp),
                elevation = CardDefaults.cardElevation(5.dp)
            ){
                MovieRectangleImage(imageUrl = image)
            }
        }
    }
}

@Composable
fun MovieDetailData(movieItem: MovieItem) {
    Text(text = movieItem.movieTitle, style = MaterialTheme.typography.headlineLarge)
    Text(text = movieItem.movieYear, style = MaterialTheme.typography.titleLarge)
    Text(text = movieItem.movieDirector, style = MaterialTheme.typography.titleLarge)
    Text(text = movieItem.movieGenre, style = MaterialTheme.typography.titleMedium)
    Text(text = movieItem.movieImdbID, style = MaterialTheme.typography.titleMedium)
    Text(text = movieItem.moviePlot, modifier = Modifier.padding(10.dp), style = MaterialTheme.typography.titleLarge)

}