package com.route.data.api.model.response.cart

import com.google.gson.annotations.SerializedName
import com.route.domain.model.ProductItemCart


abstract class ContractProductItemCart(
    @field:SerializedName("price")
    val price: Int? = null,

    @field:SerializedName("count")
    val count: Int? = null,

    @field:SerializedName("_id")
    val id: String? = null
) {
    abstract fun toProductItemCart(): ProductItemCart
}