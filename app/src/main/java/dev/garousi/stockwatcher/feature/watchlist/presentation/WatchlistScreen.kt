@file:Suppress("FunctionNaming", "LongParameterList")

package dev.garousi.stockwatcher.feature.watchlist.presentation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material.AppBarDefaults
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.contentColorFor
import androidx.compose.material.primarySurface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import dev.garousi.stockwatcher.feature.watchlist.domain.models.Stock
import dev.garousi.stockwatcher.navigation.TrackScrollJank

@Composable
fun WatchlistScreen(
    viewModel: WatchlistViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsState()
    WatchlistScreen(
        stocks = uiState.stocks,
        isLoading = uiState.isLoading,
    )
}

@Composable
fun WatchlistScreen(
    stocks: List<Stock>,
    isLoading: Boolean,
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
    ) {
        WatchlistTopAppBar {
            Text(
                text = "Stock List",
                modifier = Modifier.align(CenterVertically),
                style = MaterialTheme.typography.h6,
            )
        }
        val state = rememberLazyGridState()
        TrackScrollJank(scrollableState = state, stateName = watchlistStocks)
        StockList(stocks = stocks)
    }
    AnimatedVisibility(
        visible = isLoading,
        enter = slideInVertically(
            initialOffsetY = { fullHeight -> -fullHeight },
        ) + fadeIn(),
        exit = slideOutVertically(
            targetOffsetY = { fullHeight -> -fullHeight },
        ) + fadeOut(),
    ) {
        StockWatcherOverlayLoadingWheel(
            modifier = Modifier.testTag(watchlistStocksLoadingWheel),
        )
    }
}

@Composable
fun StockWatcherOverlayLoadingWheel(
    modifier: Modifier = Modifier,
) {
    Surface(
        color = MaterialTheme.colors.background,
        modifier = modifier.fillMaxSize(),
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
        ) {
            CircularProgressIndicator(
                modifier = Modifier.align(Center),
                strokeWidth = 1.dp,
            )
        }
    }
}

@Composable
private fun WatchlistTopAppBar(
    modifier: Modifier = Modifier,
    backgroundColor: Color = MaterialTheme.colors.primarySurface,
    contentColor: Color = contentColorFor(backgroundColor),
    elevation: Dp = AppBarDefaults.TopAppBarElevation,
    contentPadding: PaddingValues = AppBarDefaults.ContentPadding,
    content: @Composable RowScope.() -> Unit,
) {
    Surface(
        color = backgroundColor,
        contentColor = contentColor,
        elevation = elevation,
        shape = RectangleShape,
        modifier = modifier,
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(contentPadding)
                .height(56.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = CenterVertically,
            content = content,
        )
    }
}
