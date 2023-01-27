@file:Suppress("MagicNumber", "ForEachOnRange")
package dev.garousi.stockwatcher.feature.watchlist.presentation

import dev.garousi.stockwatcher.feature.watchlist.domain.models.Stock

data class WatchlistState(
    val stocks: List<Stock> = arrayListOf<Stock>().apply {
        (1..16).forEach {
            add(Stock(itemName = "itemName$it"))
        }
    },
    val isLoading: Boolean = false,
)
