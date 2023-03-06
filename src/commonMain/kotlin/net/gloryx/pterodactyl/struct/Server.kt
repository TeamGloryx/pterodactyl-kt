@file:OptIn(PterodactylInternalAPI::class)

package net.gloryx.pterodactyl.struct

import kotlinx.serialization.Serializable

import kotlinx.serialization.SerialName
import net.gloryx.pterodactyl.ObjectType
import net.gloryx.pterodactyl.PterodactylInternalAPI


@Serializable
data class Server(
    @SerialName("server_owner")
    val isServerOwner: Boolean,
    val identifier: String,
    val uuid: String,
    val name: String,
    val node: String,
    @SerialName("sftp_details")
    val sftpDetails: SftpDetails,
    val description: String,
    val limits: Limits,
    @SerialName("feature_limits")
    val featureLimits: FeatureLimits,
    @SerialName("is_suspended")
    val isSuspended: Boolean,
    @SerialName("is_installing")
    val isInstalling: Boolean,
    val relationships: Relationships
) {
    @Serializable
    data class SftpDetails(
        val ip: String,
        val port: Int
    )

    @Serializable
    data class Limits(
        val memory: Int,
        val swap: Int,
        val disk: Int,
        val io: Int,
        val cpu: Int
    )

    @Serializable
    data class FeatureLimits(
        val databases: Int,
        val allocations: Int,
        val backups: Int
    )

    @Serializable
    data class Relationships(
        val allocations: Allocations
    ) {
        @Serializable
        data class Allocations(
            @SerialName("object")
            val obj: ObjectType,
            val data: List<Data>
        ) {
            @Serializable
            data class Data(
                @SerialName("object")
                val obj: ObjectType,
                val attributes: Attributes
            ) {
                @Serializable
                data class Attributes(
                    val id: Int,
                    val ip: String,
                    @SerialName("ip_alias")
                    val ipAlias: String,
                    val port: Int,
                    val notes: String,
                    @SerialName("is_default")
                    val isDefault: Boolean
                )
            }
        }
    }
}