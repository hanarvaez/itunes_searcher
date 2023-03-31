package co.com.monkeymobile.itunes_searcher.data.source.remote.response

import android.os.Parcelable
import co.com.monkeymobile.itunes_searcher.domain.model.Album
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import java.text.SimpleDateFormat
import java.util.*

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
    return if(
        albumName == null ||
        artWork == null ||
        price == null ||
        copyright == null ||
        currency == null ||
        releaseDate == null ||
        primaryGenreName == null
    ) {
        null
    } else {

        val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())
        val outputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val parsedDate: Date = inputFormat.parse(releaseDate) ?: Date()
        val formattedDate = outputFormat.format(parsedDate)

        Album(
            albumName,
            artWork,
            price,
            copyright,
            currency,
            formattedDate,
            primaryGenreName
        )
    }
}
