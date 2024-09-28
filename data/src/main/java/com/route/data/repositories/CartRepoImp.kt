package com.route.data.repositories

import com.route.data.dataSourcesContract.CartOnlineDataSource
import com.route.domain.model.ActionCart
import com.route.domain.model.ApiResult
import com.route.domain.repositories.CartRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CartRepoImp @Inject constructor(
    private val onlineDataSource: CartOnlineDataSource
) : CartRepository
{
    override fun getCart(): Flow<ApiResult<ActionCart?>?>
    {
        return onlineDataSource.getCart()
    }

    override fun addToCart(id: String): Flow<ApiResult<ActionCart?>?>
    {
        return onlineDataSource.addToCart(id)
    }

    override fun removeFromCart(id: String): Flow<ApiResult<ActionCart?>?> {
        return onlineDataSource.removeFromCart(id)
    }

    override fun clearCart(id: String): Flow<ApiResult<String?>?> {
        return onlineDataSource.clearCart(id)
    }

    override fun updateCart(id: String, newCount: Int): Flow<ApiResult<ActionCart?>?> {
        return onlineDataSource.updateCart(id, newCount)
    }
}