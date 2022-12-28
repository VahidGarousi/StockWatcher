package dev.garousi.stock_watcher.feature.watchlist.presentation

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import com.google.common.truth.Truth.assertThat
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
                StockWatcherTheme(true) {
                    StockItem(
                        stock = actualStockItem, index = 0
                    )
                }
            }
            assertCardIsDisplayed(actualStockItemIndex)
            assertNameIsDisplayed(actualStockItemIndex, actualStockItem)
            assertLastPriceIsDisplayed(actualStockItemIndex, actualStockItem)
            assertPriceChangeIsDisplayed(actualStockItemIndex, actualStockItem)
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
}