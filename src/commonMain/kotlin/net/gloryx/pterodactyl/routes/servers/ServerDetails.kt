package net.gloryx.pterodactyl.routes.servers

import io.ktor.resources.*
import net.gloryx.pterodactyl.PterodactylInternalAPI
import net.gloryx.pterodactyl.PterodactylObject
import net.gloryx.pterodactyl.routes.Route
import net.gloryx.pterodactyl.struct.Server

@PterodactylInternalAPI
@Resource("/api/client/servers/{server}")
class ServerDetails(val server: String) : Route.Get<PterodactylObject.Attributes<Server>>