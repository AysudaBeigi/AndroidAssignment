package ir.miare.androidcodechallenge.data.repository

import ir.miare.androidcodechallenge.data.api.RankingApiService
import ir.miare.androidcodechallenge.domain.IFootballRepository

class FootballRepositoryImpl(
    private val accessTokenApiService: RankingApiService,
) :
    IFootballRepository {
    override suspend fun accessToken(requestAccessToken: RequestAccessToken) =
        accessTokenApiService.accessToken(requestAccessToken.mapToRequestAccessTokenDto())
            .mapToResponseAccessToken()

    override suspend fun saveIsFollowed(requestAccessToken: RequestAccessToken): ResponseAccessToken {
        TODO("Not yet implemented")
    }

    override suspend fun getIsFollowed(requestAccessToken: RequestAccessToken): ResponseAccessToken {
        TODO("Not yet implemented")
    }

    override suspend fun getRepositoryList(token: String): List<Repository> {
        return repositoryApiService.getUserRepositories(token = "Bearer $token")
            .map { it.mapToRepository() }
    }
}