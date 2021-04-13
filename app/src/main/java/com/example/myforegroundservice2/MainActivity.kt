package com.example.myforegroundservice2

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.myforegroundservice2.databinding.ActivityMainBinding
import com.example.myforegroundservice2.foregroundServices.CounterForegroundService
import com.example.myforegroundservice2.repositories.SharedRepositoryImpl
import com.example.myforegroundservice2.utils.countAppOpenings
import com.example.myforegroundservice2.utils.updateCountAppOpenings
import dagger.hilt.android.AndroidEntryPoint

const val KEY_EXTRA_NUMBER_APP_OPENINGS = "number_app_openings"

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ActivityMainBinding.inflate(layoutInflater).also {
            setContentView(it.root)
            it.lifecycleOwner = this
            it.mainViewModel = this.mainViewModel
            it.intent = getCounterForegroundServiceIntent()
        }

        mainViewModel.updateCounterAppOpenings(getCounterForegroundServiceIntent())
    }

    private fun getCounterForegroundServiceIntent() =
        Intent(this, CounterForegroundService::class.java)
}