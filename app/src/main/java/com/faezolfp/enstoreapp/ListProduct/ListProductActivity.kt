package com.faezolfp.enstoreapp.ListProduct

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.faezolfp.enstoreapp.additem.AddItemActivity
import com.faezolfp.enstoreapp.core.ui.ListProductAdapter
import com.faezolfp.enstoreapp.databinding.ActivityListProductBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

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

    fun displayCase(isSearch: Boolean) {
        binding.apply {
            txtTopBar.text = "List Item"
            bottomView.visibility = View.VISIBLE
            when (isSearch) {
                true -> {
                    txtTopBar.text = "Item"
                    bottomView.visibility = View.GONE
                }

                else -> {
                    txtTopBar.text = "List Item"
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
            edtProductName.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    lifecycleScope.launch {
                        viewModel.queryChannel.value = p0.toString()
                    }
                }

                override fun afterTextChanged(p0: Editable?) {
                }

            })
        }
    }

    private fun displayViewList(isByCodePeoduct: Boolean, CodeProduct: String?) {
        binding.rvListProduct.layoutManager = LinearLayoutManager(this)
        viewModel.listProduct(isByCodePeoduct, CodeProduct).observe(this) { dataProduct ->
            adapter.setListProduct(dataProduct)
            binding.rvListProduct.adapter = adapter

        }
        viewModel.trackTextChange2.observe(this) { trackText ->
            if (!trackText.equals("")) {
                viewModel.listProduct(false, null, trackText).observe(this) { dataAfterSearch ->
                    adapter.setListProduct(dataAfterSearch)
                    binding.rvListProduct.adapter = adapter
                }
            } else {
                viewModel.listProduct(isByCodePeoduct, CodeProduct).observe(this) { dataProduct ->
                    adapter.setListProduct(dataProduct)
                    binding.rvListProduct.adapter = adapter

                }
            }
        }
    }

    companion object {
        const val SEARCH_BY_KODE = "search_by_code"
        const val KODE_PRODUCT = "kode_product"
    }
}