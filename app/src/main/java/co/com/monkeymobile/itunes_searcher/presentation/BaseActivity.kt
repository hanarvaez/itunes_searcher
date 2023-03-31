package co.com.monkeymobile.itunes_searcher.presentation

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import co.com.monkeymobile.itunes_searcher.util.LOG_MESSAGE_STATE
import co.com.monkeymobile.itunes_searcher.util.TAG_VIEW_UPDATE
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

abstract class BaseActivity<ViewModel : BaseViewModel<State, Event>, State : ViewState, Event : ViewEvent> :
    AppCompatActivity() {

    abstract val viewModel: ViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        CoroutineScope(Dispatchers.IO).launch {
            viewModel.state.catch {
                it.printStackTrace()
            }.collect { state ->
                withContext(Dispatchers.Main) {
                    state?.let {
                        Log.i(TAG_VIEW_UPDATE, "$LOG_MESSAGE_STATE${it.getName()}")
                        buildState(it)
                    }
                }
            }
        }

        CoroutineScope(Dispatchers.IO).launch {
            viewModel.toastMessage.catch {
                it.printStackTrace()
            }.collect { message ->
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@BaseActivity, message, Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    protected abstract fun buildState(state: State)

    protected fun dispatchEvent(event: Event) {
        viewModel.dispatchEvent(event)
    }
}
