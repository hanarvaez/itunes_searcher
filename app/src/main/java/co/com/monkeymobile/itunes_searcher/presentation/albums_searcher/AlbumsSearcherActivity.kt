package co.com.monkeymobile.itunes_searcher.presentation.albums_searcher

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import co.com.monkeymobile.itunes_searcher.R

class AlbumsSearcherActivity : AppCompatActivity() {

    companion object {
        fun getIntent(context: Context) = Intent(context, AlbumsSearcherActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_albums_searcher)
    }
}