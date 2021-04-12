package com.example.myforegroundservice2

import android.app.Application
import com.example.myforegroundservice2.repositories.CreatorNotificationChannel

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        CreatorNotificationChannel.createNotificationChannel(this)

    }


}