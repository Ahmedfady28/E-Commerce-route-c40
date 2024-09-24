package com.example.e_commerce_route_c40.ui

import com.example.e_commerce_route_c40.adapters.HomeCategoriesAdapter
import com.example.e_commerce_route_c40.ui.fragments.category.CategoriesAdapter
import com.example.e_commerce_route_c40.ui.fragments.category.SubCategoriesAdapter
import com.example.e_commerce_route_c40.ui.fragments.home.BrandAdapter
import com.example.e_commerce_route_c40.ui.fragments.product.ProductsAdaptor
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
}