package com.kssidll.choochoo.ui.shared

import android.content.res.Configuration
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.kssidll.choochoo.ui.theme.ChooChooTheme
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.kssidll.choochoo.ui.navigation.Destinations
import kotlinx.coroutines.launch

@Composable
fun NavigationDrawer(
    navHostController: NavHostController,
    selectedNavButton: String,
    onNavigation: suspend () -> Unit,
    onNothing: suspend () -> Unit
) {
    val scope = rememberCoroutineScope()
    ModalDrawerSheet(
        drawerContainerColor = MaterialTheme.colorScheme.background,
        drawerContentColor = MaterialTheme.colorScheme.onBackground
    ) {
        NavigationDrawerItem(
            icon = Icons.Default.Search,
            text = "Search Connections",
            selected = selectedNavButton == Destinations.SEARCH_CONNECTION_ROUTE,
            onClick = {
                scope.launch {
                    if (selectedNavButton != Destinations.SEARCH_CONNECTION_ROUTE) {
                        onNavigation()
                        navHostController.popBackStack(Destinations.BASE_ROUTE, false)
                        navHostController.navigate(Destinations.SEARCH_CONNECTION_ROUTE)
                    } else {
                        onNothing()
                    }
                }
            },
            contentDescription = "Navigates to screen where you can search for connections"
        )

        Spacer(modifier = Modifier.height(2.dp))

        NavigationDrawerItem(
            icon = Icons.Default.DateRange,
            text = "Tickets",
            selected = selectedNavButton == Destinations.SHOW_TICKETS_ROUTE,
            onClick = {
                /*TODO navigate to tickets*/
            },
            contentDescription = "Navigates to screen with bought tickets"
        )

    }
}

@Composable
fun NavigationDrawerItem(
    icon: ImageVector,
    text: String,
    selected: Boolean,
    onClick: () -> Unit,
    contentDescription: String? = null
) {
    NavigationDrawerItem(
        label = {
            NavigationItemLabel(
                icon = icon,
                text = text,
                contentDescription = contentDescription
            )
        },
        selected = selected,
        onClick = onClick,
        colors = NavigationDrawerItemDefaults.colors(
            selectedContainerColor = MaterialTheme.colorScheme.secondary,
            unselectedContainerColor = MaterialTheme.colorScheme.primary,
            selectedIconColor = MaterialTheme.colorScheme.onSecondary,
            unselectedIconColor = MaterialTheme.colorScheme.onBackground,
            selectedTextColor = MaterialTheme.colorScheme.onSecondary,
            unselectedTextColor = MaterialTheme.colorScheme.onBackground
        )
    )
}

@Composable
fun NavigationItemLabel(
    icon: ImageVector,
    text: String,
    contentDescription: String? = null
) {
    Row(verticalAlignment = CenterVertically) {
        Icon(imageVector = icon, contentDescription = contentDescription)
        Text(text = text)
    }
}

@Preview(group = "NavigationDrawer", name = "Navigation Drawer Light", apiLevel = 29, showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(group = "NavigationDrawer", name = "Navigation Drawer Dark", apiLevel = 29, showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun NavigationDrawerPreview() {
    ChooChooTheme {
        Surface(
            color = MaterialTheme.colorScheme.background
        ) {
            NavigationDrawer(
                navHostController = rememberNavController(),
                selectedNavButton = Destinations.SEARCH_CONNECTION_ROUTE,
                onNavigation = {},
                onNothing = {}
            )
        }
    }
}