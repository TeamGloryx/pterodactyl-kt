package net.gloryx.pterodactyl.routes.servers

import io.ktor.resources.*
import kotlinx.serialization.Serializable
import net.gloryx.pterodactyl.PterodactylInternalAPI
import net.gloryx.pterodactyl.routes.Route

@PterodactylInternalAPI
@Resource("/api/client/servers/{server}/websocket")
class ServerWebsocket(val server: String) : Route.Get<ServerWebsocket.Response> {
    @Serializable
    data class Response(val data: WebsocketData)

    @Serializable
    data class WebsocketData(val token: String, val socket: String)
}