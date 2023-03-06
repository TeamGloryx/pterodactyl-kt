rootProject.name = "pterodactyl-kt"

dependencyResolutionManagement {
    versionCatalogs {
        val libs by creating {
            fun ktor(plugin: String, alias: String = "ktor-$plugin", prefix: String = "ktor-client") =
                library(alias, "io.ktor", "$prefix-$plugin").versionRef("ktor").let { alias }

            bundle(
                "ktor-plugins", listOf(
                    ktor("core"),
                    ktor("content-negotiation", "ktor-plugin-serialization"),
                    ktor("kotlinx-json", "ktor-plugin-serialization-json", "ktor-serialization"),
                    ktor("resources", "ktor-plugin-typedRoutes"),
                    ktor("auth", "ktor-plugin-auth")
                )
            )

            ktor("darwin", "ktor-engine-apple")
            ktor("okhttp", "ktor-engine-java")
        }
    }
}