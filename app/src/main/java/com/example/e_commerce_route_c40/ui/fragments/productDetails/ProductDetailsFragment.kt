package com.example.e_commerce_route_c40.ui.fragments.productDetails

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.example.e_commerce_route_c40.R
import com.example.e_commerce_route_c40.base.BaseFragment
import com.example.e_commerce_route_c40.databinding.FragmentProductDetailsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductDetailsFragment :
    BaseFragment<FragmentProductDetailsBinding, ProductDetailsViewModel>() {

    private val _viewModel: ProductDetailsViewModel by viewModels()

    override fun initViewModel(): ProductDetailsViewModel = _viewModel

    override fun getLayoutId(): Int = R.layout.fragment_product_details

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        specificProductObserver()
        viewModel.getSpecificProduct()
    }

    private fun initView() {
        val tvDescription = binding.TvDescription
        val tvReadMore = binding.TvReadMore

        var isExpanded = false

        tvReadMore.setOnClickListener {
            if (isExpanded) {
                tvDescription.maxLines = 2
                tvReadMore.text = "Read More"
            } else {
                tvDescription.maxLines = Integer.MAX_VALUE
                tvReadMore.text = "Read Less"
            }
            isExpanded = !isExpanded
        }
    }

    private fun specificProductObserver() {
        _viewModel.productLiveData.observe(viewLifecycleOwner) { product ->
            product?.category
        }
    }
}
