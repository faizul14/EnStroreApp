package com.faezolfp.enstoreapp.service

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.faezolfp.enstoreapp.R
import com.faezolfp.enstoreapp.core.domain.usecase.UseCase
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.Calendar

@HiltWorker
class SapaWorker @AssistedInject constructor(
    @Assisted private val context: Context,
    @Assisted workerParams: WorkerParameters,
    private val useCase: UseCase
) : CoroutineWorker(context, workerParams) {

    override suspend fun doWork(): Result {
        val calendar = Calendar.getInstance()
        val currentHours = calendar.get(Calendar.HOUR_OF_DAY)

        val greeting = if (currentHours in (6 until 18)) {
            "Selamat Pagi Faezol Padli"
        } else {
            "Selamat Malam Faezol Padli"
        }

        CoroutineScope(Dispatchers.IO).launch {
            if (currentHours in (6 until 18)) useCase.saveIsNigth(false)
            else useCase.saveIsNigth(true)
        }

        return try {
            Log.d("TRACKER", "WORKMANAGER DI JALANKER")
            showNotifikasi(greeting)
            CoroutineScope(Dispatchers.IO).launch {
                useCase.saveGrettingText(greeting)
            }.join()

            Result.success()
        } catch (e: Exception) {
            Result.failure()
        }
    }

    private fun showNotifikasi(grettingText: String) {
        Log.d("TRACKER", "NOTIFIKASI DI JALANKER")

        val notificationManager =
            applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val notification: NotificationCompat.Builder =
            NotificationCompat.Builder(applicationContext, "CHANNEL_ID")
                .setSmallIcon(R.drawable.baseline_notifications_24)
                .setContentTitle("Hi! Faezol")
                .setContentText("$grettingText \nHave Nice Day :)")
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setDefaults(NotificationCompat.DEFAULT_ALL)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                "CHANNEL_ID", "CHANNEL_NAME", NotificationManager.IMPORTANCE_HIGH
            )
            notification.setChannelId("CHANNEL_ID")
            notificationManager.createNotificationChannel(channel)
        }
        notificationManager.notify(1, notification.build())
    }
}