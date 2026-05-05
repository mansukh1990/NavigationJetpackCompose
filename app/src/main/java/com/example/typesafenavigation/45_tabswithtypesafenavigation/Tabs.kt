package com.example.typesafenavigation.`45_tabswithtypesafenavigation`

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.PrimaryTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kotlinx.serialization.Serializable

@Serializable
object SongsScreen

@Serializable
object ArtistsScreen

@Serializable
object playlistScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavigationTabExample() {

    val navController = rememberNavController()

    val tabs = listOf(
        SongsScreen to "Songs",
        ArtistsScreen to "Artists",
        playlistScreen to "Playlists"
    )

    var selectedTab by rememberSaveable { mutableIntStateOf(0) }

    Column() {
        PrimaryTabRow(
            selectedTabIndex = selectedTab
        ) {
            tabs.forEachIndexed { index, pair ->
                Tab(
                    selected = selectedTab == index,
                    onClick = {
                        selectedTab = index
                        navController.navigate(pair.first)
                    },
                    text = {
                        Text(pair.second)
                    }
                )
            }
            TabNavigation(navController)
        }

    }

}

@Composable
fun TabNavigation(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = SongsScreen
    ) {
        composable<SongsScreen> {
            SongsScreenUI()
        }
        composable<ArtistsScreen> {
            ArtistsScreenUI()
        }
        composable<playlistScreen> {
            PlaylistsScreenUI()
        }
    }
}

@Composable
fun SongsScreenUI() {

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text("Songs Screen")
    }
}

@Composable
fun ArtistsScreenUI() {

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text("Artists Screen")
    }
}

@Composable
fun PlaylistsScreenUI() {

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text("Playlists Screen")
    }
}