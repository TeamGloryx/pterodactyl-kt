@file:OptIn(PterodactylInternalAPI::class)

package net.gloryx.pterodactyl.realtime

import io.ktor.client.plugins.websocket.*
import io.ktor.http.*
import io.ktor.serialization.*
import kotlinx.coroutines.channels.consumeEach
import net.gloryx.pterodactyl.PterodactylAPI
import net.gloryx.pterodactyl.PterodactylInternalAPI
import net.gloryx.pterodactyl.routes.servers.ServerWebsocket
@OptIn(PterodactylInternalAPI::class)
class RealtimeServer private constructor(private val api: PterodactylAPI, private val sock: ServerWebsocket.WebsocketData) {
    private lateinit var session: DefaultClientWebSocketSession

    private suspend fun init() {
        session = api.http.webSocketSession(HttpMethod.Post, sock.socket)

        session.sendSerialized(RealtimeEvent.auth(sock.token))

        for (f in session.incoming) {
            try {
                val event = session.converter!!.deserialize<RealtimeEvent>(f)
            } catch (_: Exception) {
                // ignored
            }
        }
    }

    companion object {

    }
}