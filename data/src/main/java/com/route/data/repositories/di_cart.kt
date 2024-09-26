package com.route.data.repositories


import com.route.domain.repositories.CartRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent


@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoriesBinderCart
{
    @Binds
    abstract fun bindCartRepo(
        cartRepoImp: CartRepoImp
    ): CartRepository

}