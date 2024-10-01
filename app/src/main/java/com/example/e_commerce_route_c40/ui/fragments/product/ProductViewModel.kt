package com.example.e_commerce_route_c40.ui.fragments.product

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import com.example.e_commerce_route_c40.base.BaseViewModel
import com.route.data.api.interceptor.IODispatcher
import com.route.domain.model.Brand
import com.route.domain.model.Product
import com.route.domain.model.SubCategory
import com.route.domain.usecase.cart.AddProductToCartUseCase
import com.route.domain.usecase.product.GetProductsUseCase
import com.route.domain.usecase.wishList.AddProductToWishListUseCase
import com.route.domain.usecase.wishList.RemoveProductFromWishListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

@HiltViewModel
class ProductViewModel @Inject constructor(
    private val productsUseCase: GetProductsUseCase,
    private val addToWishListUseCase: AddProductToWishListUseCase,
    private val removeProductFromWishListUseCase: RemoveProductFromWishListUseCase,
    private val addToCartUseCase: AddProductToCartUseCase,
    @IODispatcher override val coroutineContext: CoroutineContext,
    savedStateHandle: SavedStateHandle,
) : BaseViewModel(),CoroutineScope {

    val productsLiveData = MutableLiveData<List<Product>?>()
    val productWishListUpdatePosition = MutableLiveData<Int>()

    private val subCategory: SubCategory? = savedStateHandle["subCategory"]
    private val brand: Brand? = savedStateHandle["brand"]

    private fun getProductsByCategory() {
        launch {
            productsUseCase.invoke(categoryId = subCategory?.id)
                .collect { res ->
                    handleCollectScope(res) { dataList ->
                        productsLiveData.postValue(dataList)
                    }
                }
        }

    }

    private fun getAllProducts() {
        launch {
            productsUseCase.invoke()
                .collect { res ->
                    handleCollectScope(res) { dataList ->
                        productsLiveData.postValue(dataList)
                    }
                }
        }
    }

    fun getProducts() {
        if (brand == null) {//&& subCategory== null
            getAllProducts()
            return
        }
//        if(subCategory != null) {
//            getProductsByCategory()
//            return
//        }
        if (brand != null) {
            getProductsByBrand(brand)
            return
        }
    }

    fun getProductsByKey(key: String) {
        launch {
            productsUseCase.invoke(keyword = key)
                .collect { res ->
                    handleCollectScope(res) { dataList ->
                        productsLiveData.postValue(dataList)
                    }
                }
        }
    }
    private fun getProductsByBrand(brand: Brand) {
        launch {
            productsUseCase.invoke(brandId = brand.id)
                .collect { res ->
                    handleCollectScope(res) { dataList ->
                        productsLiveData.postValue(dataList)
                    }
                }
        }
    }

    fun addProductToWishList(product: Product?) {
        if(product==null)return
        launch {
            addToWishListUseCase.invoke(product).collect { result ->
                handleCollectScope(result) {
                    updateProductStateIntoWishList(product)
                }
            }
        }
    }

    fun addProductToCart(product: Product?)
    {
        if (product == null) return
        launch {
            addToCartUseCase.invoke(product).collect { result ->
                handleCollectScope(result) {}
            }
        }
    }

    private fun updateProductStateIntoWishList(product: Product) {
        val pos = productsLiveData.value?.indexOf(product) ?: -1
        if (pos != -1) {
            product.isLiked = !product.isLiked
            productWishListUpdatePosition.postValue(pos)
        }
    }

    fun removeProductToWishList(product: Product?) {
        if (product == null) return
        launch {
            removeProductFromWishListUseCase.invoke(product.id!!)
                .collect { result ->
                    handleCollectScope(result) {
                        updateProductStateIntoWishList(product)
                    }
                }
        }

    }

    fun searchProducts(query: String?): List<Product>? {
        return productsLiveData.value?.filter { product ->
            product.title?.contains(query.toString(), ignoreCase = true) == true ||
                    product.brand?.name?.contains(query.toString(), ignoreCase = true) == true ||
                    product.category?.name?.contains(query.toString(), ignoreCase = true) == true
        }?.toList()


    }

}
