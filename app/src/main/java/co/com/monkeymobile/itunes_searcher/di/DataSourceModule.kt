package co.com.monkeymobile.itunes_searcher.di

import co.com.monkeymobile.itunes_searcher.data.source.remote.AlbumRemoteDataSource
import co.com.monkeymobile.itunes_searcher.data.source.remote.impl.AlbumRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {

    @Binds
    abstract fun provideAlbumRemoteDataSource(source: AlbumRemoteDataSourceImpl): AlbumRemoteDataSource
}