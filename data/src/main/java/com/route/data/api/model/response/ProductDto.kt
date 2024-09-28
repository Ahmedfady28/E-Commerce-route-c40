package com.route.data.api.model.response

import com.google.gson.annotations.SerializedName
import com.route.domain.model.Product

data class ProductDto(

	@field:SerializedName("sold")
	val sold: Int? = null,

	@field:SerializedName("images")
	val images: List<String?>? = null,

	@field:SerializedName("quantity")
	val quantity: Int? = null,

	@field:SerializedName("imageCover")
	val imageCover: String? = null,

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("ratingsQuantity")
	val ratingsQuantity: Int? = null,

	@field:SerializedName("ratingsAverage")
	val ratingsAverage: Double? = null,

	@field:SerializedName("createdAt")
	val createdAt: String? = null,

	@field:SerializedName("price")
	val price: Int? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("subcategory")
	val subcategory: List<CategoryDto?>? = null,

	@field:SerializedName("category")
	val category: CategoryDto? = null,

	@field:SerializedName("brand")
	val brand: BrandDto? = null,

	@field:SerializedName("slug")
	val slug: String? = null,

	@field:SerializedName("updatedAt")
    val updatedAt: String? = null,

    @field:SerializedName("count")
    val count: Int? = null
) {

  fun toProduct() : Product{
		return Product(
			sold = sold,
			images = images,
			quantity = quantity,
			imageCover = imageCover,
			description = description,
			title = title,
			ratingsQuantity = ratingsQuantity,
			ratingsAverage = ratingsAverage,
			createdAt = createdAt,
			price = price,
			id = id,
			subcategory = subcategory?.map {
				categoryDto -> categoryDto?.toSubCategory() },
			category = category?.toCategory(),
			brand = brand?.toBrand(),
			slug = slug,
            updatedAt = updatedAt,
            count = count
		)
	}
}