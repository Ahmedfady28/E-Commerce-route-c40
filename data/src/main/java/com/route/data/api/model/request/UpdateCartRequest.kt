package com.route.data.api.model.request

import com.google.gson.annotations.SerializedName

data class UpdateCartRequest(
    @field:SerializedName("count")
    val count: String? = null

)