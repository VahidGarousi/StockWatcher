@file:Suppress("FunctionNaming")
package dev.garousi.stockwatcher.feature.watchlist.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import dev.garousi.stockwatcher.R
import dev.garousi.stockwatcher.feature.watchlist.domain.models.Stock

@Composable
fun StockList(
    modifier: Modifier = Modifier,
    stocks: List<Stock>,
    onClick: (Stock) -> Unit = {},
) {
    if (stocks.isNotEmpty()) {
        LazyColumn(
            modifier = modifier
                .fillMaxWidth()
                .testTag(watchlistStocks),
            contentPadding = PaddingValues(16.dp),
        ) {
            itemsIndexed(
                items = stocks,
                key = { index, item -> item.itemName },
            ) { index, stock ->
                StockItem(
                    stock = stock,
                    index = index,
                    onClick = onClick,
                )
            }
        }
    } else {
        EmptyState(modifier = modifier)
    }
}

@Composable
private fun EmptyState(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .testTag(watchlistStocksEmpty),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            modifier = Modifier.fillMaxWidth(),
            imageVector = Icons.Default.ShoppingCart,
            contentDescription = null,
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = stringResource(id = R.string.stocks_empty_error),
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.body1,
            fontWeight = FontWeight.Bold,
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = stringResource(id = R.string.stocks_empty_description),
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.body2,
        )
    }
}

const val watchlistStocks = "watchlist:stocks"
const val watchlistStocksEmpty = "watchlist:stocks:empty"
const val watchlistStocksLoadingWheel = "watchlist:loadingWheel"
