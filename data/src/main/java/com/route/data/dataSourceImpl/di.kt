package com.route.data.dataSourceImpl

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.google.gson.Gson
import com.route.data.dataSourcesContract.AuthOfflineDataSource
import com.route.data.dataSourcesContract.AuthOnlineDataSource
import com.route.data.dataSourcesContract.CartOnlineDataSource
import com.route.data.dataSourcesContract.CategoriesOnlineDataSource
import com.route.data.dataSourcesContract.ProductsOnlineDataSource
import com.route.data.dataSourcesContract.WishlistOnlineDataSource
import com.route.data.dataStore.UserDataStore
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import com.route.data.dataSourcesContract.BrandsOnlineDataSourse as BrandsOnlineDataSourse1


@Module
@InstallIn(ViewModelComponent::class)
abstract class DataSourceBinder{

    @Binds
    abstract fun bindCategoriesOnlineDataSource(
        categoriesOnlineDataSourceImpl: CategoriesOnlineDataSourceImpl
    ): CategoriesOnlineDataSource

    @Binds
    abstract fun bindAuthOnlineDataSource(
        datasourceImpl: AuthOnlineDataSourceImpl
    ): AuthOnlineDataSource

    @Binds
    abstract fun bindProductsDataSource(
        datasourceImpl: ProductsOnlineDataSourceImpl
    ): ProductsOnlineDataSource

    @Binds
    abstract fun bindBrandsDataSource(
        datasourceImpl: BrandsOnlineDataSourseImpl
    ): BrandsOnlineDataSourse1

    @Binds
    abstract fun bindOnlineDataSource(
        wishlistOnlineDataSourceImpl: WishlistOnlineDataSourceImp
    ): WishlistOnlineDataSource

    @Binds
    abstract fun bindCartOnlineDataSource(
        cartOnlineDataSourceImp: CartOnlineDataSourceImp
    ): CartOnlineDataSource


}

@Module
@InstallIn(SingletonComponent::class)
object OfflineDataSourceModule{
    @Provides
    @Singleton
    fun provideAuthOfflineDataSource(
        @UserDataStore userDataStore: DataStore<Preferences>,
        gson: Gson

    ): AuthOfflineDataSource{
        return AuthOfflineDataSourceImpl(
            userDataStore,
            gson
        )
    }
}
