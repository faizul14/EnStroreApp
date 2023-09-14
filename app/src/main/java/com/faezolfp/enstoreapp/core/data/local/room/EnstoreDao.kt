package com.faezolfp.enstoreapp.core.data.local.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.faezolfp.enstoreapp.core.data.local.entity.ProductEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface EnstoreDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addProduct(productEntity: ProductEntity)

    @Update
    suspend fun updateProduct(productEntity: ProductEntity)

    @Delete
    fun deleteProduct(productEntity: ProductEntity)

    @Query("SELECT * FROM productentity")
    fun getListProduct(): Flow<List<ProductEntity>>

    @Query("SELECT * FROM productentity WHERE kodeProduct = :kode")
    fun getProductByKodeProduct(kode: String): Flow<List<ProductEntity>>

    @Query("SELECT * FROM productentity WHERE nameproduct LIKE :name")
    fun getProductByNameProduct(name: String): Flow<List<ProductEntity>>
}