package co.com.monkeymobile.itunes_searcher.data.source.remote.impl

import co.com.monkeymobile.itunes_searcher.data.source.remote.AlbumRemoteDataSource
import co.com.monkeymobile.itunes_searcher.di.NetworkModule
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AlbumRemoteDataSourceImpl @Inject constructor() : AlbumRemoteDataSource {

    override suspend fun fetchAlbumsListByTerm(term: String) =
        NetworkModule.getApiService().fetchAlbumsListByTerm(term)
}