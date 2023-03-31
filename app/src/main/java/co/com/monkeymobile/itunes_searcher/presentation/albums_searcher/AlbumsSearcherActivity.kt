package co.com.monkeymobile.itunes_searcher.presentation.albums_searcher

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import co.com.monkeymobile.itunes_searcher.R
import co.com.monkeymobile.itunes_searcher.databinding.ActivityAlbumsSearcherBinding
import co.com.monkeymobile.itunes_searcher.databinding.DialogAlbumDetailBinding
import co.com.monkeymobile.itunes_searcher.domain.model.Album
import co.com.monkeymobile.itunes_searcher.presentation.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AlbumsSearcherActivity :
    BaseActivity<AlbumSearcherViewModel, AlbumSearcherViewState, AlbumSearcherViewEvent>(),
    AlbumAdapter.AlbumItemListener {

    companion object {
        fun getIntent(context: Context) = Intent(context, AlbumsSearcherActivity::class.java)
    }

    override val viewModel: AlbumSearcherViewModel by viewModels()
    private lateinit var binding: ActivityAlbumsSearcherBinding
    private lateinit var adapter: AlbumAdapter
    private var alertDialog: AlertDialog? = null

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
        adapter = AlbumAdapter(this)
        binding.albumRecyclerView.adapter = adapter

        binding.buttonSearch.setOnClickListener {
            dispatchEvent(
                AlbumSearcherViewEvent.Search(
                    binding.term.text.toString()
                )
            )
        }
    }

    private fun buildContentState(state: AlbumSearcherViewState.Content) {
        val haveResults = state.albums.isNotEmpty()

        val errorMessageVisibility = if (haveResults) {
            View.GONE
        } else {
            View.VISIBLE
        }

        with(binding) {
            buttonSearch.isEnabled = true
            term.isEnabled = true
            progress.visibility = View.GONE
            albumRecyclerView.visibility = View.VISIBLE
            errorMessage.visibility = errorMessageVisibility
        }

        adapter.submitList(state.albums)
    }

    private fun buildAlbumInfoState(state: AlbumSearcherViewState.AlbumInfo) {
        val dialogAlbumDetailBinding = DialogAlbumDetailBinding.inflate(layoutInflater)

        with(dialogAlbumDetailBinding) {
            primaryGenre.text = state.album.primaryGenreName
            collectionPrice.text = state.album.price.toString()
            currency.text = state.album.currency
            copyright.text = state.album.copyright
        }

        alertDialog = AlertDialog.Builder(this@AlbumsSearcherActivity).apply {

            setCancelable(false)
            setView(dialogAlbumDetailBinding.root)

            setPositiveButton(getString(R.string.text_ok)) { _, _ ->
                alertDialog?.cancel()
                alertDialog = null
            }
        }.create()

        alertDialog?.show()
    }

    private fun buildLoadingState() {
        with(binding) {
            buttonSearch.isEnabled = false
            term.isEnabled = false
            progress.visibility = View.VISIBLE
            albumRecyclerView.visibility = View.GONE
            errorMessage.visibility = View.GONE
        }
    }

    override fun onAlbumClicked(album: Album) {
        dispatchEvent(AlbumSearcherViewEvent.AlbumClicked(album))
    }
}
