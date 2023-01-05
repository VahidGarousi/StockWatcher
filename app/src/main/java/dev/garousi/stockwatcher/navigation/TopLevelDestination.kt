package dev.garousi.stockwatcher.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.Home
import dev.garousi.stockwatcher.R

enum class TopLevelDestination(
    val selectedStockWatcherIcons: StockWatcherIcons,
    val unselectedStockWatcherIcons: StockWatcherIcons,
    val iconTextId: Int,
    val titleTextId: Int,
) {
    WATCHLIST(
        selectedStockWatcherIcons = StockWatcherIcons.ImageVectorStockWatcherIcons(Icons.Default.Home),
        unselectedStockWatcherIcons = StockWatcherIcons.ImageVectorStockWatcherIcons(Icons.Outlined.Home),
        iconTextId = R.string.home,
        titleTextId = R.string.home,
    ),
}
