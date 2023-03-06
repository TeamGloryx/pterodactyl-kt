package net.gloryx.pterodactyl.routes

import net.gloryx.pterodactyl.PterodactylInternalAPI

@PterodactylInternalAPI
sealed interface Route<Response> {
    interface Get<Response> : Route<Response>

    interface Post<Body, Response> : Route<Response>
}