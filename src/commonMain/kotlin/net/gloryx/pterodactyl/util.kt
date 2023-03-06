package net.gloryx.pterodactyl

import io.ktor.client.*
import io.ktor.client.engine.*
import kotlinx.serialization.*
import kotlinx.serialization.json.Json

internal typealias DefaultHttpClientConfig = HttpClientConfig<out HttpClientEngineConfig>

@PterodactylInternalAPI
@Serializable
sealed class PterodactylObject {
    @SerialName("object")
    abstract val obj: ObjectType

    @Serializable
    data class Attributes<A>(override val obj: ObjectType, val attributes: A) : PterodactylObject()

    data class Data<D>(override val obj: ObjectType, val data: D) : PterodactylObject()
}

@PterodactylInternalAPI
val PterodactylJson = Json {
    this.isLenient = true
    this.ignoreUnknownKeys = true
}