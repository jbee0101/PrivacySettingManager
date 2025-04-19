package com.example.privacysettingmanager.ui.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.privacysettingmanager.ui.screens.BaseScreen
import com.example.privacysettingmanager.ui.screens.DetailScreen
import com.example.privacysettingmanager.ui.screens.HomeScreen
import com.example.privacysettingmanager.viewmodel.DetailViewModel
import com.example.privacysettingmanager.viewmodel.HomeScreenViewModel
import com.google.gson.Gson
import java.net.URLEncoder

/**
 * Sets up the navigation graph for the app using Jetpack Compose Navigation.
 *
 * @param navController The NavHostController that controls app navigation.
 */
@Composable
fun AppNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "home") {

        composable("home") {
            val viewModel: HomeScreenViewModel = hiltViewModel()
            BaseScreen {
                HomeScreen(viewModel = viewModel, onServiceClick = { service ->
                    val serviceJson = URLEncoder.encode(Gson().toJson(service), "utf-8")
                    navController.navigate("details/$serviceJson")
                })
            }
        }

        composable(
            route = "details/{serviceJson}",
            arguments = listOf(navArgument("serviceJson") {
                type = NavType.StringType
            })
        ) {
            val viewModel: DetailViewModel = hiltViewModel()
            BaseScreen(
                showBackButton = true,
                onBackClick = { navController.popBackStack() }
            ) {
                DetailScreen(viewModel = viewModel)
            }
        }
    }
}
