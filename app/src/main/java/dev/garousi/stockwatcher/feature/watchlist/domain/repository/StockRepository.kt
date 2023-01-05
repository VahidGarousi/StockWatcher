package dev.garousi.stockwatcher.feature.watchlist.domain.repository

import dev.garousi.stockwatcher.feature.watchlist.domain.models.Stock
import kotlinx.coroutines.flow.Flow

interface StockRepository {
    fun getStocks(): Flow<List<Stock>>
}
