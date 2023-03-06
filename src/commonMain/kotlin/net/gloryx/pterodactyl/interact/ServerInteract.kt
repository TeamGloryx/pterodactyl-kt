package net.gloryx.pterodactyl.interact

import net.gloryx.pterodactyl.PterodactylAPI
import net.gloryx.pterodactyl.PterodactylInternalAPI
import net.gloryx.pterodactyl.routes.servers.ServerPower
import net.gloryx.pterodactyl.routes.servers.ServerWebsocket
import net.gloryx.pterodactyl.struct.Server
import net.gloryx.pterodactyl.struct.Signal

@OptIn(PterodactylInternalAPI::class)
class ServerInteract(override val api: PterodactylAPI, private val identifier: String) : AbstractInteract(api) {
    suspend fun websocket() = get(ServerWebsocket(identifier))
    suspend fun power(signal: Signal) = post(ServerPower(identifier), ServerPower.Body(signal))
}


fun PterodactylAPI.interact(server: Server) = ServerInteract(this@PterodactylAPI, server.identifier)