package co.com.monkeymobile.itunes_searcher.presentation.albums_searcher

import co.com.monkeymobile.itunes_searcher.domain.model.Album
import co.com.monkeymobile.itunes_searcher.presentation.ViewState

sealed class AlbumSearcherViewState : ViewState {

    object Initial : AlbumSearcherViewState() {

        override fun getName() = "AlbumSearcherViewState.Initial"
    }

    object Loading : AlbumSearcherViewState() {

        override fun getName() = "AlbumSearcherViewState.Loading"
    }

    class Content(val albums: List<Album>) : AlbumSearcherViewState() {

        override fun getName() = "AlbumSearcherViewState.Content"
    }

    class AlbumInfo(val album: Album): AlbumSearcherViewState() {

        override fun getName() = "AlbumSearcherViewState.AlbumInfo"
    }
}
