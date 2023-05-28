package com.kssidll.choochoo.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.kssidll.choochoo.ui.navigation.Destinations.SEARCH_CONNECTION_ROUTE
import com.kssidll.choochoo.ui.navigation.Destinations.SHOW_CONNECTIONS_ROUTE
import com.kssidll.choochoo.ui.searchconnection.SearchConnectionRoute
import com.kssidll.choochoo.ui.shared.AppScaffold
import com.kssidll.choochoo.ui.showconnections.ShowConnectionsRoute

object Destinations {
    const val SEARCH_CONNECTION_ROUTE = "searchconnection"
    const val SHOW_CONNECTIONS_ROUTE = "showconnections"
    const val SHOW_TICKETS_ROUTE = "showtickets"
}

@Composable
fun Navigation(
        navController: NavHostController = rememberNavController()
) {

    NavHost(
        navController = navController,
        startDestination = SEARCH_CONNECTION_ROUTE
    ) {
        composable(SEARCH_CONNECTION_ROUTE) {
            AppScaffold(
                navHostController = navController,
                selectedNavButton = SEARCH_CONNECTION_ROUTE
            ) {
                SearchConnectionRoute(
                    onSearchConnection = {
                        navController.navigate("$SHOW_CONNECTIONS_ROUTE/${it.origin}/${it.destination}")
                    }
                )
            }
        }

        composable(
            "$SHOW_CONNECTIONS_ROUTE/{origin}/{destination}",
            arguments = listOf(
                navArgument("origin") { type = NavType.StringType },
                navArgument("destination") { type = NavType.StringType }
            )
        ) {
            ShowConnectionsRoute(
                origin = it.arguments?.getString("origin")!!,
                destination = it.arguments?.getString("destination")!!,
                onConnectionSelect = {
                    /*TODO show screen with connection Data*/
                },
                onBack = {
                    navController.popBackStack()
                }
            )
        }

    }
}