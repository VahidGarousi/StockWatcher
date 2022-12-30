package dev.garousi.stock_watcher.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import dev.garousi.stock_watcher.feature.watchlist.presentation.WatchlistScreen


const val watchlistGraphRoutePattern = "watchlist_graph"
const val watchlistListRoute = "stock_list_route"

fun NavController.navigateToWatchlistGraph(navOptions: NavOptions? = null) {
    this.navigate(watchlistGraphRoutePattern, navOptions)
}

fun NavGraphBuilder.watchlistGraph(
    nestedGraphs: NavGraphBuilder.() -> Unit
) {
    navigation(
        route = watchlistGraphRoutePattern,
        startDestination = watchlistListRoute
    ) {
        composable(route = watchlistListRoute) {
            WatchlistScreen()
        }
        nestedGraphs()
    }
}
