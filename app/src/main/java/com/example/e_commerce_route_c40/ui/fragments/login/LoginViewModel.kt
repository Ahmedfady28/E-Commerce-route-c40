package com.example.e_commerce_route_c40.ui.fragments.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.e_commerce_route_c40.R
import com.example.e_commerce_route_c40.base.BaseViewModel
import com.example.e_commerce_route_c40.util.ValidationUtils
import com.route.domain.model.AuthData
import com.route.domain.usecase.auth.GetLoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
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

    val loginLiveData = MutableLiveData<AuthData?>()

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
                        handleCollectScope(result,
                            // OnRetry login again
                            { login(email, password) }
                        ) { data ->
                            loginLiveData.postValue(data)
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