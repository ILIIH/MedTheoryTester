package com.example.medtheorytester.navigation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable;
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable


@Composable
fun AppNavHost(
    modifier: Modifier =Modifier,
    navController: NavHostController,
    startDestination:String=NavigationItem.Splash.route, ) {
        NavHost(
            modifier = modifier,
            navController = navController,
            startDestination = startDestination
        ) {
            composable(NavigationItem.Splash.route) {
                SplashScreen(navController)
            }
        }
}


@Composable
fun SplashScreen(navController : NavHostController) {
    Text(
        text = "Hello TEST!",
    )
}