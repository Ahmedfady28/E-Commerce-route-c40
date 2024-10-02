package com.route.domain.usecase.product

import android.util.Log
import com.route.domain.model.ApiResult
import com.route.domain.model.Product
import com.route.domain.repositories.ProductsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class GetProductsUseCase @Inject constructor(
    private val productsRepository: ProductsRepository
) {
    fun invoke(
        categoryId: String? = null,
        brandId: String? = null,
        keyword: String? = null,
        sortBy: String? = null,
        page: Int? = null,
        limit: Int? = null
    ): Flow<ApiResult<List<Product>?>> {
        Log.e("GetProductsUseCase categoryId", categoryId.toString())
        return productsRepository.getProducts(categoryId, brandId, keyword, sortBy, page, limit)
    }
}