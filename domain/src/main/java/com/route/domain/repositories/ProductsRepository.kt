package com.route.domain.repositories

import com.route.domain.model.ApiResult
import com.route.domain.model.Product
import kotlinx.coroutines.flow.Flow

interface ProductsRepository {
    suspend fun getProducts(
        categoryId: String? = null,
        brandId: String? = null,
        keyword: String? = null,
        sortBy: String?
    ): Flow<ApiResult<List<Product>?>>
}