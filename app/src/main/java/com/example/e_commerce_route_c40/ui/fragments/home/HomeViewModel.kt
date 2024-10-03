package com.example.e_commerce_route_c40.ui.fragments.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.e_commerce_route_c40.base.BaseViewModel
import com.route.domain.model.Brand
import com.route.domain.model.Category
import com.route.domain.model.Product
import com.route.domain.usecase.brand.GetBrandsUseCase
import com.route.domain.usecase.cart.AddProductToCartUseCase
import com.route.domain.usecase.category.GetCategoriesUseCase
import com.route.domain.usecase.product.GetProductsUseCase
import com.route.domain.usecase.wishList.AddProductToWishListUseCase
import com.route.domain.usecase.wishList.RemoveProductFromWishListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getCategoriesUseCase: GetCategoriesUseCase,
    private val getBrandsUseCase: GetBrandsUseCase,
    private val getProductsUseCase: GetProductsUseCase,
    private val addToWishListUseCase: AddProductToWishListUseCase,
    private val removeProductFromWishListUseCase: RemoveProductFromWishListUseCase,
    private val addToCartUseCase: AddProductToCartUseCase
) : BaseViewModel() {
    val categoriesLiveData = MutableLiveData<List<Category>?>()
    val brandsLiveData = MutableLiveData<List<Brand>?>()
    val productsLiveData = MutableLiveData<List<Product>?>()
    private val keyOfMostSeller: String = "-sold"

    val productWishListUpdatePosition = MutableLiveData<Int>()

    fun getCategoryList() {
        viewModelScope.launch(Dispatchers.IO) {
            getCategoriesUseCase.invoke()
                .flowOn(Dispatchers.IO)
                .collect { result ->
                    handleCollectScope(result) { dataList ->
                        categoriesLiveData.postValue(dataList)
                    }
                }


        }

    }

    fun getBrandsList() {
        viewModelScope.launch(Dispatchers.IO) {
            getBrandsUseCase.invoke()
                .flowOn(Dispatchers.IO)
                .collect { result ->
                    handleCollectScope(result) { brandsList ->
                        brandsLiveData.postValue(brandsList)
                    }
                }


        }

    }

    fun getMostSellerList() {
        viewModelScope.launch(Dispatchers.IO) {
            getProductsUseCase.invoke(sortBy = keyOfMostSeller)
                .flowOn(Dispatchers.IO)
                .collect { result ->
                    handleCollectScope(result) { productList ->
                        productsLiveData.postValue(productList)
                    }
                }


        }

    }


    fun addProductToWishList(product: Product?) {
        if (product == null) return
        viewModelScope.launch(Dispatchers.IO) {
            addToWishListUseCase.invoke(product).collect { result ->
                handleCollectScope(result) {
                    updateProductState(product)
                }
            }
        }
    }

    private fun updateProductState(product: Product) {
        val pos = productsLiveData.value?.indexOf(product) ?: -1
        if (pos != -1) {
            product.isLiked = !product.isLiked
            productWishListUpdatePosition.postValue(pos)
        }
    }

    fun removeProductToWishList(product: Product?) {
        if (product == null) return
        viewModelScope.launch(Dispatchers.IO) {
            removeProductFromWishListUseCase.invoke(product.id!!)
                .collect { result ->
                    handleCollectScope(result) {
                        updateProductState(product)
                    }
                }
        }

    }

    fun addProductToCart(product: Product?) {
        if (product == null) return
        viewModelScope.launch {
            addToCartUseCase.invoke(product).collect { result ->
                handleCollectScope(result) {}
            }
        }
    }

    fun searchHomeProducts(query: String?): List<Product>?
    {
        return productsLiveData.value?.filter { product ->
            product.title?.contains(query.toString(), ignoreCase = true) == true ||
                    product.brand?.name?.contains(query.toString(), ignoreCase = true) == true ||
                    product.category?.name?.contains(query.toString(), ignoreCase = true) == true
        }?.toList()


    }
}