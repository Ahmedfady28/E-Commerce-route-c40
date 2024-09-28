package com.route.data.api.model.response.cart

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.route.domain.model.Product
import com.route.domain.model.ProductItemCart
import kotlinx.parcelize.Parcelize

@Parcelize
data class ProductsAddItemCartDto(
    @field:SerializedName("product")
    val product: String? = null,
) : Parcelable, ContractProductItemCart() {
    override fun toProductItemCart(): ProductItemCart {
        return ProductItemCart(
            product = Product(id = product),
            price = price,
            count = count,
            id = id
        )
    }
}
