package com.route.data.dataSourceImpl

import com.google.gson.Gson
import com.route.data.api.WebServices
import com.route.data.api.model.request.AddCartRequest
import com.route.data.api.model.request.UpdateCartRequest
import com.route.data.dataSourcesContract.CartOnlineDataSource
import com.route.data.executeApi
import com.route.domain.model.ActionCart
import com.route.domain.model.ApiResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CartOnlineDataSourceImp @Inject constructor(
    private val webServices: WebServices,
    private val gson: Gson
) : CartOnlineDataSource
{
    override fun getCart(): Flow<ApiResult<ActionCart?>?> {
        return executeApi {
            webServices.getCart().toActionCart()
        }
    }


    override fun addToCart(id: String): Flow<ApiResult<ActionCart?>?>
    {
        return executeApi {
            val request = AddCartRequest(id)
            webServices.addToCart(request).toActionCart()
        }
    }

    override fun removeFromCart(id: String): Flow<ApiResult<ActionCart?>?> {
        return executeApi {
            webServices.removeFromCart(id).toActionCart()
        }
    }

    override fun clearCart(id: String): Flow<ApiResult<String?>?> {
        return executeApi {
            webServices.clearCart().message
        }
    }

    override fun updateCart(id: String, newCount: Int): Flow<ApiResult<ActionCart?>?> {
        return executeApi {
            webServices.updateCart(UpdateCartRequest(newCount.toString()), id).toActionCart()
        }
    }
}