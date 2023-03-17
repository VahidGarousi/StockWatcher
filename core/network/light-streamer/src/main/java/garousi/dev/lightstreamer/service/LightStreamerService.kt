package garousi.dev.lightstreamer.service

import com.lightstreamer.client.ItemUpdate
import kotlinx.coroutines.flow.StateFlow

interface LightStreamerService<T> {
    val stream: StateFlow<T>
    fun subscribe(vararg params: Any): LightStreamerService<T>
    fun unsubscribe()
    fun observeSubscriptionUpdates()
    fun setFieldsToDto(itemUpdate: ItemUpdate)
}
