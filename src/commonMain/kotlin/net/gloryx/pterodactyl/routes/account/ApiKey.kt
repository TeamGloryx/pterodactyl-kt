package net.gloryx.pterodactyl.routes.account

import io.ktor.resources.*
import net.gloryx.pterodactyl.PterodactylInternalAPI

@PterodactylInternalAPI
@Resource("/api/client/account/api-keys")
class ApiKey

@PterodactylInternalAPI
@Resource("/api/client/account/api-keys/{identifier}")
data class DeleteApiKey(val identifier: String)