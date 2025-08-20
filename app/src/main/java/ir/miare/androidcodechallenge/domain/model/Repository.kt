package ir.miare.androidcodechallenge.domain.model

data class Repository(
    val name: String,
    val language: String,
    var isFavorite: Boolean,
    val stargazers_count: Int,
    val updated_at: String,
    val visibility: String,
)