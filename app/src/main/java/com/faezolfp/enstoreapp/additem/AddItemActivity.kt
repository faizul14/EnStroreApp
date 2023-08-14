package com.faezolfp.enstoreapp.additem

import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.faezolfp.enstoreapp.ListProduct.ListProductActivity
import com.faezolfp.enstoreapp.R
import com.faezolfp.enstoreapp.camerax.Cameractivity
import com.faezolfp.enstoreapp.camerax.Cameractivity.Companion.CAMERA_X_RESULT
import com.faezolfp.enstoreapp.core.domain.model.ProductModel
import com.faezolfp.enstoreapp.core.utils.DataMapper.rotateFile
import com.faezolfp.enstoreapp.core.utils.GetDateNow
import com.faezolfp.enstoreapp.core.utils.ProvideImage
import com.faezolfp.enstoreapp.databinding.ActivityAddItemBinding
import com.faezolfp.enstoreapp.scanqr.QrScanActivity
import com.faezolfp.enstoreapp.scanqr.QrScanActivity.Companion.PRODUCTID_RESULT
import com.google.android.material.datepicker.MaterialDatePicker
import dagger.hilt.android.AndroidEntryPoint
import java.io.File

@AndroidEntryPoint
class AddItemActivity : AppCompatActivity(), View.OnClickListener {
    private val binding: ActivityAddItemBinding by lazy {
        ActivityAddItemBinding.inflate(
            layoutInflater
        )
    }
    private var NameProduct: String? = null
    private var dataPathImgProduct: String? = null
    private var kodeProduct: String? = null
    private val viewModel: AddItemViewModel by viewModels()

