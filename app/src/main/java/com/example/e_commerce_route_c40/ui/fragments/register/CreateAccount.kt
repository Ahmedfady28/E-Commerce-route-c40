package com.example.e_commerce_route_c40.ui.fragments.register

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import com.example.e_commerce_route_c40.R
import com.example.e_commerce_route_c40.base.BaseFragment
import com.example.e_commerce_route_c40.databinding.FragmentCreateAccountBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CreateAccount : BaseFragment<FragmentCreateAccountBinding, CreateAccountViewModel>() {

    override fun getLayoutId(): Int = R.layout.fragment_create_account

    private val _viewModel: CreateAccountViewModel by viewModels ()

    override fun initViewModel(): CreateAccountViewModel {
        return _viewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.createVM = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        viewModel.signUpLiveData.observe(viewLifecycleOwner){
            navigateToLogin()
        }
    }

    private fun navigateToLogin() {
        val action = CreateAccountDirections.actionCreateAccountToLoginScreen()
        findNavController(this).navigate(action)
    }

}