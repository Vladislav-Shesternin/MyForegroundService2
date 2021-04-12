package com.example.myforegroundservice2

import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import com.example.myforegroundservice2.repositories.NotificationBuilder

const val RC_CLASS_TO_OPEN = 0
const val COUNTER_NOTIFICATION_ID = 1

class CounterForegroundService : Service() {

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        val numberAppOpenings = intent.getIntExtra(KEY_EXTRA_NUMBER_APP_OPENINGS, 0)

        NotificationBuilder.buildNotification(this, numberAppOpenings.toString())
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