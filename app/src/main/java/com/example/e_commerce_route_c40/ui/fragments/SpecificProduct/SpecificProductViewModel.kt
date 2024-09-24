package com.example.e_commerce_route_c40.ui.fragments.SpecificProduct

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.example.e_commerce_route_c40.base.BaseViewModel
import com.route.domain.model.ApiResult
import com.route.domain.model.Product
import com.route.domain.usecase.product.GetSpecificProductUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

class SpecificProductViewModel @Inject constructor(
    private val getSpecificProductUseCase: GetSpecificProductUseCase,
    savedStateHandle: SavedStateHandle
): BaseViewModel() {

    val productLiveData = MutableLiveData<Product?>()
    val productId = savedStateHandle.get<String>("id")

    fun getSpecificProduct(){
        viewModelScope.launch(Dispatchers.IO) {
            productId?.let {
                getSpecificProductUseCase.invoke(productId = it)
                    .flowOn(Dispatchers.IO)
                    .collect {result->
                        when(result){
                            is ApiResult.Failure -> handleError(result.throwable){
                                getSpecificProduct()
                            }
                            is ApiResult.Loading -> handleLoading(result)
                            is ApiResult.Success -> getSpecificProductUseCase.invoke(productId = productId)
                        }
                    }
            }
        }
    }
}