package com.kssidll.choochoo.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.kssidll.choochoo.ui.navigation.Destinations.SEARCH_CONNECTION_ROUTE
import com.kssidll.choochoo.ui.navigation.Destinations.SHOW_CONNECTIONS_ROUTE
import com.kssidll.choochoo.ui.searchconnection.SearchConnectionRoute

object Destinations {
    const val SEARCH_CONNECTION_ROUTE = "searchconnection"
    const val SHOW_CONNECTIONS_ROUTE = "showconnections"
}

@Composable
fun Navigation(
        navController: NavHostController = rememberNavController()
) {

    NavHost(
        navController = navController,
        startDestination = Screen.MainScreen.route
    ) {
        composable(SEARCH_CONNECTION_ROUTE) {
            SearchConnectionRoute(
                onSearchConnection = {
                    navController.navigate(SHOW_CONNECTIONS_ROUTE)
                }
            )
        }

    }
}