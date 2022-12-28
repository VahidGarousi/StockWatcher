package dev.garousi.stock_watcher.feature.watchlist.domain.models

import java.time.LocalTime

data class Stock(
    val itemName: String = "",
    val name: String = "",
    val last: Double = Double.NaN,
    val time: LocalTime = LocalTime.now(),
    val change: Double = Double.NaN,
    val bidSize: Double = Double.NaN,
    val bid: Double = Double.NaN,
    val ask: Double = Double.NaN,
    val askSize: Double = Double.NaN,
    val min: Double = Double.NaN,
    val max: Double = Double.NaN,
    val ref: Double = Double.NaN,
    val open: Double = Double.NaN
)
