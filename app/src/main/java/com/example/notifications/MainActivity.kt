package com.example.notifications

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        enableEdgeToEdge()

    val button1 = findViewById<Button>(R.id.btn1)
    val button2 = findViewById<Button>(R.id.btn2)
    val button3 = findViewById<Button>(R.id.btn3)

        val nm: NotificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

//        Notification only works for higher or equal 28
        if (Build.VERSION.SDK_INT >= 28) {

            nm.createNotificationChannel(
                NotificationChannel(
                    "First", "Default", NotificationManager.IMPORTANCE_DEFAULT
                )
            )
        }

        button1.setOnClickListener {
            val simpleNotif =
                NotificationCompat.Builder(this, "First").setContentTitle("Simple Title")
                    .setContentText("This is the sample description")
                    .setSmallIcon(R.drawable.ic_launcher_foreground)
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT).build()

            nm.notify(1, simpleNotif)
        }

        button2.setOnClickListener {
            val i = Intent()
            i.action = Intent.ACTION_VIEW
            i.data = Uri.parse("https://www.google.com")

            val pi = PendingIntent.getActivity(this, 123, i, PendingIntent.FLAG_UPDATE_CURRENT)

            val clickNotifi = NotificationCompat.Builder(this, "First")
                .setContentTitle("Simple Title 2")
                .setContentIntent(pi)
                .setAutoCancel(true)
                .setContentText("Sample Description 2")
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .build()

            nm.notify(2,clickNotifi)
        }
        button3.setOnClickListener {
            val i = Intent()
            i.action = Intent.ACTION_VIEW
            i.data = Uri.parse("https://www.github.com")

            val pi = PendingIntent.getActivity(this, 123, i , PendingIntent.FLAG_UPDATE_CURRENT)

            val clickNoti = NotificationCompat.Builder(this, "First")
                .setContentTitle("Simple Title 3")
                .addAction(R.drawable.ic_launcher_foreground, "Click me!", pi)
                .setAutoCancel(true)
                .setContentText("Sample Description 3")
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .build()
            nm.notify(3, clickNoti)


        }
    }
}