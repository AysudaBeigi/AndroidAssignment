package ir.miare.androidcodechallenge.domain.usecase

import com.jabama.challenge.domain.ITokenRepository

class ReadTokenUseCase(private val repository: ITokenRepository) {
    suspend fun execute() = repository.readToken()
}