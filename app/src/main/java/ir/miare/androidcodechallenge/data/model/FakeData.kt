package ir.miare.androidcodechallenge.data.model

import com.google.gson.annotations.SerializedName

data class FakeData(
    @SerializedName("league") val league: League,
    @SerializedName("players") val players: List<Player>
)

data class League(
    @SerializedName("name") val name: String,
    @SerializedName("country") val country: String
)

data class Player(
    @SerializedName("name") val name: String,
    @SerializedName("team") val team: Team
)

data class Team(
    @SerializedName("name") val name: String,
    @SerializedName("rank") val rank: Int
)
