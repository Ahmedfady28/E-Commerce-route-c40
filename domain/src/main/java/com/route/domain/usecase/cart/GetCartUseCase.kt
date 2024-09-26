package com.route.domain.usecase.cart

import com.route.domain.model.ApiResult
import com.route.domain.model.Product
import com.route.domain.repositories.CartRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCartUseCase @Inject constructor(
    private val cartRepository: CartRepository
)
{
    fun invoke(): Flow<ApiResult<List<Product>?>>
    {
        return cartRepository.getCart()
    }
}