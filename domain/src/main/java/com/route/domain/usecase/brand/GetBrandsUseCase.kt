package com.route.domain.usecase.brand

import com.route.domain.model.ApiResult
import com.route.domain.model.Brand
import com.route.domain.repositories.BrandsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetBrandsUseCase @Inject constructor(
    private val brandsRepository: BrandsRepository
) {
    fun invoke(): Flow<ApiResult<List<Brand>?>> {
        return brandsRepository.getBrands()
    }
}