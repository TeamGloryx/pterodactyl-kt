package net.gloryx.pterodactyl.routes

import io.ktor.resources.*
import kotlinx.serialization.Serializable
import net.gloryx.pterodactyl.ObjectType
import net.gloryx.pterodactyl.PterodactylInternalAPI
import net.gloryx.pterodactyl.PterodactylObject
import net.gloryx.pterodactyl.struct.Server

@PterodactylInternalAPI
@Resource("/api/client")
class ListServers : Route.Get<PterodactylObject.Data<List<Server>>>