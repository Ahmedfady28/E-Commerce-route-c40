package com.example.e_commerce_route_c40.ui.fragments.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.e_commerce_route_c40.base.BaseViewModel
import com.route.domain.model.Brand
import com.route.domain.model.Category
import com.route.domain.usecase.brand.GetBrandsUseCase
import com.route.domain.usecase.category.GetCategoriesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getCategoriesUseCase: GetCategoriesUseCase,
    private val getBrandsUseCase: GetBrandsUseCase

) : BaseViewModel() {
    val categoriesLiveData = MutableLiveData<List<Category>?>()
    val brandsLiveData = MutableLiveData<List<Brand>?>()
    fun getCategoryList() {
            viewModelScope.launch (Dispatchers.IO){
                getCategoriesUseCase.invoke()
                    .flowOn(Dispatchers.IO)
                    .collect { result ->
                        handleCollectScope(result) { dataList ->
                            categoriesLiveData.postValue(dataList)
                        }
                    }


            }

    }

    fun getBrandsList()
    {
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

}