package com.faezolfp.enstoreapp.core.data.local.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
data class ProductEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int = 0,
    @ColumnInfo(name = "nameProduct")
    val nameProduct: String? = null,
    @ColumnInfo(name = "kodeProduct")
    val kodeProduct: String? = null,
    @ColumnInfo(name = "EntityProduct")
    val entityProduct: Int? = null,
    @ColumnInfo(name = "ExpiredProduct")
    val expiredProduct: String? = null,
    @ColumnInfo(name = "priceProduct")
    val priceProduct: String? = null,
    @ColumnInfo(name = "imageProduct")
    val imageProduct: String? = null
): Parcelable
