package com.example.e_commerce_route_c40.ui.fragments.splash

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.e_commerce_route_c40.base.BaseViewModel
import com.route.domain.model.ApiResult
import com.route.domain.usecase.auth.GetLoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val loginUseCase: GetLoginUseCase
):BaseViewModel() {

    val destination  = MutableLiveData<SplashDestinations>()

    fun checkLoggedInUser(){
        viewModelScope.launch(Dispatchers.IO) {
            val result  = loginUseCase.invoke().first()
            delay(2000)
            when(result){
                is ApiResult.Success ->{
                    if(result.data !=null){
                        destination.postValue(SplashDestinations.Home)
                    } else {
                        destination.postValue(SplashDestinations.Login)
                    }
                }
                else->{
                    destination.postValue(SplashDestinations.Login)
                }
            }
        }
    }
}