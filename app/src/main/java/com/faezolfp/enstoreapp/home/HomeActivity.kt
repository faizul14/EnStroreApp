package com.faezolfp.enstoreapp.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.faezolfp.enstoreapp.ListProduct.ListProductActivity
import com.faezolfp.enstoreapp.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    private val bindig : ActivityHomeBinding by lazy { ActivityHomeBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(bindig.root)
        bindig.cdSearcproduct.setOnClickListener{
            startActivity(Intent(this, ListProductActivity::class.java))
        }
    }
}