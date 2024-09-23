package com.route.data.dataSourcesContract

import com.route.domain.model.ApiResult
import com.route.domain.model.Product
import kotlinx.coroutines.flow.Flow

interface WishlistOnlineDataSource {

     fun getWishlist(): Flow<ApiResult<List<Product>?>>
     fun addToWishList(id: String?): Flow<ApiResult<List<String>?>>
     fun removeFromWishList(id: String): Flow<ApiResult<List<String>?>>
}