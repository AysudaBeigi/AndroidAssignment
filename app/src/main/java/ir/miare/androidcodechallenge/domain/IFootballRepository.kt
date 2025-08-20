package ir.miare.androidcodechallenge.domain

interface IFootballRepository {
    suspend fun accessToken(requestAccessToken: RequestAccessToken): ResponseAccessToken
    suspend fun saveIsFollowed(requestAccessToken: RequestAccessToken): ResponseAccessToken
    suspend fun getIsFollowed(requestAccessToken: RequestAccessToken): ResponseAccessToken
    suspend fun getRepositoryList(token: String): List<Repository>
}