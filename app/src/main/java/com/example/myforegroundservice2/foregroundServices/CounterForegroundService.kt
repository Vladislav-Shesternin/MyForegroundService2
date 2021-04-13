package com.example.myforegroundservice2.foregroundServices

import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import com.example.myforegroundservice2.KEY_EXTRA_NUMBER_APP_OPENINGS
import com.example.myforegroundservice2.MainActivity
import com.example.myforegroundservice2.repositories.NotificationBuilderImpl
import com.example.myforegroundservice2.useCases.NotificationBuilder
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

const val RC_CLASS_TO_OPEN = 0
const val COUNTER_NOTIFICATION_ID = 1

@AndroidEntryPoint
class CounterForegroundService : Service() {

    @Inject
    lateinit var notificationBuilder: NotificationBuilder

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        val numberAppOpenings = intent.getIntExtra(KEY_EXTRA_NUMBER_APP_OPENINGS, 0)

        notificationBuilder.buildNotification(this, numberAppOpenings.toString())
            .setContentIntent(getPendingIntent(MainActivity::class.java))
            .build()
            .also { notification ->
                startForeground(COUNTER_NOTIFICATION_ID, notification)
            }

        return START_NOT_STICKY
    }

    private fun getPendingIntent(classToOpen: Class<*>): PendingIntent {
        val intentOpenClass = Intent(this, classToOpen)

        return PendingIntent.getActivity(
            this,
            RC_CLASS_TO_OPEN,
            intentOpenClass,
            PendingIntent.FLAG_UPDATE_CURRENT
        )
    }
}