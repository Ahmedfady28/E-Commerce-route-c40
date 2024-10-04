package com.route.data.repositories

import com.route.data.dataSourcesContract.CategoriesOnlineDataSource
import com.route.domain.model.ApiResult
import com.route.domain.model.Category
import com.route.domain.model.SubCategory
import com.route.domain.repositories.CategoriesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CategoriesRepoImpl @Inject constructor(
    private val onlineDataSource: CategoriesOnlineDataSource
) : CategoriesRepository
{

    override fun getCategories(): Flow<ApiResult<List<Category>?>>
    {
        return onlineDataSource.getCategories()
    }

    override fun getSubCategories(categoryId: String): Flow<ApiResult<List<SubCategory>?>>
    {
        return onlineDataSource.getSubCategories(categoryId)
    }

    override fun getSpecificCategory(categoryId: String): Flow<ApiResult<Category?>>
    {
        return onlineDataSource.getSpecificCategory(categoryId)
    }
}