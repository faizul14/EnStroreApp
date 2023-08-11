package com.faezolfp.enstoreapp.core.data.local

import androidx.datastore.preferences.core.Preferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import com.faezolfp.enstoreapp.core.data.local.entity.ProductEntity
import com.faezolfp.enstoreapp.core.data.local.preferences.Sapareferences
import com.faezolfp.enstoreapp.core.data.local.room.EnstoreDao
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSource @Inject constructor(private val enstoreDao: EnstoreDao, private val preferences: Sapareferences) {
    private val executorService: ExecutorService = Executors.newSingleThreadExecutor()
    fun getListDataProduct(): LiveData<List<ProductEntity>> = enstoreDao.getListProduct()
    fun addProduct(product: ProductEntity) =
        executorService.execute { enstoreDao.addProduct(product) }
    fun updateProduct(product: ProductEntity) =
        executorService.execute { enstoreDao.updateProduct(product) }
    fun deleteProduct(product: ProductEntity) =
        executorService.execute { enstoreDao.deleteProduct(product) }
    fun getDataProductByKodeProduct(kode: String) = enstoreDao.getProductByKodeProduct(kode)
    fun getDataProductByNameProduct(name: String) = enstoreDao.getProductByNameProduct("%$name%")

    fun getGreetingText(): LiveData<String>{
        return preferences.getGrettingText().asLiveData()
    }
    suspend fun saveGrettingText(dataGretting: String){
        preferences.saveGrettingText(dataGretting)
    }
}