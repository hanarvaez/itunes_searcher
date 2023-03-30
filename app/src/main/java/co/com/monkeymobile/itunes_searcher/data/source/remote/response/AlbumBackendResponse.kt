package co.com.monkeymobile.itunes_searcher.data.source.remote.response

import android.os.Parcelable
import co.com.monkeymobile.itunes_searcher.domain.model.Album
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class AlbumBackendResponse(
    @SerializedName("collectionName") val albumName: String?,
    @SerializedName("artworkUrl100") val artWork: String?,
    @SerializedName("collectionPrice") val price: Double?,
    val copyright: String?,
    val currency: String?,
    val releaseDate: String?,
    val primaryGenreName: String?
) : Parcelable

fun AlbumBackendResponse.toAlbum(): Album? {
    val haveNullField = listOf(
        albumName,
        artWork,
        price,
        copyright,
        currency,
        releaseDate,
        primaryGenreName
    ).any { it == null }

    return if (haveNullField) {
        null
    } else {
        Album(
            albumName.orEmpty(),
            artWork.orEmpty(),
            price ?: 0.0,
            copyright.orEmpty(),
            currency.orEmpty(),
            releaseDate.orEmpty(),
            primaryGenreName.orEmpty()
        )
    }
}
