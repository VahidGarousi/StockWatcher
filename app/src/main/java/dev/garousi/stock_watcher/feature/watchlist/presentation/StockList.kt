package dev.garousi.stock_watcher.feature.watchlist.presentation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import dev.garousi.stock_watcher.feature.watchlist.domain.models.Stock

@Composable
fun StockList(
    stocks: List<Stock>,
    onClick: (Stock) -> Unit = {}
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .testTag(stockListItems),
        contentPadding = PaddingValues(16.dp)
    ) {
        itemsIndexed(
            items = stocks,
            key = { index, item -> item.id }
        ) { index, stock ->
            StockItem(
                stock = stock,
                index = index,
                onClick = onClick
            )
        }
    }
}


const val stockListItems = "StockListItems"
