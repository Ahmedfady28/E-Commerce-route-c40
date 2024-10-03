package com.example.e_commerce_route_c40.ui.fragments.productDetails

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import com.example.e_commerce_route_c40.R
import com.example.e_commerce_route_c40.base.BaseFragment
import com.example.e_commerce_route_c40.databinding.FragmentProductDetailsBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ProductDetailsFragment :
    BaseFragment<FragmentProductDetailsBinding, ProductDetailsViewModel>() {

    private val _viewModel: ProductDetailsViewModel by viewModels()

    @Inject
    lateinit var imgAdapter: ImgsAdaptor

    override fun initViewModel(): ProductDetailsViewModel = _viewModel

    override fun getLayoutId(): Int = R.layout.fragment_product_details

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        specificProductObserver()
        viewModel.getSpecificProduct()
    }

    private fun initView() {
        binding.ProductImages.adapter = imgAdapter
        binding.ProductImages.layoutManager = LinearLayoutManager(
            context,
            LinearLayoutManager.HORIZONTAL,
            false
        )

        LinearSnapHelper().attachToRecyclerView(binding.ProductImages)
        var isExpanded = false

        binding.TvReadMore.setOnClickListener {
            if (isExpanded) {
                binding.tvDescriptionDetails.maxLines = 2
                binding.TvReadMore.text = buildString {
                    append("Read More")
                }
            } else {
                binding.tvDescriptionDetails.maxLines = Integer.MAX_VALUE
                binding.TvReadMore.text = buildString {
                    append("Read Less")
                }
            }
            isExpanded = !isExpanded
        }
    }


    private fun specificProductObserver() {
        _viewModel.productLiveData.observe(viewLifecycleOwner) { product ->

            binding.apply {
                tvTitle.text = product?.title
                tvPriceProduct.text = buildString {
                    append(product?.price.toString())
                    append(" $")
                }
                product?.images?.let { imgAdapter.changeData(it) }
                tvDescriptionDetails.text = product?.description
                TvHowManySold.text = buildString {
                    append(product?.sold.toString())
                    append(" Sold Times")
                }
                tvRating.text = product?.ratingsAverage.toString()

            }

            binding.AddToCartButton.setOnClickListener {
                _viewModel.addToCart(product)
            }
        }
    }
}
