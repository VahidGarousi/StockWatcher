package dev.garousi.stockwatcher.feature.watchlist.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.garousi.stockwatcher.feature.watchlist.data.LightStreamerService
import dev.garousi.stockwatcher.feature.watchlist.data.StockListDto
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class WatchlistViewModel @Inject constructor(
    private val stockListLightStreamerService: LightStreamerService<StockListDto>,
) : ViewModel() {

    private val _uiState = MutableStateFlow(WatchlistState())
    val uiState = _uiState.asStateFlow()

    init {
        _uiState.update { it.copy(isLoading = true) }
        subscribeToDemoAdapterSet()
    }

    private fun subscribeToDemoAdapterSet() {
        stockListLightStreamerService
            .subscribe()
            .flow
            .catch { cause: Throwable ->
                Log.i("LOGGER", "" + cause.localizedMessage.orEmpty())
            }
            .map { stock ->
                _uiState.update { it.copy(isLoading = false) }
                stock.itemPos?.let { updatedItemIndex ->
                    _uiState.update {
                        it.copy(
                            stocks = it.stocks.toMutableList().apply {
                                this[updatedItemIndex - 1] = this[updatedItemIndex - 1].copy(
                                    name = stock.name ?: it.stocks[updatedItemIndex].name,
                                    last = stock.last ?: it.stocks[updatedItemIndex].last,
                                    time = stock.time ?: it.stocks[updatedItemIndex].time,
                                    change = stock.change ?: it.stocks[updatedItemIndex].change,
                                    bidSize = stock.bidSize ?: it.stocks[updatedItemIndex].bidSize,
                                    bid = stock.bid ?: it.stocks[updatedItemIndex].bid,
                                    ask = stock.ask ?: it.stocks[updatedItemIndex].ask,
                                    askSize = stock.askSize ?: it.stocks[updatedItemIndex].askSize,
                                    min = stock.min ?: it.stocks[updatedItemIndex].min,
                                    max = stock.max ?: it.stocks[updatedItemIndex].max,
                                    ref = stock.ref ?: it.stocks[updatedItemIndex].ref,
                                    open = stock.open ?: it.stocks[updatedItemIndex].open,
                                )
                            },
                        )
                    }
                }
            }
            .stateIn(
                scope = viewModelScope,
                initialValue = StockListDto(),
                started = SharingStarted.Eagerly,
            )
    }
}
