package com.superz.moviedemo.ui.navigation

import com.superz.moviedemo.util.K

sealed class Route {
    data class HomeScreen(val route: String = "homeScreen") : Route()
    data class FilmScreen(
        val route: String = "FilmScreen",
        val routeWithArgs: String = "$route/{${K.MOVIE_ID}}"
    ): Route() {
        fun getRouteWithArgs(id: Int): String {
            return "$route/$id"
        }
    }
}