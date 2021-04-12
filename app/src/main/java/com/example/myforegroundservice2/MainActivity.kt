package com.example.myforegroundservice2

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.myforegroundservice2.databinding.ActivityMainBinding
import com.example.myforegroundservice2.repositories.KEY_SAVED_OBJECT
import com.example.myforegroundservice2.repositories.SharedRepository

const val KEY_EXTRA_NUMBER_APP_OPENINGS = "number_app_openings"

private enum class CommandServiceManager {
    START, STOP
}

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private val TAG = this::class.simpleName

    private lateinit var binding: ActivityMainBinding
    private lateinit var btnStart: Button
    private lateinit var btnStop: Button
    private lateinit var btnClear: Button

    private val sharedRepo by lazy { SharedRepository(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initComponentsUI()

        foregroundService(getCounterForegroundServiceIntent(sharedRepo.updateCountAppOpenings()), CommandServiceManager.START)
        buttonState(btnStartState = false, btnStopState = true, btnClearState = true)
    }

    // {init}: Components UI
    private fun initComponentsUI() {
        binding.also {
            btnStart = it.btnStart
            btnStop = it.btnStop
            btnClear = it.btnClear
        }
        initListeners()
    }

    // {init}: Listeners
    private fun initListeners() {
        btnStart.setOnClickListener(this)
        btnStop.setOnClickListener(this)
        btnClear.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when (v.id) {
            btnStart.id -> {
                foregroundService(
                    getCounterForegroundServiceIntent(sharedRepo.countAppOpenings()),
                    CommandServiceManager.START
                )
                buttonState(btnStartState = false, btnStopState = true, btnClearState = true)
            }
            btnStop.id -> {
                foregroundService(
                    getCounterForegroundServiceIntent(),
                    CommandServiceManager.STOP
                )
                buttonState(btnStartState = true, btnStopState = false, btnClearState = false)
            }
            btnClear.id -> {
                sharedRepo.clear()
                foregroundService(
                    getCounterForegroundServiceIntent(),
                    CommandServiceManager.STOP
                )
                buttonState(btnStartState = true, btnStopState = false, btnClearState = false)
            }
        }
    }

    private fun getCounterForegroundServiceIntent(countAppOpening: Int = 0): Intent {
        return Intent(this, CounterForegroundService::class.java)
            .putExtra(KEY_EXTRA_NUMBER_APP_OPENINGS, countAppOpening)
    }

    private fun buttonState(
        btnStartState: Boolean,
        btnStopState: Boolean,
        btnClearState: Boolean,
    ) {
        btnStart.isEnabled = btnStartState
        btnStop.isEnabled = btnStopState
        btnClear.isEnabled = btnClearState
    }

    private fun foregroundService(
        intent: Intent,
        command: CommandServiceManager
    ) {
        when (command) {
            CommandServiceManager.START -> ContextCompat.startForegroundService(this, intent)
            CommandServiceManager.STOP -> stopService(intent)
        }
    }
}