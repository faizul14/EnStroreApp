package com.faezolfp.enstoreapp.core.utils

import com.faezolfp.enstoreapp.core.data.local.entity.ProductEntity
import com.faezolfp.enstoreapp.core.domain.model.ProductModel

object DataMapper {
    fun mapperListFromEntityToModel(input: List<ProductEntity>): List<ProductModel>{
        val dataResult = ArrayList<ProductModel>()
        input.map {
            val data = ProductModel(
                id = it.id,
                nameProduct = it.nameProduct,
                kodeProduct = it.kodeProduct,
                entityProduct = it.entityProduct,
                expiredProduct = it.expiredProduct,
                priceProduct = it.priceProduct,
                imageProduct = it.imageProduct,
            )
            dataResult.add(data)
        }
        return dataResult
    }

    fun mapperFromEntityToModel(input: ProductEntity): ProductModel{
            return ProductModel(
                id = input.id,
                nameProduct = input.nameProduct,
                kodeProduct = input.kodeProduct,
                entityProduct = input.entityProduct,
                expiredProduct = input.expiredProduct,
                priceProduct = input.priceProduct,
                imageProduct = input.imageProduct,
            )
    }

    fun mapperFromModelToEntity(input: ProductModel): ProductEntity {
        return ProductEntity(
            id = input.id,
            nameProduct = input.nameProduct,
            kodeProduct = input.kodeProduct,
            entityProduct = input.entityProduct,
            expiredProduct = input.expiredProduct,
            priceProduct = input.priceProduct,
            imageProduct = input.imageProduct,
        )
    }

}