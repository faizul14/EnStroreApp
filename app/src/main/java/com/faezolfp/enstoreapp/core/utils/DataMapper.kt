package com.faezolfp.enstoreapp.core.utils

import android.app.Application
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.os.Environment
import com.faezolfp.enstoreapp.R
import com.faezolfp.enstoreapp.core.data.local.entity.ProductEntity
import com.faezolfp.enstoreapp.core.domain.model.ProductModel
import java.io.File
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.util.Locale

object DataMapper {
    fun mapperListFromEntityToModel(input: List<ProductEntity>): List<ProductModel> {
        val dataResult = ArrayList<ProductModel>()
        input.map {
            val data = ProductModel(
                id = it.id,
                nameProduct = it.nameproduct,
                kodeProduct = it.kodeproduct,
                entityProduct = it.entityproduct,
                expiredProduct = it.expiredproduct,
                priceProduct = it.priceproduct,
                imageProduct = it.imageproduct,
            )
            dataResult.add(data)
        }
        return dataResult
    }

    fun mapperFromEntityToModel(input: ProductEntity): ProductModel {
        return ProductModel(
            id = input.id,
            nameProduct = input.nameproduct,
            kodeProduct = input.kodeproduct,
            entityProduct = input.entityproduct,
            expiredProduct = input.expiredproduct,
            priceProduct = input.priceproduct,
            imageProduct = input.imageproduct,
        )
    }

    fun mapperFromModelToEntity(input: ProductModel): ProductEntity {
        return ProductEntity(
            id = input.id,
            nameproduct = input.nameProduct,
            kodeproduct = input.kodeProduct,
            entityproduct = input.entityProduct,
            expiredproduct = input.expiredProduct,
            priceproduct = input.priceProduct,
            imageproduct = input.imageProduct,
        )
    }

    //FOR CAMERA
    private const val FILENAME_FORMAT = "dd-MMM-yyyy"

    val timeStamp: String = SimpleDateFormat(
        FILENAME_FORMAT, Locale.US
    ).format(System.currentTimeMillis())

    fun createTempFile(context: Context): File {
        val storageDir: File? = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile(timeStamp, ".jpg", storageDir)
    }

    fun createFile(application: Application, productName: String): File {
        val mediaDir = application.externalMediaDirs.firstOrNull()?.let {
            File(it, application.resources.getString(R.string.app_name)).apply { mkdirs() }
        }

        val outputDirectory =
            if (mediaDir != null && mediaDir.exists()) mediaDir else application.filesDir

        return File(outputDirectory, "$productName.jpg")
    }

    fun rotateFile(file: File, isBackCamera: Boolean = false) {
        val matrix = Matrix()
        val bitmap = BitmapFactory.decodeFile(file.path)
        val rotation = if (isBackCamera) 90f else -90f
        matrix.postRotate(rotation)
        if (!isBackCamera) {
            matrix.postScale(-1f, 1f, bitmap.width / 2f, bitmap.height / 2f)
        }
        val result = Bitmap.createBitmap(bitmap, 0, 0, bitmap.width, bitmap.height, matrix, true)
        result.compress(Bitmap.CompressFormat.JPEG, 100, FileOutputStream(file))
    }

}