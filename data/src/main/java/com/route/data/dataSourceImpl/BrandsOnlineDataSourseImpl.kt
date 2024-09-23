package com.route.data.dataSourceImpl

import com.route.data.api.WebServices
import com.route.data.dataSourcesContract.BrandsOnlineDataSourse
import com.route.data.executeApi
import com.route.domain.model.ApiResult
import com.route.domain.model.Brand
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class BrandsOnlineDataSourseImpl @Inject constructor(
    private val webServices: WebServices
) : BrandsOnlineDataSourse {
    override fun getBrands(): Flow<ApiResult<List<Brand>?>> {
        return executeApi {
            webServices.getBrands().data?.map { brandDto ->
                brandDto.toBrand()
            }
        }
    }
}