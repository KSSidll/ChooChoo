package com.kssidll.choochoo.ui.navigation

sealed class Screen(val route: String) {
    object MainScreen: Screen("main_screen")
}