    /*
    * state
    * di gunakan untuk menentukan flow dari activity mana dan menentukan flow selanjutnya
    */
    private var state: Int = 0
    private lateinit var getDataProductForDelete: ProductModel
    private var idEdit: Int? = null
    private var isEdit: Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        state = intent.getIntExtra(STATE, 0)
        val getDataProduct = intent.getParcelableExtra<ProductModel>(DATA_PRODUCT) ?: null
        if (getDataProduct != null) {
            getDataProductForDelete = getDataProduct
            displayEdit(getDataProduct)
            isEdit = true
            binding.textView10.text = "Edit Item"
        }
        displayButton()
    }

    private fun displayButton() {
        binding.btnBack.setOnClickListener(this)
        binding.btnSave.setOnClickListener(this)
        binding.btnStartCamera.setOnClickListener(this)
        binding.btnGetkodeproductwithqr.setOnClickListener(this)
        binding.btnCalendar.setOnClickListener(this)
        binding.btnDelete.setOnClickListener(this)
    }

    private fun displayEdit(data: ProductModel) {
        binding.apply {
            btnDelete.visibility = View.VISIBLE
            idEdit = data.id
            dataPathImgProduct = data.imageProduct
            edtProductName.setText(data.nameProduct)
            edtQuantityProduct.setText(data.entityProduct.toString())
            edtIdProduct.setText(data.kodeProduct.toString())
            edtDateExpired.setText(data.expiredProduct.toString())
            edtPrice.setText(data.priceProduct)
            imgProduct.setImageBitmap(BitmapFactory.decodeFile(data.imageProduct))
            btnSave.text = "Edit Item"
        }
    }

    private fun displayDelete(dataProduct: ProductModel) {
        val dataImage = dataProduct.imageProduct?.let { ProvideImage.deleteImageByPath(it) }

        if (dataImage.equals("Succes")) {
            viewModel.deleteProduct(dataProduct)
            Toast.makeText(this, "Item Product Delete Succes!", Toast.LENGTH_SHORT).show()
            finish()
        } else {
            Toast.makeText(this, "Item Product Failed Succes!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun displaySave() {
        var nameProduct: String? = null
        var kodeProduct: String? = null
        var entityProduct: String? = null
        var expiredProduct: String? = null
        var priceProduct: String? = null
        binding.apply {
            nameProduct = edtProductName.text.toString()
            kodeProduct = edtIdProduct.text.toString()
            entityProduct = edtQuantityProduct.text.toString()
            expiredProduct = edtDateExpired.text.toString()
            priceProduct = if (edtTotalPrice.text.toString() != "") {
                "${
                    edtTotalPrice.text.toString().toInt() / edtQuantityProduct.text.toString()
                        .toInt()
                }"
            } else {
                edtPrice.text.toString()
            }
        }
        val validatePrice: Boolean =
            binding.edtTotalPrice.text.toString() != "" || binding.edtPrice.text.toString() != ""

        if (nameProduct != null && kodeProduct != null && entityProduct != null && validatePrice) {
            val data = ProductModel(
                id = idEdit ?: 0,
                nameProduct = nameProduct,
                kodeProduct = kodeProduct,
                entityProduct = entityProduct?.toInt(),
                expiredProduct = expiredProduct,
                priceProduct = priceProduct,
                imageProduct = dataPathImgProduct ?: "path://default"
            )
            viewModel.saveProduct(data, isEdit)
            Toast.makeText(this, "Item berhasil di tambahkan!", Toast.LENGTH_SHORT).show()
            when (state) {
                0 -> {
                    finish()
                }

                else -> {
                    startActivity(Intent(this@AddItemActivity, ListProductActivity::class.java))
                    finish()
                }
            }
        } else {
            Toast.makeText(this, "minimal isi dulu Bos!", Toast.LENGTH_SHORT).show()
        }
    }

    //Get Picrure
    private val launcherIntentCameraX = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        if (it.resultCode == CAMERA_X_RESULT) {
            val myFile = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                it.data?.getSerializableExtra("picture", File::class.java)
            } else {
                @Suppress("DEPRECATION") it.data?.getSerializableExtra("picture")
            } as? File
            val isBackCamera = it.data?.getBooleanExtra("isBackCamera", true) as Boolean
            myFile?.let { file ->
                rotateFile(file, isBackCamera)
                binding.imgProduct.setImageBitmap(BitmapFactory.decodeFile(file.path))
                dataPathImgProduct = file.path
            }
        }
    }

    private val luncherIntentKodeProduct = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        if (it.resultCode == PRODUCTID_RESULT) {
            kodeProduct = @Suppress("DEPRECATION") it.data?.getStringExtra("kodeproduct").toString()
            binding.edtIdProduct.setText(kodeProduct)
        }
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.btn_back -> {
                finish()
            }

            R.id.btn_save -> {
                displaySave()
            }

            R.id.btn_startCamera -> {
                NameProduct = binding.edtProductName.text.toString()
                if (NameProduct != null && NameProduct != "") {
                    val intent = Intent(this, Cameractivity::class.java)
                    intent.putExtra(Cameractivity.NAME_PRODUCT, NameProduct)
                    launcherIntentCameraX.launch(intent)
                } else {
                    Toast.makeText(this, "Minilmal isi nama Bos!", Toast.LENGTH_SHORT).show()
                }
            }

            R.id.btn_getkodeproductwithqr -> {
                val intentResult = Intent(this, QrScanActivity::class.java)
                luncherIntentKodeProduct.launch(intentResult)
            }

            R.id.btn_calendar -> {
                datePicker()
            }

            R.id.btn_delete -> {
                displayDelete(getDataProductForDelete)
            }
        }
    }

    override fun onStop() {
        super.onStop()
        binding.btnDelete.visibility = View.GONE
    }

    //datePicker
    private fun datePicker() {
        val dpc = MaterialDatePicker.Builder.datePicker().setTitleText("Selected Expired Date")
            .setSelection(MaterialDatePicker.todayInUtcMilliseconds()).build()
        with(dpc) {
            show(supportFragmentManager, "Tag")
            addOnPositiveButtonClickListener {
                val date = GetDateNow.formatDate(it)
                binding.edtDateExpired.setText(date)
            }
        }
    }


    companion object {
        const val DATA_PRODUCT = "data_product"
        const val STATE = "data_state"
    }
}