package com.example.e_commerce_route_c40.ui.fragments.cart

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.e_commerce_route_c40.base.BaseViewModel
import com.route.domain.model.Product
import com.route.domain.usecase.cart.GetCartUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val getCartUseCase: GetCartUseCase
) : BaseViewModel()
{
    val cartLiveData = MutableLiveData<List<Product>?>()
    fun getCart()
    {
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
}