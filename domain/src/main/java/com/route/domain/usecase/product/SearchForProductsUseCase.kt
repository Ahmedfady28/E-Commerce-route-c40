package com.route.domain.usecase.product


import com.route.domain.model.ApiResult
import com.route.domain.model.Product
import com.route.domain.repositories.ProductsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchForProductsUseCase @Inject constructor(
    private val repository: ProductsRepository
)
{
    fun invoke(search: String): Flow<ApiResult<List<Product>?>>
    {
        return repository.getProducts(search)
    }
}
