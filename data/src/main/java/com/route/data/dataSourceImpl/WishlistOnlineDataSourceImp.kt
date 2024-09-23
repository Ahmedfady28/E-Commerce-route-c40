package com.route.data.dataSourceImpl

import com.route.data.api.WebServices
import com.route.data.api.model.request.AddWishListRequest
import com.route.data.dataSourcesContract.WishlistOnlineDataSource
import com.route.data.executeApi
import com.route.domain.model.ApiResult
import com.route.domain.model.Product
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class WishlistOnlineDataSourceImp @Inject constructor(
    private val webServices: WebServices
) : WishlistOnlineDataSource {
    override fun getWishlist(): Flow<ApiResult<List<Product>?>> {
        return executeApi {
            webServices.getWishlist()
                .data?.map {productDto ->
                    productDto?.toProduct() ?: Product()
                }
        }
    }

    override fun addToWishList(id: String?): Flow<ApiResult<List<String>?>> {
        return executeApi {
            webServices.addToWishList(AddWishListRequest(productId = id)).data
        }
    }

    override fun removeFromWishList(id: String): Flow<ApiResult<List<String>?>> {
        return executeApi {
            webServices.removeFromWishList(id).data
        }
    }

}