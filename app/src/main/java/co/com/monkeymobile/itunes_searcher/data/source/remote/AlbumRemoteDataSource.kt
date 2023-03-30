package co.com.monkeymobile.itunes_searcher.data.source.remote

import co.com.monkeymobile.itunes_searcher.data.source.remote.response.AlbumQueryBackendResponse

interface AlbumRemoteDataSource {

    suspend fun fetchAlbumsListByTerm(term: String): AlbumQueryBackendResponse
}