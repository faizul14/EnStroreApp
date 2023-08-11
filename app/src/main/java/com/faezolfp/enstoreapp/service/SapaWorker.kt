package com.faezolfp.enstoreapp.service

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.hilt.work.HiltWorker
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.faezolfp.enstoreapp.R
import com.faezolfp.enstoreapp.core.domain.usecase.UseCase
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import java.util.Calendar

class SapaWorker (
     context: Context,
     workerParams: WorkerParameters): Worker(context, workerParams) {
    override fun doWork(): Result {
        val calendar = Calendar.getInstance()
        val currentHours = calendar.get(Calendar.HOUR_OF_DAY)

        val greeting = if (currentHours in 6 until 18){
            "Selamat Pagi Faezo"
        }else{
            "Selamat Malam Faezol"
        }
        return Result.success()
    }

    private fun showNotifikasi(){
        val notificationManager = applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val notification: NotificationCompat.Builder = NotificationCompat.Builder(applicationContext, "CHANNEL_ID")
            .setSmallIcon(R.drawable.baseline_notifications_24)
            .setContentTitle("title")
            .setContentText("description")
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setDefaults(NotificationCompat.DEFAULT_ALL)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel("CHANNEL_ID", "CHANNEL_NAME", NotificationManager.IMPORTANCE_HIGH)
            notification.setChannelId("CHANNEL_ID")
            notificationManager.createNotificationChannel(channel)
        }
        notificationManager.notify(1, notification.build())
    }
}