package com.route.data.dataSourcesContract

import com.route.domain.model.ActionCart
import com.route.domain.model.ApiResult
import kotlinx.coroutines.flow.Flow

interface CartOnlineDataSource
{
    fun getCart(): Flow<ApiResult<ActionCart?>?>
    fun addToCart(id: String): Flow<ApiResult<ActionCart?>?>
    fun removeFromCart(id: String): Flow<ApiResult<ActionCart?>?>
    fun clearCart(id: String): Flow<ApiResult<String?>?>
    fun updateCart(id: String, newCount: Int): Flow<ApiResult<ActionCart?>?>
}