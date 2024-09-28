package com.route.domain.model


data class ActionCart(

    val numOfCartItems: Int? = null,

    val cartId: String? = null,

    val status: String? = null,

    val message: String? = null,

    val cartOwner: String? = null,

    val createdAt: String? = null,

    val totalCartPrice: Int? = null,

    val v: Int? = null,

    val id: String? = null,

    val products: List<ProductItemCart>? = null,

    val updatedAt: String? = null

)

data class ProductItemCart(

    val product: Product? = null,

    val price: Int? = null,

    val count: Int? = null,

    val id: String? = null
)
