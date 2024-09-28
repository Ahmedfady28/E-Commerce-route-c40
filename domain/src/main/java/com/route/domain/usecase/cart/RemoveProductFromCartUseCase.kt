package com.route.domain.usecase.cart

import com.route.domain.model.ActionCart
import com.route.domain.model.ApiResult
import com.route.domain.model.Product
import com.route.domain.repositories.CartRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RemoveProductFromCartUseCase @Inject constructor(
    private val cartRepository: CartRepository
) {
    fun invoke(product: Product): Flow<ApiResult<ActionCart?>?> {
        return cartRepository.removeFromCart(product.id ?: "")
    }
}