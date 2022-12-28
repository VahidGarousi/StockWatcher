package dev.garousi.stock_watcher.feature.watchlist.data

import com.lightstreamer.client.ItemUpdate
import kotlinx.coroutines.flow.StateFlow


interface LightStreamerService<T> {
    val flow: StateFlow<T>
    fun subscribe(vararg params: Any): LightStreamerService<T>
    fun unsubscribe()
    fun observeSubscriptionUpdates()
    fun setFieldsToDto(itemUpdate: ItemUpdate)
}