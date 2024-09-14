package com.example.e_commerce_route_c40.ui.activities

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.e_commerce_route_c40.R
import com.example.e_commerce_route_c40.base.BaseViewModel
import com.example.e_commerce_route_c40.util.ValidationUtils
import com.route.domain.model.ApiResult
import com.route.domain.model.LoginData
import com.route.domain.usecase.GetLoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: GetLoginUseCase
): BaseViewModel() {

    val emailLiveData = MutableLiveData<String>()
    val passwordLiveData = MutableLiveData<String>()

    val emailError = MutableLiveData<Int?>()
    val passwordError = MutableLiveData<Int?>()

    val loginLiveData = MutableLiveData<LoginData?>()

    fun onLoginClick(){
        if(!isValidForm()){
            return
        }
        login(email = emailLiveData.value?:"",
            password = passwordLiveData.value ?:"")

    }
    private fun login(email: String, password: String){
            viewModelScope.launch (Dispatchers.IO){
                loginUseCase.invoke(email, password)
                    .flowOn(Dispatchers.IO)
                    .collect{result->
                        when(result){
                            is ApiResult.Failure -> handleError(result.throwable){
                                login(email,password)
                            }
                            is ApiResult.Loading ->  handleLoading(result)
                            is ApiResult.Success ->{
                                loginLiveData.postValue(result.data)

                            }
                        }
                    }

            }
    }

    private fun isValidForm(): Boolean {
        var isValid = true
        if(emailLiveData.value.isNullOrEmpty()){
            isValid = false
            emailError.value = R.string.please_enter_your_email
        } else if(!ValidationUtils.isValidEmail(email = emailLiveData.value)){
            isValid = false
            emailError.value = R.string.email_format_not_valid
        } else {
            emailError.value = null
        }
        if(!ValidationUtils.isValidPassword(password = passwordLiveData.value)){
            passwordError.value = R.string.please_enter_valid_password
            isValid = false
        }else {
            passwordError.value = null

        }
        return isValid
    }

}