package com.faezolfp.enstoreapp.core.ui

import android.content.Intent
import android.content.res.Resources
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.faezolfp.enstoreapp.R
import com.faezolfp.enstoreapp.additem.AddItemActivity
import com.faezolfp.enstoreapp.core.domain.model.ProductModel
import com.faezolfp.enstoreapp.databinding.ItemListProductBinding

class ListProductAdapter : RecyclerView.Adapter<ListProductAdapter.ViewHolder>() {

    private val listProduct = ArrayList<ProductModel>()
    fun setListProduct(data: List<ProductModel>) {
        listProduct.clear()
        listProduct.addAll(data)
        notifyDataSetChanged()
    }

    class ViewHolder(val binding: ItemListProductBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: ProductModel) {
            binding.apply {
                txtProductName.text = data.nameProduct
                txtProductID.text = ": ID${data.kodeProduct}"
                txtProductquantity.text = ": ${data.entityProduct.toString()} Qty"
                txtProductprice.text = ": Rp.${data.priceProduct}"
                txtProductexpireddate.text = ": ${data.expiredProduct}"
                if (data.imageProduct.equals("path://default") || data.imageProduct.equals("local image//:dkdk")) {
                    imageView.setImageResource(R.drawable.img_qr)
                }else{
                    imageView.setImageBitmap(BitmapFactory.decodeFile(data.imageProduct))
                }
            }
            itemView.setOnClickListener {
                val move = Intent(itemView.context, AddItemActivity::class.java)
                move.putExtra(AddItemActivity.DATA_PRODUCT, data)
                itemView.context.startActivity(move)
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            ItemListProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = listProduct.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listProduct[position])
    }
}