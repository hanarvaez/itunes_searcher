package co.com.monkeymobile.itunes_searcher.domain.use_case

import co.com.monkeymobile.itunes_searcher.di.DefaultDispatcher
import co.com.monkeymobile.itunes_searcher.domain.model.Album
import co.com.monkeymobile.itunes_searcher.domain.repository.AlbumRepository
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

@ViewModelScoped
class GetAlbumsUseCase @Inject constructor(
    private val albumRepository: AlbumRepository,
    @DefaultDispatcher dispatcher: CoroutineDispatcher
): SuspendUseCase<GetAlbumsUseCaseParams, GetAlbumsUseCaseResult>(dispatcher) {

    override suspend fun execute(parameters: GetAlbumsUseCaseParams): GetAlbumsUseCaseResult {
        val albumList = albumRepository.fetchAlbumsListByTerm(parameters.terms)
        return GetAlbumsUseCaseResult(albumList)
    }
}

data class GetAlbumsUseCaseParams(val terms: String)

data class GetAlbumsUseCaseResult(val albums: List<Album>)
