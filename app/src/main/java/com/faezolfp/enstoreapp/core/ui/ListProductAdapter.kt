package com.faezolfp.enstoreapp.core.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
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
                txtProductID.text = data.kodeProduct
                txtProductquantity.text = data.entityProduct.toString()
                txtProductprice.text = data.priceProduct
                txtProductexpireddate.text = data.expiredProduct

                itemView.setOnClickListener{

                }
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