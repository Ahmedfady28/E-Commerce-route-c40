package com.route.data.api.model.response

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

@Parcelize
class ProductsItem(

    @field:SerializedName("product")
    val product: String? = null,

    @field:SerializedName("price")
    val price: Int? = null,

    @field:SerializedName("count")
    val count: Int? = null,

    @field:SerializedName("_id")
    val id: String? = null
) : Parcelable