package dev.garousi.stock_watcher.feature.watchlist.domain.usecases

import dev.garousi.stock_watcher.feature.watchlist.domain.repository.StockRepository
import dev.garousi.stock_watcher.feature.watchlist.presentation.WatchlistState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetStockListUseCase constructor(
    private val repository: StockRepository
) {
    operator fun invoke(): Flow<WatchlistState> {
        return  flow {  }
    }
}
