package dev.garousi.stock_watcher.feature.watchlist.presentation

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.hasScrollToKeyAction
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performScrollToKey
import androidx.compose.ui.test.printToLog
import dev.garousi.stock_watcher.feature.watchlist.domain.models.Stock
import dev.garousi.stock_watcher.ui.theme.StockWatcherTheme
import java.time.LocalTime
import java.util.UUID
import org.junit.Rule
import org.junit.Test

class WatchlistScreenTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun circularProgressIndicator_whenScreenIsLoading_exists() {
        with(composeTestRule) {
            setContent {
                StockWatcherTheme(true) {
                    WatchlistScreen(
                        stocks = emptyList(),
                        isLoading = true
                    )
                }
            }
        }
        composeTestRule.mainClock.autoAdvance = false
        composeTestRule.onNodeWithTag(watchlistStocksLoadingWheel, useUnmergedTree = true)
            .printToLog("LOGGER2")
        composeTestRule
            .onNodeWithTag(
                testTag = watchlistStocksLoadingWheel,
                useUnmergedTree = true
            )
            .assertExists()
    }

    @Test
    fun stocks_whenHasStocks_showStocks() {
        with(composeTestRule) {
            setContent {
                StockWatcherTheme(true) {
                    WatchlistScreen(
                        stocks = testStocks,
                        isLoading = false
                    )
                }
            }
        }
        testStocks.take(5).forEachIndexed { actualStockItemIndex, actualStockItem ->
            with(composeTestRule) {
                assertCardIsDisplayed(actualStockItemIndex)
                assertNameIsDisplayed(actualStockItemIndex, actualStockItem)
                assertLastPriceIsDisplayed(actualStockItemIndex, actualStockItem)
                assertPriceChangeIsDisplayed(actualStockItemIndex, actualStockItem)
                assertArrowIsDisplayed(actualStockItemIndex)
            }
        }

        composeTestRule
            .onNodeWithTag(testTag = StockItemTestTags.card + 5, useUnmergedTree = true)
            .assertExists()
            .assertHasClickAction()

        composeTestRule.onNode(hasScrollToKeyAction())
            .performScrollToKey(testStocks[11].itemName)

        composeTestRule
            .onNodeWithTag(testTag = StockItemTestTags.card + 12, useUnmergedTree = true)
            .assertExists()
            .assertHasClickAction()
    }

    @Test
    fun stocks_whenHasNoStocks_showsEmptyState() {
        with(composeTestRule) {
            setContent {
                StockWatcherTheme(true) {
                    WatchlistScreen(
                        stocks = emptyList(),
                        isLoading = false
                    )
                }
            }
            onNodeWithTag(
                testTag = watchlistStocksEmpty,
                useUnmergedTree = true
            ).assertExists()
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