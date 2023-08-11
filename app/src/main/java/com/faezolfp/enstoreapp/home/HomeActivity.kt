package com.faezolfp.enstoreapp.home

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.work.Constraints
import androidx.work.OneTimeWorkRequest
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import com.faezolfp.enstoreapp.ListProduct.ListProductActivity
import com.faezolfp.enstoreapp.core.utils.GetDateNow
import com.faezolfp.enstoreapp.databinding.ActivityHomeBinding
import com.faezolfp.enstoreapp.scanqr.QrScanActivity
import com.faezolfp.enstoreapp.service.SapaWorker
import dagger.hilt.android.AndroidEntryPoint
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {
    private val bindig: ActivityHomeBinding by lazy { ActivityHomeBinding.inflate(layoutInflater) }
    private lateinit var workManager: WorkManager
    private lateinit var periodicWork : PeriodicWorkRequest
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(bindig.root)
        bindig.cdSearcproduct.setOnClickListener {
            startActivity(Intent(this, ListProductActivity::class.java))
        }
        bindig.cdScanner.setOnClickListener {
            startActivity(Intent(this, QrScanActivity::class.java))
        }
        bindig.include.textView5.text = GetDateNow.getCurrentFormattedDate()
        workManager = WorkManager.getInstance(this)
//        setWorkManager()
        priodicWorkManager()
    }

    private fun setWorkManager() {
        val contain = Constraints.Builder()
            .setRequiresCharging(true)
            .build()
        val oneTimeWorkRequest = OneTimeWorkRequest.Builder(SapaWorker::class.java)
//            .setConstraints(contain)
            .build()
        workManager.enqueue(oneTimeWorkRequest)
    }

    private fun priodicWorkManager(){
        periodicWork = PeriodicWorkRequest.Builder(SapaWorker::class.java, 15, TimeUnit.MINUTES).build()
        workManager.enqueue(periodicWork)
    }

    override fun onPause() {
        super.onPause()
        workManager.cancelWorkById(periodicWork.id)
    }
}