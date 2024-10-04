package com.route.domain.usecase.category

import com.route.domain.repositories.CategoriesRepository
import javax.inject.Inject

class GetSpecificCategoryUseCase @Inject constructor(
    private val categoriesRepository: CategoriesRepository
)
{
    fun invoke(categoryId: String) = categoriesRepository.getSpecificCategory(categoryId)
}