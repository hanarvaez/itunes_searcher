package co.com.monkeymobile.itunes_searcher.data.source.remote.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class AlbumQueryBackendResponse(
    val resultsCount: Int?,
    val results: List<AlbumBackendResponse>?
): Parcelable

fun AlbumQueryBackendResponse.toAlbumList() = results?.mapNotNull { it.toAlbum() } ?: emptyList()
