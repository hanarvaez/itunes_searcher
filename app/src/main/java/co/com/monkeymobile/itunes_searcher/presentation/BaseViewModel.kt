package co.com.monkeymobile.itunes_searcher.presentation

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.com.monkeymobile.itunes_searcher.util.LOG_MESSAGE_EVENT
import co.com.monkeymobile.itunes_searcher.util.TAG_VIEW_UPDATE
import co.com.monkeymobile.itunes_searcher.util.launchSafe
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

abstract class BaseViewModel<State : ViewState, Event : ViewEvent>(
    private val coroutineDispatcher: CoroutineDispatcher
) : ViewModel() {

    private val mutableState: MutableStateFlow<State?> = MutableStateFlow(null)
    val state: Flow<State?> = mutableState

    protected val mutableToastMessage = MutableStateFlow("")
    val toastMessage: Flow<String> = mutableToastMessage

    init {
        setInitialState()
    }

    protected abstract fun getInitialState(): State

    private fun setInitialState() {
        mutableState.update {
            getInitialState()
        }
    }

    fun getCurrentState() = mutableState.value ?: getInitialState()

    fun dispatchEvent(event: Event) {
        viewModelScope.launchSafe(coroutineDispatcher) {
            Log.i(TAG_VIEW_UPDATE, "$LOG_MESSAGE_EVENT${event.getName()}")
            processEvent(event)
        }
    }

    protected abstract suspend fun processEvent(event: Event)

    protected fun setState(state: State) {
        this.mutableState.update {
            state
        }
    }
}
