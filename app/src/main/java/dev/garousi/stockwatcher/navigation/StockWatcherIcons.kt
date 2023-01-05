package dev.garousi.stockwatcher.navigation

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.vector.ImageVector

/**
 * A sealed class to make dealing with [ImageVector] and [DrawableRes] icons easier.
 */
sealed class StockWatcherIcons {
    data class ImageVectorStockWatcherIcons(val imageVector: ImageVector) : StockWatcherIcons()
    data class DrawableResourceStockWatcherIcons(@DrawableRes val id: Int) : StockWatcherIcons()
}
