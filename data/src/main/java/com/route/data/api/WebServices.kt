package com.route.data.api

import com.route.data.api.model.request.AddWishListRequest
import com.route.data.api.model.response.BaseResponse
import com.route.data.api.model.response.BrandDto
import com.route.data.api.model.response.CategoriesResponse
import com.route.data.api.model.response.LoginRequest
import com.route.data.api.model.response.LoginResponse
import com.route.data.api.model.response.ProductDto
import com.route.data.api.model.response.ProductsResponse
import com.route.data.api.model.response.SignUpRequest
import com.route.data.api.model.response.SignUpResponse
import com.route.data.api.model.response.WishListResponse
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query


interface WebServices {

    @GET("api/v1/categories")
    suspend fun getCategories():CategoriesResponse

    @GET("api/v1/categories/{catId}/subcategories")
    suspend fun getSubCategories(
        @Path("catId")categoryId:String
    ):CategoriesResponse

    @GET("api/v1/products")
    suspend fun getProducts(
        @Query("category") categoryId: String? = null,
        @Query("brand") brandId: String? = null,
        @Query("keyword") keyword: String? = null,
        @Query("sort") sortBy: String? = null,
    ): ProductsResponse

    @POST("api/v1/auth/signin")
    suspend fun login(
        @Body loginRequest: LoginRequest
    ): LoginResponse


    @POST("api/v1/auth/signup")
    suspend fun signUp(
        @Body signUpRequest: SignUpRequest
    ): SignUpResponse

    @GET("api/v1/wishlist")
    suspend fun getWishlist():WishListResponse

    @POST("api/v1/wishlist")
    suspend fun addToWishList(@Body body:AddWishListRequest):BaseResponse<List<String>?>

    @DELETE("api/v1/wishlist/{id}")
    suspend fun removeFromWishList(
        @Path("id") id: String
    ): BaseResponse<List<String>?>

    @GET("/api/v1/brands")
    suspend fun getBrands(): BaseResponse<List<BrandDto>>

    @GET("api/v1/products/{id}")
    suspend fun getSpecificProduct(
        @Path("id") id: String
    ): BaseResponse<ProductDto>

}