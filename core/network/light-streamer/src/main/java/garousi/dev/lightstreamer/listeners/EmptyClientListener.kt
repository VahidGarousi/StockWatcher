package garousi.dev.lightstreamer.listeners

import com.lightstreamer.client.ClientListener
import com.lightstreamer.client.LightstreamerClient

object EmptyClientListener : ClientListener {
    override fun onListenStart(client: LightstreamerClient) = Unit
    override fun onListenEnd(client: LightstreamerClient) = Unit
    override fun onServerError(errorCode: Int, errorMessage: String) = Unit
    override fun onStatusChange(status: String) = Unit
    override fun onPropertyChange(property: String) = Unit
}
