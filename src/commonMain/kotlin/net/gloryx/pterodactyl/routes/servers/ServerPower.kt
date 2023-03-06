package net.gloryx.pterodactyl.routes.servers

import io.ktor.resources.*
import kotlinx.serialization.Serializable
import net.gloryx.pterodactyl.PterodactylInternalAPI
import net.gloryx.pterodactyl.routes.Route
import net.gloryx.pterodactyl.struct.Signal

@PterodactylInternalAPI
@Resource("/api/client/servers/{server}/power")
class ServerPower(val server: String) : Route.Post<ServerPower.Body, Unit> {
    @Serializable
    data class Body(val signal: Signal)
}