package net.gloryx.pterodactyl.struct

import kotlinx.serialization.*

@Serializable
data class User(
    val id: Long,
    val admin: Boolean,
    val username: String,
    val email: String,
    @SerialName("first_name")
    val firstName: String,
    @SerialName("last_name")
    val lastName: String,
    val language: String
)