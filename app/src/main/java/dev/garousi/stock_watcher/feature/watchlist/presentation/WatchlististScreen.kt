package dev.garousi.stock_watcher.feature.watchlist.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import dev.garousi.stock_watcher.feature.watchlist.domain.models.Stock

@Composable
fun Watchlist(
    viewModel: WatchlistViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    when (val state = uiState) {
        WatchlistState.Error -> ErrorView()
        WatchlistState.Loading -> LoadingView()
        is WatchlistState.Success -> Watchlist(state.stocks)
    }
}

@Composable
fun LoadingView() {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        CircularProgressIndicator(
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

@Composable
fun ErrorView() {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Text(text = "Error Happened", modifier = Modifier.align(Alignment.Center))
    }
}

@Composable
private fun Watchlist(
    stocks: List<Stock>
) {

}