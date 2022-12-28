package dev.garousi.stock_watcher.feature.watchlist.presentation

import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.junit4.ComposeTestRule
import androidx.compose.ui.test.onChildren
import androidx.compose.ui.test.onNodeWithTag
import dev.garousi.stock_watcher.feature.watchlist.domain.models.Stock


fun ComposeContentTestRule.assertArrowIsDisplayed(
    actualStockItemIndex: Int
) = onNodeWithTag(StockItemTestTags.arrow + actualStockItemIndex, true)
    .assertIsDisplayed()

fun ComposeContentTestRule.assertPriceChangeIsDisplayed(
    actualStockItemIndex: Int,
    actualStockItem: Stock
) {
    val expectedPriceChange =
        buildString { append("+").append(actualStockItem.change) }

    onNodeWithTag(
        StockItemTestTags.priceChange + actualStockItemIndex,
        true
    ).assertIsDisplayed().assertTextEquals(expectedPriceChange)
}

fun ComposeContentTestRule.assertLastPriceIsDisplayed(
    actualStockItemIndex: Int,
    actualStockItem: Stock
) {
    onNodeWithTag(
        StockItemTestTags.lastPrice + actualStockItemIndex,
        true
    ).assertIsDisplayed().assertTextEquals(actualStockItem.last.toString())
}

fun ComposeContentTestRule.assertNameIsDisplayed(
    actualStockItemIndex: Int,
    actualStockItem: Stock
) {
    onNodeWithTag(
        StockItemTestTags.name + actualStockItemIndex,
        true
    ).assertIsDisplayed().assertTextEquals(actualStockItem.name)
}

fun ComposeContentTestRule.assertCardIsDisplayed(
    actualStockItemIndex: Int
) = onNodeWithTag(StockItemTestTags.card + actualStockItemIndex).assertIsDisplayed()
    .assertHasClickAction()



fun ComposeTestRule.getVisibleStockListSize(testTag: String): Int {
    return onNodeWithTag(testTag).onChildren().fetchSemanticsNodes().size
}