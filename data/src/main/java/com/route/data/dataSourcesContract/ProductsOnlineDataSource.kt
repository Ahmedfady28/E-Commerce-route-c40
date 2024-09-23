package com.route.data.dataSourcesContract

import com.route.domain.model.ApiResult
import com.route.domain.model.Product
import kotlinx.coroutines.flow.Flow

interface ProductsOnlineDataSource {
    suspend fun getProducts(
        categoryId: String? = null,
        brandId: String? = null,
        keyword: String? = null,
        sortBy: String? = null
    ): Flow<ApiResult<List<Product>?>>

}