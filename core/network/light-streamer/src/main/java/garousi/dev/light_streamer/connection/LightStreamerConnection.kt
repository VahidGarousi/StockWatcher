package garousi.dev.light_streamer.connection

import com.lightstreamer.client.LightstreamerClient
import com.lightstreamer.client.Subscription
import com.lightstreamer.client.SubscriptionListener
import garousi.dev.light_streamer.models.SubscriptionMode

interface LightStreamerConnection {
    val lsClient: LightstreamerClient?
    var subscriptionListener: SubscriptionListener?
    var subscription: Subscription?
    fun connect(serverAddress: String, adapterSet: String)
    fun disconnect()
    fun subscribe(
        subscriptionMode: SubscriptionMode,
        dataAdapter: String? = null,
        requestedSnapshot: String? = null,
        requestedMaxFrequency: String? = null,
        itemNames: Array<String>,
        fieldNames: Array<String>,
    ): Subscription?

    fun subscribe(
        subscriptionMode: SubscriptionMode,
        dataAdapter: String? = null,
        requestedSnapshot: String? = null,
        requestedMaxFrequency: String? = null,
        itemName: String,
        fieldNames: Array<String>,
    ): Subscription?

    fun unsubscribe()
    fun subscribeToClient()
    fun setSubscription(
        subscriptionMode: SubscriptionMode,
        itemNames: Array<String>,
        fieldNames: Array<String>,
    )

    fun setSubscription(
        subscriptionMode: SubscriptionMode,
        itemName: String,
        fieldNames: Array<String>,
    )

    fun setRequestedSnapshot(requestedSnapshot: String?)
    fun setRequestedMaxFrequency(requestedMaxFrequency: String?)
}