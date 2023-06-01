package com.kssidll.choochoo

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.compose.runtime.getValue
import com.kssidll.choochoo.Destinations.BASE_ROUTE
import com.kssidll.choochoo.Destinations.INITIAL_ROUTE
import com.kssidll.choochoo.Destinations.SEARCH_CONNECTION_ROUTE
import com.kssidll.choochoo.Destinations.SHOW_CONNECTIONS_ROUTE
import com.kssidll.choochoo.Destinations.SHOW_CONNECTION_DETAILS_ROUTE
import com.kssidll.choochoo.Destinations.SHOW_TICKETS_ROUTE
import com.kssidll.choochoo.Destinations.SHOW_TICKET_DETAILS_ROUTE
import com.kssidll.choochoo.NavigationActions.ACTION_SHOW_TICKET_DETAILS
import com.kssidll.choochoo.notification.Extras.TICKET_ID
import com.kssidll.choochoo.ui.searchconnection.SearchConnectionRoute
import com.kssidll.choochoo.ui.shared.AppScaffold
import com.kssidll.choochoo.ui.showconnectiondetails.ShowConnectionDetailsRoute
import com.kssidll.choochoo.ui.showconnections.ShowConnectionsRoute
import com.kssidll.choochoo.ui.showticketdetails.ShowTicketDetailsRoute
import com.kssidll.choochoo.ui.showtickets.ShowTicketsRoute


object Destinations {
    const val SEARCH_CONNECTION_ROUTE = "searchconnection"
    const val SHOW_CONNECTIONS_ROUTE = "showconnections"
    const val SHOW_CONNECTION_DETAILS_ROUTE = "showconnectiondetails"
    const val SHOW_TICKETS_ROUTE = "showtickets"
    const val SHOW_TICKET_DETAILS_ROUTE = "showticketdetails"

    const val BASE_ROUTE = SEARCH_CONNECTION_ROUTE
    const val INITIAL_ROUTE = SEARCH_CONNECTION_ROUTE
}

object NavigationActions {
    const val ACTION_SHOW_TICKET_DETAILS = "navigation.actions.showticketdetails"
}

@Composable
fun Navigation(
        navController: NavHostController = rememberNavController()
) {
    val navigationViewModel: NavigationViewModel = hiltViewModel()
    val action: String? by navigationViewModel.action.collectAsState()

    when (action) {
        ACTION_SHOW_TICKET_DETAILS -> {
            val id = navigationViewModel.extras?.getInt(TICKET_ID)
            if (navController.currentBackStackEntry?.destination?.route != "${SHOW_TICKET_DETAILS_ROUTE}/$id") {
                navController.popBackStack(BASE_ROUTE, false)
                navController.navigate("${SHOW_TICKET_DETAILS_ROUTE}/$id")
            }
        }
    }

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
                ShowTicketsRoute(
                    onTicketClick = {
                        navController.navigate("$SHOW_TICKET_DETAILS_ROUTE/${it}")
                    }
                )
            }
        }

        composable(
            "$SHOW_TICKET_DETAILS_ROUTE/{ticketId}",
            arguments = listOf(
                navArgument("ticketId") {type = NavType.IntType},
            )
        ) {
            ShowTicketDetailsRoute(
                ticketId = it.arguments?.getInt("ticketId")!!,
                onTicketCancel = {
                    navController.popBackStack()
                },
                onBack = {
                    navController.popBackStack()
                }
            )
        }

    }
}