package com.route.data.repositories

import com.route.data.dataSourcesContract.CartOnlineDataSource
import com.route.domain.model.ApiResult
import com.route.domain.model.Product
import com.route.domain.repositories.CartRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CartRepoImp @Inject constructor(
    private val onlineDataSource: CartOnlineDataSource
) : CartRepository
{
    override fun getCart(): Flow<ApiResult<List<Product>?>>
    {
        return onlineDataSource.getCart()
    }

    override fun addToCart(id: String?): Flow<ApiResult<List<String>?>>
    {
        return onlineDataSource.addToCart(id)
    }
}