package net.gloryx.pterodactyl

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.auth.*
import io.ktor.client.plugins.auth.providers.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.resources.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.*
import kotlinx.serialization.*
import net.gloryx.pterodactyl.routes.ListServers
import net.gloryx.pterodactyl.routes.Route
import net.gloryx.pterodactyl.routes.account.AccountDetails
import net.gloryx.pterodactyl.struct.Server
import net.gloryx.pterodactyl.struct.User
import catx.ktormagic.ratelimit.Ratelimit
import io.ktor.client.request.*

@OptIn(PterodactylInternalAPI::class)
class PterodactylAPI private constructor(val endpoint: Url, private val apiKey: String) {
    @PterodactylInternalAPI
    val http = getHttpClient { make() }

    private fun DefaultHttpClientConfig.make() {
        install(Resources)
        install(DefaultRequest) {
            url {
                this.host = endpoint.host
                this.port = endpoint.port
                this.path("api", "client")
            }
        }
        install(Auth) {
            bearer {
                loadTokens {
                    BearerTokens(apiKey, "")
                }
            }
        }
        install(ContentNegotiation) {
            serialization(ContentType.Application.Json, PterodactylJson)
        }
        install(Ratelimit)
    }

    private suspend fun test() {
        try {
            http.get(AccountDetails()).takeUnless { it.status != HttpStatusCode.Unauthorized }
                ?.body<PterodactylObject.Attributes<User>>() ?: throw invalidApiKey()
        } catch (_: SerializationException) {
            throw invalidApiKey()
        } catch (e: IllegalArgumentException) {
            throw e
        }
    }

    private suspend inline fun <reified Ret, reified Req : Route.Get<Ret>> get(
        request: Req, configure: HttpRequestBuilder.() -> Unit = {}
    ): Ret =
        http.get(request) {
            configure()
        }.body()

    private suspend inline fun <reified Ret, reified Body, reified Req : Route.Post<Body, Ret>> post(
        request: Req, data: Body, configure: HttpRequestBuilder.() -> Unit = {}
    ): Ret = http.post(request) {
        setBody(data)
        configure()
    }.body()

    suspend fun account(): User = get(AccountDetails()).attributes

    suspend fun servers(): List<Server> = get(ListServers()).data

    companion object {
        suspend operator fun invoke(endpoint: Url, apiKey: String) = PterodactylAPI(endpoint, apiKey).also { it.test() }
    }
}

private inline fun invalidApiKey() = IllegalArgumentException("The provided API key was invalid!")

internal expect fun getHttpClient(config: HttpClientConfig<out HttpClientEngineConfig>.() -> Unit): HttpClient