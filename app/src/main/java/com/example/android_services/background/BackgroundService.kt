package com.example.android_services.background

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder
import android.provider.Settings


class BackgroundService: Service(){
    private lateinit var player: MediaPlayer

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        player = MediaPlayer.create(this, Settings.System.DEFAULT_NOTIFICATION_URI)
        player.setLooping(true)
        player.start()
        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        player.stop()

    }
    override fun onBind(intent: Intent?): IBinder? {
        TODO("Not yet implemented")
    }
}