package com.route.data.api.model.response

import com.google.gson.annotations.SerializedName

data class CartResponse
    (
    @field:SerializedName("metadata")
    val metadata: PaginationInfo? = null,

    @field:SerializedName("data")
    val data: List<ProductDto?>? = null,

    @field:SerializedName("results")
    val results: Int? = null
)