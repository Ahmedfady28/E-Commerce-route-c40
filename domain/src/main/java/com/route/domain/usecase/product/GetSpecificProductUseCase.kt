package com.route.domain.usecase.product

import com.route.domain.repositories.ProductsRepository
import javax.inject.Inject

class GetSpecificProductUseCase @Inject constructor(
    private val productsRepository: ProductsRepository
){
    fun invoke(productId: String) = productsRepository.getSpecificProduct(productId)
}