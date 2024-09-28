package com.example.e_commerce_route_c40.ui.fragments.cart

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.e_commerce_route_c40.base.BaseViewModel
import com.route.domain.model.ActionCart
import com.route.domain.model.Product
import com.route.domain.usecase.cart.ClearCartUseCase
import com.route.domain.usecase.cart.GetCartUseCase
import com.route.domain.usecase.cart.RemoveProductFromCartUseCase
import com.route.domain.usecase.cart.UpdateItemCartUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val getCartUseCase: GetCartUseCase,
    private val updateCartUseCase: UpdateItemCartUseCase,
    private val removeFromCartUseCase: RemoveProductFromCartUseCase,
    private val clearCartUseCase: ClearCartUseCase
) : BaseViewModel() {
    val cartLiveData = MutableLiveData<ActionCart?>()
    val cartMessageLiveData = MutableLiveData<String?>()

    fun getCart() {
        viewModelScope.launch {
            getCartUseCase.invoke()
                .flowOn(Dispatchers.IO)
                .collect { result ->
                    handleCollectScope(result) { dataList ->
                        cartLiveData.postValue(dataList)
                    }
                }
        }
    }

    fun updateCart(id: String, newCount: Int) {
        viewModelScope.launch {
            updateCartUseCase.invoke(id, newCount)
                .flowOn(Dispatchers.IO)
                .collect { result ->
                    handleCollectScope(result) { dataList ->
                        cartLiveData.postValue(dataList)
                    }

                }
        }
    }

    fun removeFromCart(product: Product) {
        viewModelScope.launch {
            removeFromCartUseCase.invoke(product)
                .flowOn(Dispatchers.IO)
                .collect { result ->
                    handleCollectScope(result) { dataList ->
                        cartLiveData.postValue(dataList)
                    }
                }
        }
    }

    fun clearCart(id: String) {
        viewModelScope.launch {
            clearCartUseCase.invoke(id)
                .flowOn(Dispatchers.IO)
                .collect { result ->
                    handleCollectScope(result) { message ->
                        cartMessageLiveData.postValue(message)
                    }
                }
        }
    }
}