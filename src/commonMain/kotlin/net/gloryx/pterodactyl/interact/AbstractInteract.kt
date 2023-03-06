package net.gloryx.pterodactyl.interact

import io.ktor.client.call.*
import io.ktor.client.plugins.resources.*
import io.ktor.client.request.*
import net.gloryx.pterodactyl.PterodactylAPI
import net.gloryx.pterodactyl.PterodactylInternalAPI
import net.gloryx.pterodactyl.routes.Route

@PterodactylInternalAPI
sealed class AbstractInteract(protected open val api: PterodactylAPI) {
    protected suspend inline fun <reified Response, reified Request : Route.Get<Response>> get(request: Request) =
        api.http.get(request).body<Response>()

    protected suspend inline fun <reified Response, reified Body, reified Request : Route.Post<Body, Response>> post(
        request: Request, body: Body, configure: HttpRequestBuilder.() -> Unit = {}
    ) = api.http.post(request) {
        setBody(body)
        configure()
    }
}
