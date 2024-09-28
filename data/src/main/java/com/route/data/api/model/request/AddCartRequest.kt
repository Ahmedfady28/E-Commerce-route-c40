package com.route.data.api.model.request

import com.google.gson.annotations.SerializedName

data class AddCartRequest
    (
    @field:SerializedName("productId")
    val productId: String? = null
)