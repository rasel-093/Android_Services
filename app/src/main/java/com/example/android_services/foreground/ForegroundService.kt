package com.example.android_services.foreground

import android.annotation.SuppressLint
import android.app.Service
import android.content.Intent
import android.os.IBinder
import androidx.core.app.NotificationCompat


class ForegroundService: Service() {
    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        when(intent?.action){
            Actions.START.toString()-> start()
            Actions.STOP.toString() -> stopSelf()
        }
        return super.onStartCommand(intent, flags, startId)
    }

    @SuppressLint("ForegroundServiceType")
    fun start(){
        val notification = NotificationCompat.Builder(this,"foreground_channel")
            .setSmallIcon(androidx.core.R.drawable.ic_call_answer)
            .setContentTitle("Phone call")
            .setContentText("Incoming call")
            .build()
        startForeground(2, notification)
    }
    enum class Actions{
        START, STOP
    }
}