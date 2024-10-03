package com.example.e_commerce_route_c40.ui.fragments.productDetails

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.example.e_commerce_route_c40.base.BaseViewModel
import com.route.domain.model.ApiResult
import com.route.domain.model.Product
import com.route.domain.usecase.cart.AddProductToCartUseCase
import com.route.domain.usecase.product.GetSpecificProductUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductDetailsViewModel @Inject constructor(
    private val getSpecificProductUseCase: GetSpecificProductUseCase,
    private val addProductToCartUseCase: AddProductToCartUseCase,
    savedStateHandle: SavedStateHandle,
): BaseViewModel() {

    val productLiveData = MutableLiveData<Product?>()
    private val productId: String = savedStateHandle["id"] ?: ""

    fun getSpecificProduct(){
        viewModelScope.launch(Dispatchers.IO) {
            getSpecificProductUseCase.invoke(productId = productId)
                    .flowOn(Dispatchers.IO)
                    .collect {result->
                        when(result){
                            is ApiResult.Failure -> handleError(result.throwable){
                                getSpecificProduct()
                            }
                            is ApiResult.Loading -> handleLoading(result)
                            is ApiResult.Success -> productLiveData.postValue(result.data)
                        }
                    }
        }
    }

    fun addToCart(product: Product?) {
        viewModelScope.launch(Dispatchers.IO) {
            addProductToCartUseCase.invoke(productLiveData.value!!)
                .flowOn(Dispatchers.IO)
                .collect { result ->
                    handleCollectScope(result) {}
                }
        }
    }
}
