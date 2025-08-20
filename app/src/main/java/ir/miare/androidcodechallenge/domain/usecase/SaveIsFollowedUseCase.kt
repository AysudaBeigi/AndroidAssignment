package ir.miare.androidcodechallenge.domain.usecase

import ir.miare.androidcodechallenge.domain.IFootballRepository


class SaveIsFollowedUseCase(private val repository: IFootballRepository) {
    suspend fun execute(token: String) = repository.saveIsFollowed(token = token)
}