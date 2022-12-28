package dev.garousi.stock_watcher.feature.watchlist.domain.repository

import dev.garousi.stock_watcher.feature.watchlist.domain.models.Stock
import kotlinx.coroutines.flow.Flow

interface StockRepository {
    fun getStocks(): Flow<List<Stock>>
}