package com.example.medtheorytester.navigation

enum class Screen {
    HOME,
    LOGIN,
    SPLASH
}
sealed class NavigationItem(val route: String) {
    object Home : NavigationItem(Screen.HOME.name)
    object Login : NavigationItem(Screen.LOGIN.name)
    object  Splash : NavigationItem(Screen.SPLASH.name)
}