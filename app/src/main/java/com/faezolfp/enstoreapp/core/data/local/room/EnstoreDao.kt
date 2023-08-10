package com.faezolfp.enstoreapp.core.data.local.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.faezolfp.enstoreapp.core.data.local.entity.ProductEntity

@Dao
interface EnstoreDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addProduct(productEntity: ProductEntity)

    @Update
    fun updateProduct(productEntity: ProductEntity)

    @Delete
    fun deleteProduct(productEntity: ProductEntity)

    @Query("SELECT * FROM productentity")
    fun getListProduct(): LiveData<List<ProductEntity>>

    @Query("SELECT * FROM productentity WHERE kodeProduct = :kode")
    fun getProductByKodeProduct(kode: String): LiveData<List<ProductEntity>>

    @Query("SELECT * FROM productentity WHERE nameproduct LIKE :name")
    fun  getProductByNameProduct(name: String): LiveData<List<ProductEntity>>
}