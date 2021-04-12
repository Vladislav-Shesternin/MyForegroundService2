package com.example.myforegroundservice2.repositories

import android.content.Context
import androidx.core.app.NotificationCompat
import com.example.myforegroundservice2.R
import com.example.myforegroundservice2.useCases.NotificationBuilderUseCase

object NotificationBuilder : NotificationBuilderUseCase {

    override fun buildNotification(
        context: Context,
        contentText: String
    ): NotificationCompat.Builder {
        return NotificationCompat.Builder(context, CHANNEL_ID)
            .setContentTitle(context.resources.getString(R.string.app_name))
            .setContentText(contentText)
            .setSmallIcon(R.drawable.ic_app_opening)
    }
}
