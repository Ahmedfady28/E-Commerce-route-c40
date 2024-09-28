package com.route.data.api.model.response.cart

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.route.domain.model.ActionCart
import com.route.domain.model.ProductItemCart
import kotlinx.parcelize.Parcelize

@Parcelize
data class ResponseCart<ItemCartDto : ContractProductItemCart>(
    @field:SerializedName("data")
    val dataCart: DataCart<ItemCartDto>? = null,

    @field:SerializedName("numOfCartItems")
    val numOfCartItems: Int? = null,

    @field:SerializedName("cartId")
    val cartId: String? = null,

    @field:SerializedName("status")
    val status: String? = null
) : Parcelable {

    fun toActionCart(): ActionCart {
        return ActionCart(
            numOfCartItems = numOfCartItems,
            cartId = cartId,
            status = status,
            cartOwner = dataCart?.cartOwner,
            createdAt = dataCart?.createdAt,
            totalCartPrice = dataCart?.totalCartPrice,
            v = dataCart?.v,
            id = dataCart?.id,
            products = convertToProductItemCart(dataCart?.products), // Directly map products here
            updatedAt = dataCart?.updatedAt,
        )
    }

    private fun convertToProductItemCart(list: List<ItemCartDto?>?): List<ProductItemCart>? {
        return list?.map { it?.toProductItemCart()!! }
    }
}
