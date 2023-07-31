package com.faezolfp.enstoreapp.core.domain.model

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey

data class ProductModel(
    val id: Int = 0,
    val nameProduct: String? = null,
    val kodeProduct: String? = null,
    val entityProduct: Int? = null,
    val expiredProduct: String? = null,
    val priceProduct: String? = null,
    val imageProduct: String? = null
)
