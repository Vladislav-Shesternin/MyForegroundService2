package com.example.myforegroundservice2.repositories

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import com.example.myforegroundservice2.R
import com.example.myforegroundservice2.useCases.CreatorNotificationChannelUseCase

const val CHANNEL_ID = "channel_id"

object CreatorNotificationChannel : CreatorNotificationChannelUseCase {

    override fun createNotificationChannel(context: Context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel(
                CHANNEL_ID,
                context.resources.getString(R.string.channel_name),
                NotificationManager.IMPORTANCE_HIGH
            ).also { channel ->
                context.getSystemService(NotificationManager::class.java)
                    .createNotificationChannel(channel)
            }
        }
    }
}