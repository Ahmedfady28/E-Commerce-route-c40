package com.route.domain.usecase.wishList

import com.route.domain.model.ApiResult
import com.route.domain.repositories.WishlistRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RemoveProductFromWishListUseCase @Inject constructor(
    private val wishlistRepository: WishlistRepository
) {
    fun invoke(id: String): Flow<ApiResult<List<String>?>> {
        return wishlistRepository.removeFromWishList(id)
    }
}