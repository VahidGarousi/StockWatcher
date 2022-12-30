package dev.garousi.stock_watcher.feature.watchlist.presentation

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performScrollToIndex
import dev.garousi.stock_watcher.feature.watchlist.domain.models.Stock
import dev.garousi.stock_watcher.feature.watchlist.domain.repository.stocks
import dev.garousi.stock_watcher.ui.theme.StockWatcherTheme
import java.time.LocalTime
import java.util.UUID
import org.junit.Rule
import org.junit.Test


class StockListTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun stocks_whenHasStocks_showsStocks() {
        with(composeTestRule) {
            setContent {
                StockWatcherTheme(true) {
                    StockList(stocks = stocks)
                }
            }
            stocks.forEachIndexed { index, item ->
                assertCardIsDisplayed(index)
                assertNameIsDisplayed(index, item)
                assertLastPriceIsDisplayed(index, item)
                assertPriceChangeIsDisplayed(index, item)
                assertArrowIsDisplayed(index)
            }
        }
    }

    @Test
    fun stocks_whenHasNoStocks_showsNothing() {
        with(composeTestRule) {
            setContent {
                StockWatcherTheme(true) {
                    StockList(stocks = emptyList())
                }
            }
            onNodeWithTag(watchlistStocks).assertDoesNotExist()
        }
    }

    @Test
    fun assertStockListScrollAction() {
        val stocks = testStocks
        var canScroll: Boolean
        repeat(200) { index ->
            val last = if (index % 2 == 0) 1250.0 else (0.5 * index)
            val change = if (index % 2 == 0) 1.5 else (-0.8 * index)
            val bidSize = if (index % 2 == 0) 12_300.0 else (100.0 * index)
            val bid = if (index % 2 == 0) 12.0 else (44_0 * index).toDouble()
            val ask = if (index % 2 == 0) 9.0 else (4_0 * index).toDouble()
            val askSize = if (index % 2 == 0) 1240.0 else (4230 * index).toDouble()
            val min = if (index % 2 == 0) 999.0 else (499 * index).toDouble()
            val max = if (index % 2 == 0) 1580.0 else (480 * index).toDouble()
            val ref = if (index % 2 == 0) 124.0 else (484 * index).toDouble()
            val open = if (index % 2 == 0) 101.0 else (421 * index).toDouble()
            stocks.add(
                Stock(
                    name = "Apple $index",
                    itemName = UUID.randomUUID().toString(),
                    last = last,
                    time = LocalTime.now(),
                    change = change,
                    bidSize = bidSize,
                    bid = bid,
                    ask = ask,
                    askSize = askSize,
                    min = min,
                    max = max,
                    ref = ref,
                    open = open
                )
            )
        }
        with(composeTestRule) {
            setContent {
                StockWatcherTheme(true) {
                    StockList(stocks = stocks)
                }
            }
            val visibleItemsSize = getVisibleStockListSize(watchlistStocks)
            var currentIndex = visibleItemsSize
            repeat(visibleItemsSize) { index ->
                assertCardIsDisplayed(index)
            }
            canScroll = currentIndex < stocks.size
            while (canScroll) {
                if (currentIndex <= stocks.size - 1) {
                    if (currentIndex < stocks.size - visibleItemsSize) {
                        currentIndex += visibleItemsSize
                    } else {
                        currentIndex = stocks.lastIndex
                    }
                    onNodeWithTag(watchlistStocks).performScrollToIndex(currentIndex)
                    assertCardIsDisplayed(currentIndex)
                }
                canScroll = currentIndex < stocks.size - 1
            }
        }
    }

}


private val testStocks = arrayListOf<Stock>().apply {
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
            open = 1010.0
        )
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
            open = 1710.0
        )
    )
    add(
        Stock(
            name = "CVS Asia",
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
            open = 1710.0
        )
    )
    add(
        Stock(
            name = "Datio PLC",
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
            open = 1710.0
        )
    )
    add(
        Stock(
            name = "MED",
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
            open = 1710.0
        )
    )
    add(
        Stock(
            name = "Dentemes",
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
            open = 1710.0
        )
    )
    add(
        Stock(
            name = "Datio PLC",
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
            open = 1710.0
        )
    )
    add(
        Stock(
            name = "ELE",
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
            open = 1710.0
        )
    )
    add(
        Stock(
            name = "Lted",
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
            open = 1710.0
        )
    )
    add(
        Stock(
            name = "Corcor",
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
            open = 1710.0
        )
    )
    add(
        Stock(
            name = "Excat Tom",
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
            open = 1710.0
        )
    )
    add(
        Stock(
            name = "PRess",
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
            open = 1710.0
        )
    )
    add(
        Stock(
            name = "News 2",
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
            open = 1710.0
        )
    )
    add(
        Stock(
            name = "IGTV",
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
            open = 1710.0
        )
    )
}