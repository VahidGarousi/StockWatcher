package dev.garousi.stock_watcher.feature.watchlist.presentation

import dev.garousi.stock_watcher.feature.watchlist.domain.models.Stock

data class WatchlistState(
    val stocks: List<Stock> = arrayListOf<Stock>().apply {
        (1..16).forEach {
            add(Stock(itemName = "itemName$it"))
        }
    }
)