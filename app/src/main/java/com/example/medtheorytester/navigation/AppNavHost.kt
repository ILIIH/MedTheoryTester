package com.example.medtheorytester.navigation

import androidx.compose.runtime.Composable;
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.medtheorytester.ui.screen.MenuScreen
import com.example.medtheorytester.ui.screen.QuizScreen
import com.example.medtheorytester.ui.screen.SplashScreen


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
            composable(NavigationItem.Quiz.route){
                QuizScreen(navController)
            }
            composable(NavigationItem.Menu.route){
                MenuScreen(navController)
            }

        }
}

