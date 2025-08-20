package ir.miare.androidcodechallenge.domain

import com.jabama.challenge.data.model.RepositoryDto
import com.jabama.challenge.data.model.RequestAccessTokenDto
import com.jabama.challenge.data.model.ResponseAccessTokenDto
import ir.miare.androidcodechallenge.domain.model.Repository
import ir.miare.androidcodechallenge.domain.model.RequestAccessToken
import ir.miare.androidcodechallenge.domain.model.ResponseAccessToken

internal fun RequestAccessToken.mapToRequestAccessTokenDto() = RequestAccessTokenDto(
    clientId = clientId,
    clientSecret = clientSecret,
    code = code,
    redirectUri = redirectUri,
    state = state,
)

internal fun ResponseAccessTokenDto.mapToResponseAccessToken() = ResponseAccessToken(
    accessToken = accessToken,
    tokenType = tokenType,
)

internal fun RepositoryDto.mapToRepository() = Repository(
    name = name.orEmpty(),
    language = language.orEmpty(),
    isFavorite = isFavorite,
    stargazers_count = stargazers_count,
    updated_at = updated_at.orEmpty(),
    visibility = visibility.orEmpty(),
)