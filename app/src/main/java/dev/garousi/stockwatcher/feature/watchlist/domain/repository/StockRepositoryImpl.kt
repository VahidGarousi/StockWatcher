package dev.garousi.stockwatcher.feature.watchlist.domain.repository

import dev.garousi.stockwatcher.feature.watchlist.domain.models.Stock
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.time.LocalTime
import java.util.UUID
import javax.inject.Inject

class StockRepositoryImpl @Inject constructor() : StockRepository {
    override fun getStocks(): Flow<List<Stock>> {
        return flow { emit(stocks) }
    }
}

val stocks = arrayListOf<Stock>().apply {
    add(
        Stock(
            name = "Apple",
            itemName = UUID.randomUUID().toString(),
            last = 1250.0,
            time = LocalTime.now(),
            change = 1_5.0,
            bidSize = 12_300.0,
            bid = 12_0.0,
            ask = 9_0.0,
            askSize = 1240.0,
            min = 999.0,
            max = 1580.0,
            ref = 124.0,
            open = 1010.0,
        ),
    )
    add(
        Stock(
            name = "Tesla",
            itemName = UUID.randomUUID().toString(),
            last = 1150.0,
            time = LocalTime.now(),
            change = 1_5.0,
            bidSize = 12_300.0,
            bid = 12_0.0,
            ask = 9_0.0,
            askSize = 1240.0,
            min = 999.0,
            max = 1580.0,
            ref = 124.0,
            open = 1710.0,
        ),
    )
}
