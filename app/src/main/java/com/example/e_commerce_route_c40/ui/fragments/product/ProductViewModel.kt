package com.example.e_commerce_route_c40.ui.fragments.product

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import com.example.e_commerce_route_c40.base.BaseViewModel
import com.route.data.api.interceptor.IODispatcher
import com.route.domain.model.ApiResult
import com.route.domain.model.Product
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
    @IODispatcher override val coroutineContext: CoroutineContext,
    savedStateHandle: SavedStateHandle,
) : BaseViewModel(),CoroutineScope {

    val productsLiveData = MutableLiveData<List<Product>?>()
    val productWishListUpdatePosition = MutableLiveData<Int>()
//    private val subCategory: SubCategory? = savedStateHandle["subCategory"]

    fun getProductsByCategory() {
        launch {
            productsUseCase.invoke() //categoryId = subCategory?.id
                .collect { res ->
                    when (res) {
                        is ApiResult.Failure -> handleError(res.throwable)
                        is ApiResult.Loading -> handleLoading(res)
                        is ApiResult.Success -> productsLiveData.postValue(res.data)
                    }


                }
        }

    }

    fun getProductsByKey(key: String) {
        launch {
            productsUseCase.invoke(keyword = key)
                .collect { res ->
                    when (res) {
                        is ApiResult.Failure -> handleError(res.throwable)
                        is ApiResult.Loading -> handleLoading(res)
                        is ApiResult.Success -> productsLiveData.postValue(res.data)
                    }
                }
        }
    }

    fun addProductToWishList(product: Product?) {
        if(product==null)return
        launch {
            addToWishListUseCase.invoke(product)
                .collect{result->
                    when (result) {
                        is ApiResult.Failure -> handleError(result.throwable)
                        is ApiResult.Loading -> handleLoading(result)
                        is ApiResult.Success -> {
                            val pos = productsLiveData.value?.indexOf(product) ?:-1
                            if(pos!=-1){
                                product.apply {
                                    isLiked = true
                                }
                                productWishListUpdatePosition.postValue(pos)
                            }
                        }
                    }
                }
        }

    }

    fun removeProductToWishList(product: Product?) {
        if (product == null) return
        launch {
            removeProductFromWishListUseCase.invoke(product.id!!)
                .collect { result ->
                    when (result) {
                        is ApiResult.Failure -> handleError(result.throwable)
                        is ApiResult.Loading -> handleLoading(result)
                        is ApiResult.Success -> {
                            val pos = productsLiveData.value?.indexOf(product) ?: -1
                            if (pos != -1) {
                                product.apply {
                                    isLiked = false
                                }
                                productWishListUpdatePosition.postValue(pos)
                            }
                        }
                    }
                }
        }

    }
}