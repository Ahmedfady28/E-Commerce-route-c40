package com.route.data.dataSourcesContract

import com.route.domain.model.ApiResult
import com.route.domain.model.Brand
import kotlinx.coroutines.flow.Flow

interface BrandsOnlineDataSourse {
    fun getBrands(): Flow<ApiResult<List<Brand>?>>
}