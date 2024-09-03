package com.example.medtheorytester.navigation

enum class Screen {
    MENU,
    SPLASH,
    QUIZ
}
sealed class NavigationItem(val route: String) {
    object Menu : NavigationItem(Screen.MENU.name)
    object  Splash : NavigationItem(Screen.SPLASH.name)
    object Quiz : NavigationItem(Screen.QUIZ.name)

}