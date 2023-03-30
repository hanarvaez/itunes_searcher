package co.com.monkeymobile.itunes_searcher.data.source.remote.response

import android.os.Parcelable
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