package com.hjhjw1991.barney

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.hjhjw1991.barney.logcat.LogcatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        start_service.setOnClickListener {
            startActivity(Intent(this, LogcatActivity::class.java))
            startService(Intent(this, RemoteService::class.java))
        }

        textView.apply { text = "click to open logcat activity" }
            .setOnClickListener {
                startActivity(Intent(this, LogcatActivity::class.java))
            }

        var i = 0
        Thread{
            while (true) {
                Log.d("MainActivity", "onCreate: current is ${i++}")
                Thread.sleep(1000)
            }
        }.start()
    }
}