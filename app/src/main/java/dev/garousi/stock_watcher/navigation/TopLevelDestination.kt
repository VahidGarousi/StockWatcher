package dev.garousi.stock_watcher.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.Home
import dev.garousi.stock_watcher.R

enum class TopLevelDestination(
    val selectedIcon: Icon,
    val unselectedIcon: Icon,
    val iconTextId: Int,
    val titleTextId: Int
) {
    WATCHLIST(
        selectedIcon = Icon.ImageVectorIcon(Icons.Default.Home),
        unselectedIcon = Icon.ImageVectorIcon(Icons.Outlined.Home),
        iconTextId = R.string.home,
        titleTextId = R.string.home,
    )
}