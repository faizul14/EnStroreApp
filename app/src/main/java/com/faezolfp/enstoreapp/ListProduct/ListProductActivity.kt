package com.faezolfp.enstoreapp.ListProduct

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.faezolfp.enstoreapp.additem.AddItemActivity
import com.faezolfp.enstoreapp.core.ui.ListProductAdapter
import com.faezolfp.enstoreapp.databinding.ActivityListProductBinding
import dagger.hilt.android.AndroidEntryPoint

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

        val getByIntentKodeProduct: String? = intent.getStringExtra(KODE_PRODUCT) ?: null
        val getByIntentIsForSearch = intent.getBooleanExtra(SEARCH_BY_KODE, false)

        displayViewList(getByIntentIsForSearch, getByIntentKodeProduct)
        displayButton()
        displayCase(getByIntentIsForSearch)
    }

    fun displayCase(isSearch: Boolean){
        binding.apply {
            txtTopBar.setText("List Item")
            bottomView.visibility = View.VISIBLE
            when(isSearch){
                true ->{
                    txtTopBar.setText("Item")
                    bottomView.visibility = View.GONE
                }
                else ->{
                    txtTopBar.setText("List Item")
                    bottomView.visibility = View.VISIBLE
                }
            }
        }
    }

    private fun displayButton() {
        binding.apply {
            btnAdditem.setOnClickListener {
                startActivity(Intent(this@ListProductActivity, AddItemActivity::class.java))
            }
            btnBack.setOnClickListener {
                finish()
            }
        }
    }

    private fun displayViewList(isByCodePeoduct: Boolean, CodeProduct: String?) {
        binding.rvListProduct.layoutManager = LinearLayoutManager(this)
        viewModel.listProduct(isByCodePeoduct, CodeProduct).observe(this) { dataProduct ->
            adapter.setListProduct(dataProduct)
            binding.rvListProduct.adapter = adapter

        }
    }

    companion object {
        const val SEARCH_BY_KODE = "search_by_code"
        const val KODE_PRODUCT = "kode_product"
    }
}