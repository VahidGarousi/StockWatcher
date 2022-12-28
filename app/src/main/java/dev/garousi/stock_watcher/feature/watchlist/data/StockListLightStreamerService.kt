package dev.garousi.stock_watcher.feature.watchlist.data

import android.util.Log
import com.lightstreamer.client.ItemUpdate
import com.lightstreamer.client.Subscription
import com.lightstreamer.client.SubscriptionListener
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class StockListLightStreamerService @Inject constructor(
    private val connection: LightStreamerConnection
) : LightStreamerService<StockListDto> {

    private val scope = CoroutineScope(SupervisorJob())
    private var dto = StockListDto()
    private val _flow: MutableStateFlow<StockListDto> = MutableStateFlow(StockListDto())
    override val flow = _flow.asStateFlow()

    init {
        connection.connect(serverAddress = serverAddress, adapterSet = adapterSet)
    }

    companion object {
        const val serverAddress = "https://push.lightstreamer.com:443"
        const val adapterSet = "DEMO"

        internal const val LAST_PRICE = "last_price"
        internal const val TIME = "time"
        internal const val PCT_CHANGE = "pct_change"
        internal const val BID_QUANTITY = "bid_quantity"
        internal const val BID = "bid"
        internal const val ASK = "ask"
        internal const val ASK_QUANTITY = "ask_quantity"
        internal const val MIN = "min"
        internal const val MAX = "max"
        internal const val REF_PRICE = "ref_price"
        internal const val OPEN_PRICE = "open_price"
        internal const val STOCK_NAME = "stock_name"
        internal const val ITEM_STATUS = "item_status"
        val itemNames = arrayOf(
            "item1",
            "item2",
            "item3",
            "item4",
            "item5",
            "item6",
            "item7",
            "item8",
            "item9",
            "item10",
            "item11",
            "item12",
            "item13",
            "item14",
            "item15"
        )
        val fieldNames = arrayOf(
            LAST_PRICE,
            TIME,
            PCT_CHANGE,
            BID_QUANTITY,
            BID,
            ASK,
            ASK_QUANTITY,
            MIN,
            MAX,
            REF_PRICE,
            OPEN_PRICE,
            STOCK_NAME,
            ITEM_STATUS,
        )
    }

    override fun subscribe(vararg params: Any): LightStreamerService<StockListDto> {
        observeSubscriptionUpdates()
        connection.subscribe(
            subscriptionMode = SubscriptionMode.Merge,
            itemNames = itemNames,
            fieldNames = fieldNames,
            dataAdapter = "QUOTE_ADAPTER",
            requestedSnapshot = "yes",
            requestedMaxFrequency = "1"
        )
        return this
    }


    override fun observeSubscriptionUpdates() {
        this.connection.subscriptionListener =
            object : SubscriptionListener by EmptySubscriptionListener {
                override fun onItemUpdate(itemUpdate: ItemUpdate) {
                    setFieldsToDto(itemUpdate)
                }

                override fun onListenStart(subscription: Subscription) {
                    Log.i("LOGGER", "")
                }

                override fun onListenEnd(subscription: Subscription) {
                    Log.i("LOGGER", "")
                }

                override fun onSubscription() {
                    Log.i("LOGGER", "")
                }

                override fun onSubscriptionError(code: Int, message: String?) {
                    Log.i("LOGGER", "")
                }

                override fun onUnsubscription() {
                    Log.i("LOGGER", "")
                }
            }
    }

    override fun setFieldsToDto(itemUpdate: ItemUpdate) {
        val name = itemUpdate.getValue(STOCK_NAME)
        val change = itemUpdate.getValue(PCT_CHANGE)?.toDouble() ?: dto.change
        val bidSize = itemUpdate.getValue(BID_QUANTITY)?.toDouble() ?: dto.bidSize
        val bid = itemUpdate.getValue(BID)?.toDouble() ?: dto.bid
        val last = itemUpdate.getValue(LAST_PRICE)?.toDouble() ?: dto.last
        val ask = itemUpdate.getValue(ASK)?.toDouble() ?: dto.ask
        val askSize = itemUpdate.getValue(ASK_QUANTITY)?.toDouble() ?: dto.askSize
        val min = itemUpdate.getValue(MIN)?.toDouble() ?: dto.min
        val max = itemUpdate.getValue(MAX)?.toDouble() ?: dto.max
        val ref = itemUpdate.getValue(REF_PRICE)?.toDouble() ?: dto.ref
        val open = itemUpdate.getValue(OPEN_PRICE)?.toDouble() ?: dto.open
        val stringTime = itemUpdate.getValue(TIME)
        val time =  LocalTime.parse(stringTime)  ?: dto.time
        val itemName = itemUpdate.itemName
        val itemPos = itemUpdate.itemPos
        dto = dto.copy(
            name = name,
            itemName = itemName,
            itemPos = itemPos,
            last = last,
            time = time,
            change = change,
            bidSize = bidSize,
            bid = bid,
            ask = ask,
            askSize = askSize,
            min = min,
            max = max,
            ref = ref,
            open = open,
        )
        scope.launch(Dispatchers.IO) {
            _flow.emit(dto)
        }
    }

    override fun unsubscribe() {
        connection.unsubscribe()
        scope.cancel()
    }
}