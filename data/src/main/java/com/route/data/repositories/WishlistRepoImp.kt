package com.route.data.repositories

import com.route.data.dataSourcesContract.WishlistOnlineDataSource
import com.route.domain.model.ApiResult
import com.route.domain.model.Product
import com.route.domain.repositories.WishlistRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class WishlistRepoImp @Inject constructor(
    private val onlineDataSource: WishlistOnlineDataSource
):WishlistRepository {
    override fun getWishlist(): Flow<ApiResult<List<Product>?>> {
        return onlineDataSource.getWishlist()
    }

    override fun addToWishList(id: String?): Flow<ApiResult<List<String>?>> {
        return onlineDataSource.addToWishList(id)
    }

    override fun removeFromWishList(id: String): Flow<ApiResult<List<String>?>> {
        return onlineDataSource.removeFromWishList(id)
    }
}