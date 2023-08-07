package com.faezolfp.enstoreapp.ListProduct

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.faezolfp.enstoreapp.R
import com.faezolfp.enstoreapp.additem.AddItemActivity
import com.faezolfp.enstoreapp.core.ui.ListProductAdapter
import com.faezolfp.enstoreapp.databinding.ActivityListProductBinding
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp

@AndroidEntryPoint
class ListProductActivity : AppCompatActivity() {
    private val binding: ActivityListProductBinding by lazy {
        ActivityListProductBinding.inflate(
            layoutInflater
        )
    }
    private val adapter: ListProductAdapter by lazy { ListProductAdapter() }
    private val viewModel: ListProductViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        displayViewList()
        displayButton()
    }

    private fun displayButton() {
        binding.apply {
            btnAdditem.setOnClickListener {
                startActivity(Intent(this@ListProductActivity, AddItemActivity::class.java))
            }
            btnBack.setOnClickListener{
                finish()
            }
        }
    }

    private fun displayViewList() {
        binding.rvListProduct.layoutManager = LinearLayoutManager(this)
        viewModel.listProduct.observe(this) { dataProduct ->
            adapter.setListProduct(dataProduct)
            binding.rvListProduct.adapter = adapter

        }
    }
}