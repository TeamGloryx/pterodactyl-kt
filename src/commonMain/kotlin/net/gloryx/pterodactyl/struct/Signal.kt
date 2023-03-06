package net.gloryx.pterodactyl.struct

import kotlinx.serialization.*
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

@Serializable(Signal.Companion::class)
enum class Signal {
    START,
    STOP,
    RESTART,
    KILL;

    companion object : KSerializer<Signal> {
        override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("pterodactyl.Signal", PrimitiveKind.STRING)
        override fun deserialize(decoder: Decoder): Signal = Signal.valueOf(decoder.decodeString().uppercase())
        override fun serialize(encoder: Encoder, value: Signal) = encoder.encodeString(value.name.lowercase())
    }
}