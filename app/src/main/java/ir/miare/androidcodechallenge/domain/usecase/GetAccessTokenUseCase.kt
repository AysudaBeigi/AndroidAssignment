package ir.miare.androidcodechallenge.domain.usecase

import com.jabama.challenge.domain.IGithubRepository
import ir.miare.androidcodechallenge.domain.model.RequestAccessToken
import ir.miare.androidcodechallenge.domain.model.ResponseAccessToken

private const val CLIENT_ID = "Ov23lieNfZen0Ac2lX66"
private const val CLIENT_SECRET = "b49541b80e0b3db47a8e5a785749f1fdf32baa27"
const val REDIRECT_URI = "myapp://callback"

const val url =
    "https://github.com/login/oauth/authorize?client_id=$CLIENT_ID&redirect_uri=$REDIRECT_URI&scope=repo%20user&state=0"


class GetAccessTokenUseCase(private val repository: IGithubRepository) {
    suspend fun execute(code: String): ResponseAccessToken {
        return repository.accessToken(
            requestAccessToken = RequestAccessToken(
                code = code,
                state = "0",
                clientSecret = CLIENT_SECRET,
                clientId = CLIENT_ID,
                redirectUri = REDIRECT_URI,
            )
        )
    }
}