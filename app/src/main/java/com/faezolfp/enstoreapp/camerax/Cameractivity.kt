package com.faezolfp.enstoreapp.camerax

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.faezolfp.enstoreapp.R
import com.faezolfp.enstoreapp.databinding.ActivityCameractivityBinding

class Cameractivity : AppCompatActivity() {
    private val binding: ActivityCameractivityBinding by lazy { ActivityCameractivityBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}