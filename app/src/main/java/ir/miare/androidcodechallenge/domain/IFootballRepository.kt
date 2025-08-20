package ir.miare.androidcodechallenge.domain

import ir.miare.androidcodechallenge.domain.model.Repository
import ir.miare.androidcodechallenge.domain.model.RequestAccessToken
import ir.miare.androidcodechallenge.domain.model.ResponseAccessToken

interface IFootballRepository {
    suspend fun accessToken(requestAccessToken: RequestAccessToken): ResponseAccessToken
    suspend fun getRepositoryList(token: String): List<Repository>
}