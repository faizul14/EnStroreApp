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
    var id: Int = 0,
    @ColumnInfo(name = "nameproduct")
    var nameproduct: String? = null,
    @ColumnInfo(name = "kodeproduct")
    var kodeproduct: String? = null,
    @ColumnInfo(name = "entityproduct")
    var entityproduct: Int? = null,
    @ColumnInfo(name = "expiredproduct")
    var expiredproduct: String? = null,
    @ColumnInfo(name = "priceproduct")
    var priceproduct: String? = null,
    @ColumnInfo(name = "imageproduct")
    var imageproduct: String? = null
): Parcelable
