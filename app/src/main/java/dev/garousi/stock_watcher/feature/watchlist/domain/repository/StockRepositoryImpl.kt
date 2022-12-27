package dev.garousi.stock_watcher.feature.watchlist.domain.repository

import dev.garousi.stock_watcher.feature.watchlist.domain.models.Stock
import java.time.LocalTime
import java.util.UUID
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class StockRepositoryImpl @Inject constructor(

) : StockRepository {
    override fun getStocks(): Flow<List<Stock>> {
        return flow { emit(stocks) }
    }
}

val stocks = arrayListOf<Stock>().apply {
    add(
        Stock(
            id = UUID.randomUUID().toString(),
            last = 1250,
            time = LocalTime.now(),
            change = 1_5,
            bidSize = 12_300,
            bid = 12_0,
            ask = 9_0,
            askSize = 1240,
            min = 999,
            max = 1580,
            ref = 124,
            open = 1010
        )
    )
    add(
        Stock(
            id = UUID.randomUUID().toString(),
            last = 1150,
            time = LocalTime.now(),
            change = 1_5,
            bidSize = 12_300,
            bid = 12_0,
            ask = 9_0,
            askSize = 1240,
            min = 999,
            max = 1580,
            ref = 124,
            open = 1710
        )
    )
}