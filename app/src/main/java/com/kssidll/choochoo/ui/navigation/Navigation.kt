package com.kssidll.choochoo.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.kssidll.choochoo.ui.navigation.Destinations.BASE_ROUTE
import com.kssidll.choochoo.ui.navigation.Destinations.INITIAL_ROUTE
import com.kssidll.choochoo.ui.navigation.Destinations.SEARCH_CONNECTION_ROUTE
import com.kssidll.choochoo.ui.navigation.Destinations.SHOW_CONNECTIONS_ROUTE
import com.kssidll.choochoo.ui.navigation.Destinations.SHOW_CONNECTION_DETAILS_ROUTE
import com.kssidll.choochoo.ui.navigation.Destinations.SHOW_TICKETS_ROUTE
import com.kssidll.choochoo.ui.searchconnection.SearchConnectionRoute
import com.kssidll.choochoo.ui.shared.AppScaffold
import com.kssidll.choochoo.ui.showconnectiondetails.ShowConnectionDetailsRoute
import com.kssidll.choochoo.ui.showconnections.ShowConnectionsRoute
import com.kssidll.choochoo.ui.showtickets.ShowTicketsRoute

object Destinations {
    const val SEARCH_CONNECTION_ROUTE = "searchconnection"
    const val SHOW_CONNECTIONS_ROUTE = "showconnections"
    const val SHOW_CONNECTION_DETAILS_ROUTE = "showconnectiondetails"
    const val SHOW_TICKETS_ROUTE = "showtickets"

    const val BASE_ROUTE = SEARCH_CONNECTION_ROUTE
    const val INITIAL_ROUTE = SEARCH_CONNECTION_ROUTE
}

@Composable
fun Navigation(
        navController: NavHostController = rememberNavController()
) {

    NavHost(
        navController = navController,
        startDestination = INITIAL_ROUTE
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
            "$SHOW_CONNECTION_DETAILS_ROUTE/{connectionId}/{date}",
            arguments = listOf(
                navArgument("connectionId") {type = NavType.IntType},
                navArgument("date") {type = NavType.LongType}
            )
        ) {
            ShowConnectionDetailsRoute(
                connectionId = it.arguments?.getInt("connectionId")!!,
                date = it.arguments?.getLong("date")!!,
                onTicketBuy = {
                    navController.popBackStack(BASE_ROUTE, false)
                    navController.navigate(SHOW_TICKETS_ROUTE)
                },
                onBack = {
                    navController.popBackStack()
                }
            )
        }

        composable(
            "$SHOW_CONNECTIONS_ROUTE/{origin}/{destination}",
            arguments = listOf(
                navArgument("origin") {type = NavType.StringType},
                navArgument("destination") {type = NavType.StringType}
            )
        ) {
            ShowConnectionsRoute(
                origin = it.arguments?.getString("origin")!!,
                destination = it.arguments?.getString("destination")!!,
                onConnectionSelect = { connectionData, date ->
                    navController.navigate("$SHOW_CONNECTION_DETAILS_ROUTE/${connectionData.id}/${date}")
                },
                onBack = {
                    navController.popBackStack()
                }
            )
        }

        composable(SHOW_TICKETS_ROUTE) {
            AppScaffold(
                navHostController = navController,
                selectedNavButton = SHOW_TICKETS_ROUTE
            ) {
                ShowTicketsRoute()
            }
        }

    }
}