package com.example.privacysettingmanager.ui.navigation

import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.privacysettingmanager.ui.screens.DetailScreen
import com.example.privacysettingmanager.ui.screens.HomeScreen
import com.example.privacysettingmanager.viewmodel.DetailViewModel
import com.example.privacysettingmanager.viewmodel.HomeScreenViewModel
import com.google.gson.Gson

@Composable
fun AppNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "home") {

        composable("home") {
            val viewModel: HomeScreenViewModel = hiltViewModel()
            HomeScreen(viewModel = viewModel, onServiceClick = { service ->
                val serviceJson = Uri.encode(Gson().toJson(service))
                navController.navigate("details/$serviceJson")
            })
        }

        composable(
            route = "details/{serviceJson}",
            arguments = listOf(navArgument("serviceJson") {
                type = NavType.StringType
            })
        ) {
            val viewModel: DetailViewModel = hiltViewModel()
            DetailScreen(viewModel = viewModel)
        }
    }
}
