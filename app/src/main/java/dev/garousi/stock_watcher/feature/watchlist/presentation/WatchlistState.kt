package dev.garousi.stock_watcher.feature.watchlist.presentation

import dev.garousi.stock_watcher.feature.watchlist.domain.models.Stock

sealed interface WatchlistState {
    object Loading : WatchlistState
    data class Success(val stocks: List<Stock>) : WatchlistState
    object Error : WatchlistState
}