package com.route.data.dataSourceImpl

import com.route.data.api.WebServices
import com.route.data.dataSourcesContract.CategoriesOnlineDataSource
import com.route.data.executeApi
import com.route.domain.model.ApiResult
import com.route.domain.model.Category
import com.route.domain.model.SubCategory
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CategoriesOnlineDataSourceImpl @Inject constructor(
    private val webServices: WebServices
) :CategoriesOnlineDataSource {

    override fun getCategories(): Flow<ApiResult<List<Category>?>> {
        return executeApi {
            webServices.getCategories()
                .data?.map {categoryDto ->
                    categoryDto?.toCategory() ?: Category()
                }
        }
    }

    override fun getSubCategories(categoryId: String): Flow<ApiResult<List<SubCategory>?>> {
        return executeApi {
            webServices.getSubCategories(categoryId)
                .data?.map {categoryDto->
                    categoryDto?.toSubCategory() ?: SubCategory()
                }
        }
    }

    override fun getSpecificCategory(categoryId: String): Flow<ApiResult<Category?>>
    {
        return executeApi {
            val response = webServices.getSpecificCategory(categoryId)
            response.data?.toCategory()
        }
    }
}