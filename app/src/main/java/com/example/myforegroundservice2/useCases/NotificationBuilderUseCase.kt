package com.example.myforegroundservice2.useCases

import android.content.Context
import androidx.core.app.NotificationCompat

interface NotificationBuilderUseCase {

    fun buildNotification(context: Context, contentText: String): NotificationCompat.Builder

}