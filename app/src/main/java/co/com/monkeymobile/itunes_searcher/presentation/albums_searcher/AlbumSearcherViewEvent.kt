package co.com.monkeymobile.itunes_searcher.presentation.albums_searcher

import co.com.monkeymobile.itunes_searcher.domain.model.Album
import co.com.monkeymobile.itunes_searcher.presentation.ViewEvent

sealed class AlbumSearcherViewEvent : ViewEvent {

    class Search(val term: String) : AlbumSearcherViewEvent() {

        override fun getName() = "AlbumSearcherViewEvent.Search"
    }

    class AlbumClicked(val album: Album) : AlbumSearcherViewEvent() {

        override fun getName() = "AlbumSearcherViewEvent.AlbumClicked"
    }
}
