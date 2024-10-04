package com.example.e_commerce_route_c40.ui.fragments.category

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.example.e_commerce_route_c40.base.BaseViewModel
import com.route.domain.model.Category
import com.route.domain.model.SubCategory
import com.route.domain.usecase.category.GetCategoriesUseCase
import com.route.domain.usecase.category.GetSubCategoriesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoriesViewModel @Inject constructor(
    private val getCategoriesUseCase: GetCategoriesUseCase,
    private val getSubCategoriesUseCase: GetSubCategoriesUseCase,
    savedStateHandle: SavedStateHandle
) :BaseViewModel() {
    val categoriesLiveData = MutableLiveData<List<Category>?>()
    val subCategoriesLiveData = MutableLiveData<List<SubCategory>?>()
    var selectedPos: Int = savedStateHandle["SeletedPos"] ?: 0

    fun getCategories(){
        viewModelScope.launch {
            getCategoriesUseCase.invoke()
                .collect{result->
                    handleCollectScope(result) { dataList ->
                        categoriesLiveData.postValue(dataList)
                    }
                }
        }
    }
    fun getSubCategories(categoryId:String){

        viewModelScope.launch {
            getSubCategoriesUseCase.invoke(categoryId)
                .collect{result->
                    handleCollectScope(result) { dataList ->
                        subCategoriesLiveData.postValue(dataList)
                    }
                }
        }
        hideLoading()

    }
}