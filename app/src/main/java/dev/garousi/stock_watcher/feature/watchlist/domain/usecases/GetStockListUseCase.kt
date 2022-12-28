package dev.garousi.stock_watcher.feature.watchlist.domain.usecases

import dev.garousi.stock_watcher.feature.watchlist.domain.repository.StockRepository
import dev.garousi.stock_watcher.feature.watchlist.presentation.WatchlistState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart

class GetStockListUseCase constructor(
    private val repository: StockRepository
) {
    operator fun invoke(): Flow<WatchlistState> {
        return repository.getStocks()
            .onStart {
                WatchlistState.Loading
            }
            .catch { _: Throwable ->
                WatchlistState.Error
            }
            .map {
                WatchlistState.Success(it)
            }
    }
}
