package com.example.myforegroundservice2

import android.content.Context
import android.content.Intent
import androidx.core.content.ContextCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.myforegroundservice2.useCases.CreatorNotificationChannel
import com.example.myforegroundservice2.useCases.SharedRepository
import com.example.myforegroundservice2.utils.ButtonState
import com.example.myforegroundservice2.utils.countAppOpenings
import com.example.myforegroundservice2.utils.updateCountAppOpenings
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

private enum class Command {
    START, STOP
}

@HiltViewModel
class MainViewModel @Inject constructor(
    @ApplicationContext val context: Context,
    private val sharedRepo: SharedRepository,
    private val creatorNotificationChannel: CreatorNotificationChannel
) : ViewModel() {

    private val _state = MutableLiveData<ButtonState>()
    val state: LiveData<ButtonState>
        get() = _state

    init {
        creatorNotificationChannel.createNotificationChannel(context)
    }

    fun startForegroundService(intent: Intent) {
        intent.putExtra(KEY_EXTRA_NUMBER_APP_OPENINGS, sharedRepo.countAppOpenings())
        controlForegroundService(intent, Command.START)

        _state.value = ButtonState(btnStartState = false)
    }

    fun stopForegroundService(intent: Intent) {
        controlForegroundService(intent, Command.STOP)

        _state.value = ButtonState(btnStopState = false, btnClearState = false)
    }

    fun updateCounterAppOpenings(intent: Intent) {
        intent.putExtra(KEY_EXTRA_NUMBER_APP_OPENINGS, sharedRepo.updateCountAppOpenings())
        controlForegroundService(intent, Command.START)

        _state.value = ButtonState(btnStartState = false)
    }

    fun clearCounterAppOpenings(intent: Intent) {
        sharedRepo.clear()
        controlForegroundService(intent, Command.STOP)

        _state.value = ButtonState(btnStopState = false, btnClearState = false)
    }

    private fun controlForegroundService(
        intent: Intent,
        command: Command
    ) {
        when (command) {
            Command.START -> ContextCompat.startForegroundService(context, intent)
            Command.STOP -> context.stopService(intent)
        }
    }

}