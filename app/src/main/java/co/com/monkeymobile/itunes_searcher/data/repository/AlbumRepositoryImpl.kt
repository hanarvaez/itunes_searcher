package co.com.monkeymobile.itunes_searcher.data.repository

import co.com.monkeymobile.itunes_searcher.data.source.remote.AlbumRemoteDataSource
import co.com.monkeymobile.itunes_searcher.data.source.remote.response.toAlbumList
import co.com.monkeymobile.itunes_searcher.domain.repository.AlbumRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AlbumRepositoryImpl @Inject constructor(
    private val remoteDataSource: AlbumRemoteDataSource
) : AlbumRepository {

    override suspend fun fetchAlbumsListByTerm(term: String) = remoteDataSource.fetchAlbumsListByTerm(term).toAlbumList()
}
