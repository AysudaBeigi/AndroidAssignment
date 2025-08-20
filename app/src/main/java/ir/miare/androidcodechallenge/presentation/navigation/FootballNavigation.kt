package ir.miare.androidcodechallenge.presentation.navigation


sealed class FootballNavigation(private val navigationName: String) {
    companion object {
        const val routeName = "football"
    }

    object FollowedPlayerListScreen : FootballNavigation("followed-player-list")
    object PlayerListScreen : FootballNavigation("all-player-list")

    fun createRoute(): String {
        return "$routeName/$navigationName"
    }
}