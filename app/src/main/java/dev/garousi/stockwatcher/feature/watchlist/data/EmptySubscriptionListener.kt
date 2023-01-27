@file:Suppress("TooManyFunctions")
package dev.garousi.stockwatcher.feature.watchlist.data

import com.lightstreamer.client.ItemUpdate
import com.lightstreamer.client.Subscription
import com.lightstreamer.client.SubscriptionListener

object EmptySubscriptionListener : SubscriptionListener {
    override fun onClearSnapshot(itemName: String?, itemPos: Int) = Unit

    override fun onCommandSecondLevelItemLostUpdates(lostUpdates: Int, key: String) = Unit

    override fun onCommandSecondLevelSubscriptionError(code: Int, message: String?, key: String?) = Unit

    override fun onEndOfSnapshot(itemName: String?, itemPos: Int) = Unit

    override fun onItemLostUpdates(itemName: String?, itemPos: Int, lostUpdates: Int) = Unit

    override fun onItemUpdate(itemUpdate: ItemUpdate) = Unit

    override fun onListenEnd(subscription: Subscription) = Unit

    override fun onListenStart(subscription: Subscription) = Unit

    override fun onSubscription() = Unit

    override fun onSubscriptionError(code: Int, message: String?) = Unit

    override fun onUnsubscription() = Unit

    override fun onRealMaxFrequency(frequency: String?) = Unit
}
