package com.route.data.dataSourceImpl

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringSetPreferencesKey
import com.route.data.api.WebServices
import com.route.data.api.model.request.AddWishListRequest
import com.route.data.dataSourcesContract.WishlistOnlineDataSource
import com.route.data.dataStore.UserDataStore
import com.route.data.executeApi
import com.route.domain.model.ApiResult
import com.route.domain.model.Product
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class WishlistOnlineDataSourceImp @Inject constructor(
    private val webServices: WebServices,
    @UserDataStore private val userDataStore: DataStore<Preferences>
) : WishlistOnlineDataSource {
    private val favoriteProductKey = stringSetPreferencesKey("favProducts")

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
            storeProductInFavoriteSet(id)
            webServices.addToWishList(AddWishListRequest(productId = id)).data
        }
    }

    override fun removeFromWishList(id: String): Flow<ApiResult<List<String>?>> {
        return executeApi {
            removeProductInFavoriteSet(id)
            webServices.removeFromWishList(id).data
        }
    }

    private suspend fun storeProductInFavoriteSet(idProduct: String?) {
        userDataStore.edit { preferences ->
            val currentSet = preferences[favoriteProductKey]?.toMutableSet() ?: mutableSetOf()
            idProduct?.let { currentSet.add(it) }
            preferences[favoriteProductKey] = currentSet.toSet()
        }
    }

    private suspend fun removeProductInFavoriteSet(idProduct: String?) {
        userDataStore.edit { preferences ->
            val currentSet = preferences[favoriteProductKey]?.toMutableSet() ?: mutableSetOf()
            currentSet.remove(idProduct)
            preferences[favoriteProductKey] = currentSet.toSet()
        }
    }
}