package com.example.e_commerce_route_c40.ui.fragments.favorite

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.e_commerce_route_c40.base.BaseViewModel
import com.route.domain.model.Product
import com.route.domain.usecase.wishList.AddProductToWishListUseCase
import com.route.domain.usecase.wishList.GetWishlistUseCase
import com.route.domain.usecase.wishList.RemoveProductFromWishListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val getWishlistUseCase: GetWishlistUseCase,
    private val addToCartUseCase: AddProductToWishListUseCase,
    private val removeProductFromWishListUseCase: RemoveProductFromWishListUseCase
) : BaseViewModel() {
    val wishlistLiveData = MutableLiveData<List<Product>?>()

    fun getWishlist() {
            viewModelScope.launch {
                getWishlistUseCase.invoke()
                    .flowOn((Dispatchers.IO))
                    .collect{result->
                        handleCollectScope(result) { dataList ->
                            wishlistLiveData.postValue(dataList)
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

    fun removeProductToCart(product: Product?) {
        if (product == null) return
        viewModelScope.launch {
            product.id?.let { id ->
                removeProductFromWishListUseCase.invoke(id).collect { result ->
                    handleCollectScope(result) {
                        wishlistLiveData.postValue(
                            wishlistLiveData.value?.filter { it.id != product.id }
                        )
                    }
                }
            }
        }
    }
}