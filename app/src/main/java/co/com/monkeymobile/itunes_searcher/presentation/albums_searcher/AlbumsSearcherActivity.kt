package co.com.monkeymobile.itunes_searcher.presentation.albums_searcher

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import co.com.monkeymobile.itunes_searcher.databinding.ActivityAlbumsSearcherBinding
import co.com.monkeymobile.itunes_searcher.presentation.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AlbumsSearcherActivity :
    BaseActivity<AlbumSearcherViewModel, AlbumSearcherViewState, AlbumSearcherViewEvent>() {

    companion object {
        fun getIntent(context: Context) = Intent(context, AlbumsSearcherActivity::class.java)
    }

    override val viewModel: AlbumSearcherViewModel by viewModels()
    private lateinit var binding: ActivityAlbumsSearcherBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAlbumsSearcherBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }

    override fun buildState(state: AlbumSearcherViewState) {
        when (state) {
            AlbumSearcherViewState.Initial -> buildInitialState()
            is AlbumSearcherViewState.Content -> buildContentState(state)
            is AlbumSearcherViewState.AlbumInfo -> buildAlbumInfoState(state)
            AlbumSearcherViewState.Loading -> buildLoadingState()
        }
    }

    private fun buildInitialState() {

    }

    private fun buildContentState(state: AlbumSearcherViewState.Content) {

    }

    private fun buildAlbumInfoState(state: AlbumSearcherViewState.AlbumInfo) {

    }

    private fun buildLoadingState() {

    }
}
