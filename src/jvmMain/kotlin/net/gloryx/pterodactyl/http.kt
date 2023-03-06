package net.gloryx.pterodactyl

import io.ktor.client.*
import io.ktor.client.engine.*
import io.ktor.client.engine.okhttp.*
import okhttp3.Dns
import okhttp3.OkHttpClient
import kotlin.time.Duration.Companion.seconds
import kotlin.time.toJavaDuration

internal actual fun getHttpClient(config: HttpClientConfig<out HttpClientEngineConfig>.() -> Unit) =
    HttpClient(OkHttp) {
        engine {
            this.preconfigured = OkHttpClient.Builder()
                .connectTimeout(5.seconds.toJavaDuration())
                .build()
        }

        config()
    }