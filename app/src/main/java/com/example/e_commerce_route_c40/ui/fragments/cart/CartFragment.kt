package com.example.e_commerce_route_c40.ui.fragments.cart


import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.example.e_commerce_route_c40.R
import com.example.e_commerce_route_c40.base.BaseFragment
import com.example.e_commerce_route_c40.databinding.FragmentCartBinding
import com.example.e_commerce_route_c40.ui.fragments.favorite.FavoriteProductAdapter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CartFragment : BaseFragment<FragmentCartBinding, CartViewModel>()
{

    private val _viewModel: CartViewModel by viewModels()
    override fun initViewModel(): CartViewModel
    {
        return _viewModel
    }

    override fun getLayoutId(): Int = R.layout.fragment_cart

    lateinit var cartAdapter: CartAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        super.onViewCreated(view, savedInstanceState)

        initViews()
        observeLiveData()
        viewModel.getCart()
    }

    private fun observeLiveData()
    {
        viewModel.cartLiveData.observe(viewLifecycleOwner) { products ->
            products?.let {
                cartAdapter.updateProducts(it)
            }
        }
    }

    private fun initViews()
    {
        cartAdapter = CartAdapter()
        binding.cartRecyclerViewe.adapter = cartAdapter

    }


}