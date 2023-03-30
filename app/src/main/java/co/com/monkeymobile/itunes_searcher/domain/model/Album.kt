package co.com.monkeymobile.itunes_searcher.domain.model

data class Album(
    val albumName: String,
    val artWork: String,
    val price: Double,
    val copyright: String,
    val currency: String,
    val releaseDate: String,
    val primaryGenreName: String
)
