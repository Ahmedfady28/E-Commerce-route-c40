package com.route.data.repositories

import com.route.data.dataSourcesContract.BrandsOnlineDataSourse
import com.route.domain.model.ApiResult
import com.route.domain.model.Brand
import com.route.domain.repositories.BrandsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class BrandsRepositoryImpl @Inject constructor(
    private val brandsOnlineDataSourse: BrandsOnlineDataSourse
) : BrandsRepository {
    override fun getBrands(): Flow<ApiResult<List<Brand>?>> {
        return brandsOnlineDataSourse.getBrands()
    }
}