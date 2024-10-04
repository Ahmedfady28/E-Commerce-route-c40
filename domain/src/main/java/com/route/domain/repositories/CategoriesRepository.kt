package com.route.domain.repositories

import com.route.domain.model.ApiResult
import com.route.domain.model.Category
import com.route.domain.model.SubCategory
import kotlinx.coroutines.flow.Flow

interface CategoriesRepository {
     fun getCategories(): Flow<ApiResult<List<Category>?>>
     fun getSubCategories(categoryId:String):Flow<ApiResult<List<SubCategory>?>>

    fun getSpecificCategory(
        categoryId: String
    ): Flow<ApiResult<Category?>>
}