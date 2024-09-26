package com.route.data.dataSourceImpl

import com.route.data.api.WebServices
import com.route.data.api.model.request.AddCartRequest
import com.route.data.api.model.request.AddWishListRequest
import com.route.data.dataSourcesContract.CartOnlineDataSource
import com.route.data.executeApi
import com.route.domain.model.ApiResult
import com.route.domain.model.Product
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CartOnlineDataSourceImp @Inject constructor(
    private val webServices: WebServices
) : CartOnlineDataSource
{
    override fun getCart(): Flow<ApiResult<List<Product>?>>
    {
        return executeApi {
            webServices.getCart()
                .data?.map { productDto ->
                    productDto?.toProduct() ?: Product()
                }
        }
    }

    override fun addToCart(id: String?): Flow<ApiResult<List<String>?>>
    {
        return executeApi {
            webServices.addToCart(AddCartRequest(productId = id)).data
        }

    }
}