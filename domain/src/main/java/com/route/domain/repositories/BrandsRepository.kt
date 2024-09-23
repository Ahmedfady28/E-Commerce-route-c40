package com.route.domain.repositories

import com.route.domain.model.ApiResult
import com.route.domain.model.Brand
import kotlinx.coroutines.flow.Flow

interface BrandsRepository {
    fun getBrands(): Flow<ApiResult<List<Brand>?>>
}