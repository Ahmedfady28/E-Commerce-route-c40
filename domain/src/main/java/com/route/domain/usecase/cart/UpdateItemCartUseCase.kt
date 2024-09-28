package com.route.domain.usecase.cart

import com.route.domain.model.ActionCart
import com.route.domain.model.ApiResult
import com.route.domain.repositories.CartRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UpdateItemCartUseCase @Inject constructor(
    private val cartRepository: CartRepository
) {
    fun invoke(id: String, newCount: Int): Flow<ApiResult<ActionCart?>?> {
        return cartRepository.updateCart(id, newCount)
    }
}