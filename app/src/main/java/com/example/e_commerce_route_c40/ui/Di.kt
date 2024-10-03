package com.example.e_commerce_route_c40.ui

import com.example.e_commerce_route_c40.ui.fragments.cart.CartAdapter
import com.example.e_commerce_route_c40.ui.fragments.category.CategoriesAdapter
import com.example.e_commerce_route_c40.ui.fragments.category.SubCategoriesAdapter
import com.example.e_commerce_route_c40.ui.fragments.favorite.FavoriteProductAdapter
import com.example.e_commerce_route_c40.ui.fragments.home.adaptors.AdapterMostSeller
import com.example.e_commerce_route_c40.ui.fragments.home.adaptors.BrandAdapter
import com.example.e_commerce_route_c40.ui.fragments.home.adaptors.HomeCategoriesAdapter
import com.example.e_commerce_route_c40.ui.fragments.product.ProductsAdaptor
import com.example.e_commerce_route_c40.ui.fragments.productDetails.ImgsAdaptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.android.scopes.FragmentScoped

@Module
@InstallIn(FragmentComponent::class)
class Di {

    @Provides
    @FragmentScoped
    fun provideCategoriesAdaptor(): CategoriesAdapter = CategoriesAdapter()

    @Provides
    @FragmentScoped
    fun provideSubCategoriesAdaptor(): SubCategoriesAdapter = SubCategoriesAdapter()

    @Provides
    @FragmentScoped
    fun provideProductsAdaptor(): ProductsAdaptor = ProductsAdaptor()

    @Provides
    @FragmentScoped
    fun provideHomeCategoriesAdapter(): HomeCategoriesAdapter = HomeCategoriesAdapter()

    @Provides
    @FragmentScoped
    fun provideBrandAdapter(): BrandAdapter = BrandAdapter()

    @Provides
    @FragmentScoped
    fun provideProductMostSellerAdapter(): AdapterMostSeller = AdapterMostSeller()

    @Provides
    @FragmentScoped
    fun provideCartAdaptor(): CartAdapter = CartAdapter()

    @Provides
    @FragmentScoped
    fun provideFavoriteProductAdapter(): FavoriteProductAdapter = FavoriteProductAdapter()

    @Provides
    @FragmentScoped
    fun provideProductDetailsAdaptor(): ImgsAdaptor = ImgsAdaptor()
}