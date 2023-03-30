package co.com.monkeymobile.itunes_searcher.presentation.splash

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import co.com.monkeymobile.itunes_searcher.R
import co.com.monkeymobile.itunes_searcher.presentation.albums_searcher.AlbumsSearcherActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
    }

    override fun onResume() {
        super.onResume()
        startActivity(AlbumsSearcherActivity.getIntent(this))
        finish()
    }
}
