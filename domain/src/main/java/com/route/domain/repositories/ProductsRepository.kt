package com.route.domain.repositories

import com.route.domain.model.ApiResult
import com.route.domain.model.Product
import kotlinx.coroutines.flow.Flow

interface ProductsRepository {
    fun getProducts(
        categoryId: String? = null,
        brandId: String? = null,
        keyword: String? = null,
        sortBy: String? = null,
        search: String? = null
    ): Flow<ApiResult<List<Product>?>>

    fun getSpecificProduct(
        productId:String
    ): Flow<ApiResult<Product?>>
}