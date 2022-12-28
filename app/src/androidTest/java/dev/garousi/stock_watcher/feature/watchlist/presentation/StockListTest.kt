package dev.garousi.stock_watcher.feature.watchlist.presentation

import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onChildren
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
    fun assertStockListIsDisplayed() {
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
    fun assertStockListIsNotDisplayed() {
        with(composeTestRule) {
            setContent {
                StockWatcherTheme(true) {
                    StockList(stocks = emptyList())
                }
            }
            onNodeWithTag(stockListItems)
                .onChildren()
                .assertCountEquals(0)
        }
    }

    @Test
    fun assertStockListScrollAction() {
        val stocks = stocks
        var canScroll: Boolean
        repeat(200) { index ->
            val last = if (index % 2 == 0) 1250 else (0.5 * index).toLong()
            val change = if (index % 2 == 0) 1_5 else (-0.8 * index).toLong()
            val bidSize = if (index % 2 == 0) 12_300 else (100.0 * index).toLong()
            val bid = if (index % 2 == 0) 12_0 else (44_0 * index).toLong()
            val ask = if (index % 2 == 0) 9_0 else (4_0 * index).toLong()
            val askSize = if (index % 2 == 0) 1240 else (4230 * index).toLong()
            val min = if (index % 2 == 0) 999 else (499 * index).toLong()
            val max = if (index % 2 == 0) 1580 else (480 * index).toLong()
            val ref = if (index % 2 == 0) 124 else (484 * index).toLong()
            val open = if (index % 2 == 0) 101 else (421 * index).toLong()
            stocks.add(
                Stock(
                    name = "Apple $index",
                    id = UUID.randomUUID().toString(),
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
            val visibleItemsSize = getVisibleStockListSize(stockListItems)
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
                    onNodeWithTag(stockListItems).performScrollToIndex(currentIndex)
                    assertCardIsDisplayed(currentIndex)
                }
                canScroll = currentIndex < stocks.size - 1
            }
        }
    }

}