package com.faezolfp.enstoreapp.additem

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.faezolfp.enstoreapp.R
import com.faezolfp.enstoreapp.core.domain.model.ProductModel
import com.faezolfp.enstoreapp.databinding.ActivityAddItemBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddItemActivity : AppCompatActivity(), View.OnClickListener {
    private val binding: ActivityAddItemBinding by lazy {
        ActivityAddItemBinding.inflate(
            layoutInflater
        )
    }
    private val viewModel: AddItemViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        displayButton()
    }

    private fun displayButton() {
        binding.btnBack.setOnClickListener(this)
        binding.btnSave.setOnClickListener(this)
    }

    private fun displaySave() {
        var nameProduct: String? = null
        var kodeProduct: String? = null
        var entityProduct: String? = null
        var expiredProduct: String? = null
        var priceProduct: String? = null
        var imageProduct: String? = null
        binding.apply {
            nameProduct = edtProductName.text.toString()
            kodeProduct = edtIdProduct.text.toString()
            entityProduct = edtQuantityProduct.text.toString()
            expiredProduct = edtDateExpired.text.toString()
            priceProduct = if (edtTotalPrice.text.toString() != "") {
                "${
                    edtTotalPrice.text.toString().toInt() / edtQuantityProduct.text.toString().toInt()
                }"
            } else {
                edtPrice.text.toString()
            }
            imageProduct = "local image//:dkdk"
        }
        val data = ProductModel(
            id = 0,
            nameProduct = nameProduct,
            kodeProduct = kodeProduct,
            entityProduct = entityProduct?.toInt(),
            expiredProduct = expiredProduct,
            priceProduct = priceProduct,
            imageProduct = imageProduct
        )
        viewModel.saveProduct(data)
        Toast.makeText(this, "Item berhasil di tambahkan!", Toast.LENGTH_SHORT).show()
        finish()
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.btn_back -> {
                finish()
            }

            R.id.btn_save -> {
                displaySave()
            }

        }
    }

    companion object{
        const val DATA_PRODUCT = "data_product"
        const val STATE = 0
    }
}