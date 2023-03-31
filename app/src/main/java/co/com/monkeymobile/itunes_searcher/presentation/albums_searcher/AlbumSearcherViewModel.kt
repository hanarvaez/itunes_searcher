package co.com.monkeymobile.itunes_searcher.presentation.albums_searcher

import co.com.monkeymobile.itunes_searcher.di.DefaultDispatcher
import co.com.monkeymobile.itunes_searcher.domain.model.Album
import co.com.monkeymobile.itunes_searcher.domain.use_case.GetAlbumsUseCase
import co.com.monkeymobile.itunes_searcher.domain.use_case.GetAlbumsUseCaseParams
import co.com.monkeymobile.itunes_searcher.domain.use_case.Result
import co.com.monkeymobile.itunes_searcher.presentation.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class AlbumSearcherViewModel @Inject constructor(
    private val getAlbumsUseCase: GetAlbumsUseCase,
    @DefaultDispatcher coroutineDispatcher: CoroutineDispatcher
) : BaseViewModel<AlbumSearcherViewState, AlbumSearcherViewEvent>(coroutineDispatcher) {

    override fun getInitialState() = AlbumSearcherViewState.Initial

    override suspend fun processEvent(event: AlbumSearcherViewEvent) {
        when (event) {
            is AlbumSearcherViewEvent.Search -> fetchAlbumList(event.term)
            is AlbumSearcherViewEvent.AlbumClicked -> showAlbumDetail(event.album)
        }
    }

    private suspend fun fetchAlbumList(term: String) {
        setState(AlbumSearcherViewState.Loading)

        when (val result = getAlbumsUseCase(GetAlbumsUseCaseParams(term))) {
            is Result.Success -> {
                setState(AlbumSearcherViewState.Content(result.data.albums))
            }

            is Result.Error -> {
                mutableToastMessage.update {
                    result.toString()
                }
                setState(AlbumSearcherViewState.Content(emptyList()))
            }
        }
    }

    private fun showAlbumDetail(album: Album) {
        setState(AlbumSearcherViewState.AlbumInfo(album))
    }

}
