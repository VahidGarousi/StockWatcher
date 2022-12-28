package dev.garousi.stock_watcher.feature.watchlist.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.garousi.stock_watcher.feature.watchlist.domain.usecases.StockListUseCases
import javax.inject.Inject
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

@HiltViewModel
class WatchlistViewModel @Inject constructor(
    private val stockListUseCases: StockListUseCases
) : ViewModel() {
    val uiState = stockListUseCases.getStockList()
        .stateIn(
            scope = viewModelScope,
            initialValue = WatchlistState.Loading,
            started = SharingStarted.WhileSubscribed(5_000)
        )
}