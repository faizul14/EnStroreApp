package com.faezolfp.enstoreapp.core.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.faezolfp.enstoreapp.core.data.local.entity.ProductEntity

@Database(entities = [ProductEntity::class], version = 1, exportSchema = false)
abstract class EnstoreDatabase : RoomDatabase() {

    abstract fun enstoreDao(): EnstoreDao

}