package com.route.data.api.model.response.cart

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.route.data.api.model.response.ProductDto
import com.route.domain.model.ProductItemCart
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
data class ProductsItemCartDto(
    @field:SerializedName("product")
    val product: @RawValue ProductDto? = null,

    ) : Parcelable, ContractProductItemCart() {
    override fun toProductItemCart(): ProductItemCart {
        return ProductItemCart(
            product = product?.toProduct(),
            price = price,
            count = count,
            id = id
        )
    }
}
