package com.example.android_services

import android.Manifest
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.core.app.ActivityCompat
import com.example.android_services.background.BackgroundService
import com.example.android_services.foreground.ForegroundService
import com.example.android_services.foreground.RunningApp
import com.example.android_services.ui.theme.Android_ServicesTheme

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.TIRAMISU){
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.POST_NOTIFICATIONS),
                0
            )
        }
        setContent {
            Android_ServicesTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                    ) {
                        //Foreground Service Screen
                        Text(text = "foreground Service")
                        Button(onClick = {
                            Intent(applicationContext, ForegroundService::class.java).also {
                                it.action = ForegroundService.Actions.START.toString()
                                startService(it)
                            }
                        }) {
                            Text(text = "Start Foreground Service")
                        }

                        Button(onClick = {
                            Intent(applicationContext, ForegroundService::class.java).also {
                                it.action = ForegroundService.Actions.STOP.toString()
                                //startService(it)
                                startForegroundService(it)
                            }
                        }) {
                            Text(text = " Stop Foreground Service")
                        }

                        //Background Service Screen
                        Text(text = "Background Service")
                        Button(onClick = {
                            startService(Intent(this@MainActivity, BackgroundService::class.java))
                        }) {
                            Text(text = "Start Service")
                        }

                        Button(onClick = {
                            stopService(Intent(this@MainActivity, BackgroundService::class.java))
                        }) {
                            Text(text = "Stop Service")
                        }
                    }
                }
            }
        }
    }
}