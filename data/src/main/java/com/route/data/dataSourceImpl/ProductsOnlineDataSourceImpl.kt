package com.route.data.dataSourceImpl

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.stringSetPreferencesKey
import com.route.data.api.WebServices
import com.route.data.dataSourcesContract.ProductsOnlineDataSource
import com.route.data.dataStore.UserDataStore
import com.route.data.executeApi
import com.route.domain.model.ApiResult
import com.route.domain.model.Product
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ProductsOnlineDataSourceImpl @Inject constructor(
    private val webServices: WebServices,
    @UserDataStore private val userDataStore: DataStore<Preferences>
) :
    ProductsOnlineDataSource {
    private val favoriteProductKey = stringSetPreferencesKey("favProducts")


    override fun getProducts(
        categoryId: String?,
        brandId: String?,
        keyword: String?,
        sortBy: String?,
        page: Int?,
        limit: Int?
    ): Flow<ApiResult<List<Product>?>>
    {
        return executeApi {
            val response = webServices.getProducts(categoryId, brandId, keyword, sortBy, page, limit)
            val favoriteProducts = getProductFavoriteSet().first()
            response.data?.map { productDto ->
                val isFavorite = favoriteProducts.contains(productDto?.id)
                productDto?.toProduct(isFavorite) ?: Product(isLiked = isFavorite)
            }
        }

    }

    override fun getSpecificProduct(productId: String): Flow<ApiResult<Product?>> {
        return executeApi {
            val response = webServices.getSpecificProduct(productId)
            response.data?.toProduct()
        }
    }
    private fun getProductFavoriteSet() = userDataStore.data.map { preferences ->
        preferences[favoriteProductKey] ?: emptySet()
    }
}