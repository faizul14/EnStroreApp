package com.faezolfp.enstoreapp.home

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkInfo
import androidx.work.WorkManager
import com.faezolfp.enstoreapp.ListProduct.ListProductActivity
import com.faezolfp.enstoreapp.R
import com.faezolfp.enstoreapp.additem.AddItemActivity
import com.faezolfp.enstoreapp.core.utils.GetDateNow
import com.faezolfp.enstoreapp.databinding.ActivityHomeBinding
import com.faezolfp.enstoreapp.scanqr.QrScanActivity
import com.faezolfp.enstoreapp.service.SapaWorker
import dagger.hilt.android.AndroidEntryPoint
import java.util.concurrent.TimeUnit

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {
    private val bindig: ActivityHomeBinding by lazy { ActivityHomeBinding.inflate(layoutInflater) }
    private val viewModel: HomeViewModel by viewModels()
    private lateinit var workManager: WorkManager
    private lateinit var periodicWork: PeriodicWorkRequest
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(bindig.root)

        observerViewModel()
        displayBUtton()
        workManager = WorkManager.getInstance(this)
        priodicWorkManager()
        workManager.getWorkInfoByIdLiveData(periodicWork.id)
            .observe(this@HomeActivity) { workInfo ->
                if (workInfo.state != WorkInfo.State.ENQUEUED) {
                    bindig.btnNotif.isEnabled = true
                    workManager.enqueue(periodicWork)
                } else {
                    bindig.btnNotif.visibility = View.GONE
                    workManager.cancelWorkById(periodicWork.id)
                }
            }
    }

    private fun observerViewModel() {
        viewModel.getIsNight.observe(this) { isNight ->
            viewModel.getGrettingText.observe(this) { dataGretting ->
                bindig.apply {
                    include.textView3.text = dataGretting
                    if (isNight) {
                        include.textView3.setCompoundDrawablesRelativeWithIntrinsicBounds(
                            resources.getDrawable(
                                R.drawable.baseline_nights_stay_24
                            ), null, null, null
                        )
                    } else {
                        include.textView3.setCompoundDrawablesRelativeWithIntrinsicBounds(
                            resources.getDrawable(
                                R.drawable.baseline_wb_sunny_24
                            ), null, null, null
                        )
                    }
                }
            }
        }
        bindig.include.textView5.text = GetDateNow.getCurrentFormattedDate()
    }

    private fun displayBUtton() {
        bindig.apply {
            cdScanner.setOnClickListener {
                startActivity(Intent(this@HomeActivity, QrScanActivity::class.java))
            }
            cdSearcproduct.setOnClickListener {
                startActivity(Intent(this@HomeActivity, ListProductActivity::class.java))
            }
            cdAdditem.setOnClickListener {
                val move = Intent(this@HomeActivity, AddItemActivity::class.java)
                move.putExtra(AddItemActivity.STATE, 1)
                startActivity(move)
            }
            btnNotif.setOnClickListener {
                workManager.enqueue(periodicWork)
            }
        }
    }

    private fun priodicWorkManager() {
        periodicWork =
            PeriodicWorkRequest.Builder(SapaWorker::class.java, 15, TimeUnit.MINUTES).build()
    }
}