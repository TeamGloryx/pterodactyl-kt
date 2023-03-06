package net.gloryx.pterodactyl

import io.ktor.client.*
import io.ktor.client.engine.*
import io.ktor.client.engine.darwin.*

internal actual fun getHttpClient(config: HttpClientConfig<out HttpClientEngineConfig>.() -> Unit) = HttpClient(Darwin) {
    engine {
        // Серёга, помоги
    }

    config()
}