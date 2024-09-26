package com.route.data.dataSourcesContract

import com.route.domain.model.ApiResult
import com.route.domain.model.Product
import kotlinx.coroutines.flow.Flow

interface CartOnlineDataSource
{
    fun getCart(): Flow<ApiResult<List<Product>?>>
    fun addToCart(id: String?): Flow<ApiResult<List<String>?>>
}