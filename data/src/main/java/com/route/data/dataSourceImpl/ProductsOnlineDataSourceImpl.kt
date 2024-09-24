package com.route.data.dataSourceImpl

import com.route.data.api.WebServices
import com.route.data.dataSourcesContract.ProductsOnlineDataSource
import com.route.data.executeApi
import com.route.domain.model.ApiResult
import com.route.domain.model.Product
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ProductsOnlineDataSourceImpl @Inject constructor(
    private val webServices: WebServices) :
    ProductsOnlineDataSource {

    override suspend fun getProducts(
        categoryId: String?,
        brandId: String?,
        keyword: String?,
        sortBy: String?
    ): Flow<ApiResult<List<Product>?>> {

        val response = webServices.getProducts(categoryId, brandId, keyword, sortBy)
        return executeApi {
            response.data?.map { productDto ->
                productDto?.toProduct() ?: Product()
            }
        }
    }

    override suspend fun getSpecificProduct(productId: String): Flow<ApiResult<Product?>> {
        val response = webServices.getSpecificProduct(productId)
        return executeApi {
            response.data?.toProduct()
        }
    }
}