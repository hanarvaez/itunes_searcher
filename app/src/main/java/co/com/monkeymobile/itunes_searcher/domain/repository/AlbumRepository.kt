package co.com.monkeymobile.itunes_searcher.domain.repository

import co.com.monkeymobile.itunes_searcher.domain.model.Album

interface AlbumRepository {

    suspend fun fetchAlbumsListByTerm(term: String): List<Album>
}