@file:Suppress("FunctionNaming")
package dev.garousi.stockwatcher.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost

@Composable
fun StockWatcherNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    startDestination: String = watchlistGraphRoutePattern,
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier,
    ) {
        watchlistGraph {
        }
    }
}
