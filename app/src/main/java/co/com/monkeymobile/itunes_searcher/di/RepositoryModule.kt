package co.com.monkeymobile.itunes_searcher.di

import co.com.monkeymobile.itunes_searcher.data.repository.AlbumRepositoryImpl
import co.com.monkeymobile.itunes_searcher.domain.repository.AlbumRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindAlbumRepository(albumRepositoryImpl: AlbumRepositoryImpl): AlbumRepository
}