package dev.garousi.stock_watcher.feature.watchlist.presentation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.material.shimmer
import com.google.accompanist.placeholder.placeholder
import dev.garousi.stock_watcher.feature.watchlist.domain.models.Stock

@Composable
fun StockList(
    stocks: List<Stock>,
    onClick: (Stock) -> Unit = {}
) {

    val isLoading = remember(stocks) {
        stocks.firstOrNull { it.last.isNaN() } == null
    }
    val placeholderModifier = Modifier.placeholder(
        visible = true,
        highlight = PlaceholderHighlight.shimmer(),
        color = Color(0X33CCDAFF)
    )
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .testTag(stockListItems),
        contentPadding = PaddingValues(16.dp)
    ) {
        itemsIndexed(
            items = stocks,
            key = { index, item -> item.itemName }
        ) { index, stock ->
            StockItem(
                stock = stock,
                index = index,
                modifier = placeholderModifier,
                onClick = onClick
            )
        }
    }
}


const val stockListItems = "StockListItems"
