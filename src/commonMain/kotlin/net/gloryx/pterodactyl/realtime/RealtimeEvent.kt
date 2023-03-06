package net.gloryx.pterodactyl.realtime

import kotlinx.serialization.Serializable
import net.gloryx.pterodactyl.struct.Signal

@Serializable
data class RealtimeEvent(val event: String, val args: List<String?>?) {
    companion object {
        fun auth(token: String) = RealtimeEvent("auth", listOf(token))
        fun getStats() = RealtimeEvent("send stats", listOf(null))
        fun getLogs() = RealtimeEvent("send logs", listOf(null))
        fun power(signal: Signal) = RealtimeEvent("set state", listOf(signal.name.lowercase()))
    }
}