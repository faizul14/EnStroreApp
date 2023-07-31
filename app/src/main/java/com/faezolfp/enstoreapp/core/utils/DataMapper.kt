package com.faezolfp.enstoreapp.core.utils

import com.faezolfp.enstoreapp.core.data.local.entity.ProductEntity
import com.faezolfp.enstoreapp.core.domain.model.ProductModel

object DataMapper {
    fun mapperListFromEntityToModel(input: List<ProductEntity>): List<ProductModel>{
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

    fun mapperFromEntityToModel(input: ProductEntity): ProductModel{
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

}