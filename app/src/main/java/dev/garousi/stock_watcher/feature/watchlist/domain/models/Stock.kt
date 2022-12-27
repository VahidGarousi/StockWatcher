package dev.garousi.stock_watcher.feature.watchlist.domain.models

import java.time.LocalTime

data class Stock(
    val id: String,
    val last: Long,
    val time: LocalTime,
    val change: Long,
    val bidSize: Long,
    val bid: Long,
    val ask: Long,
    val askSize: Long,
    val min: Long,
    val max: Long,
    val ref: Long,
    val open: Long
)
