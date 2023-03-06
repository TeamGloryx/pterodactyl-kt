package net.gloryx.pterodactyl.routes.account

import io.ktor.resources.*
import kotlinx.serialization.*
import net.gloryx.pterodactyl.ObjectType
import net.gloryx.pterodactyl.PterodactylInternalAPI
import net.gloryx.pterodactyl.PterodactylObject
import net.gloryx.pterodactyl.routes.Route
import net.gloryx.pterodactyl.struct.User

@PterodactylInternalAPI
@Resource("/api/client/account")
class AccountDetails : Route.Get<PterodactylObject.Attributes<User>>