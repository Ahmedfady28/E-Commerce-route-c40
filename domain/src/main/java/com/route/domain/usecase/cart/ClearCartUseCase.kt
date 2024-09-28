package com.route.domain.usecase.cart

import com.route.domain.model.ApiResult
import com.route.domain.repositories.CartRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ClearCartUseCase @Inject constructor(
    private val cartRepository: CartRepository
) {
    fun invoke(id: String): Flow<ApiResult<String?>?> {
        return cartRepository.clearCart(id)
    }
}