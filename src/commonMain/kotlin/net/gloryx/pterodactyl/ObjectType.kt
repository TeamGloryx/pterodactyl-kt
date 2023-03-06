package net.gloryx.pterodactyl

import kotlinx.serialization.*
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

@Serializable(ObjectType.Companion::class)
enum class ObjectType {
    User;

    companion object : KSerializer<ObjectType> {
        override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("pterodactyl.object_type", PrimitiveKind.STRING)
        override fun deserialize(decoder: Decoder): ObjectType = ObjectType.valueOf(decoder.decodeString().uppercase())
        override fun serialize(encoder: Encoder, value: ObjectType) = encoder.encodeString(value.name.lowercase())
    }
}