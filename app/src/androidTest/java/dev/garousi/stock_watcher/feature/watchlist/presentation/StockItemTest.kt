package dev.garousi.stock_watcher.feature.watchlist.presentation

import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import com.google.common.truth.Truth.assertThat
import dev.garousi.stock_watcher.feature.watchlist.domain.models.Stock
import dev.garousi.stock_watcher.feature.watchlist.domain.repository.stocks
import dev.garousi.stock_watcher.ui.theme.StockWatcherTheme
import org.junit.Rule
import org.junit.Test


class StockItemTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    private val actualStockItem = stocks.first()
    private val actualStockItemIndex = 0


    @Test
    fun assertStockIsDisplayed() {
        with(composeTestRule) {
            setContent {
                StockWatcherTheme {
                    StockItem(
                        stock = actualStockItem, index = 0
                    )
                }
            }
            assertCardIsDisplayed(actualStockItemIndex)
            assertNameIsDisplayed(actualStockItemIndex, actualStockItem)
            assertLastPriceIsDisplayed(actualStockItemIndex, actualStockItem)
            assertPriceChangeIsDisplayed(actualStockItem, actualStockItemIndex)
            assertArrowIsDisplayed(actualStockItemIndex)
        }
    }


    @Test
    fun clickOnStockItem() {
        var hasClickedOnStockItem = false
        with(composeTestRule) {
            setContent {
                StockWatcherTheme {
                    StockItem(
                        stock = actualStockItem,
                        index = 0,
                        onClick = {
                            hasClickedOnStockItem = true
                        }
                    )
                }
            }
            onNodeWithTag(StockItemTestTags.card + actualStockItemIndex).performClick()
            assertThat(hasClickedOnStockItem).isTrue()
        }
    }

    private fun ComposeContentTestRule.assertArrowIsDisplayed(
        actualStockItemIndex: Int
    ) = onNodeWithTag(StockItemTestTags.arrow + actualStockItemIndex, true)
        .assertIsDisplayed()

    private fun ComposeContentTestRule.assertPriceChangeIsDisplayed(
        actualStockItem: Stock,
        actualStockItemIndex: Int
    ) {
        val expectedPriceChange =
            buildString { append("+").append(" ").append(actualStockItem.change) }

        onNodeWithTag(
            StockItemTestTags.priceChange + actualStockItemIndex,
            true
        ).assertIsDisplayed().assertTextEquals(expectedPriceChange)
    }

    private fun ComposeContentTestRule.assertLastPriceIsDisplayed(
        actualStockItemIndex: Int,
        actualStockItem: Stock
    ) {
        onNodeWithTag(
            StockItemTestTags.lastPrice + actualStockItemIndex,
            true
        ).assertIsDisplayed().assertTextEquals(actualStockItem.last.toString())
    }

    private fun ComposeContentTestRule.assertNameIsDisplayed(
        actualStockItemIndex: Int,
        actualStockItem: Stock
    ) {
        onNodeWithTag(
            StockItemTestTags.name + actualStockItemIndex,
            true
        ).assertIsDisplayed().assertTextEquals(actualStockItem.name)
    }

    private fun ComposeContentTestRule.assertCardIsDisplayed(
        actualStockItemIndex: Int
    ) {
        onNodeWithTag(StockItemTestTags.card + actualStockItemIndex)
            .assertIsDisplayed()
            .assertHasClickAction()
    }
}