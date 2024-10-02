package com.example.e_commerce_route_c40.ui.fragments.product

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.e_commerce_route_c40.R
import com.example.e_commerce_route_c40.base.BaseFragment
import com.example.e_commerce_route_c40.databinding.FragmentProductBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class ProductsFragment : BaseFragment<FragmentProductBinding, ProductViewModel>() {

    private val _viewModel: ProductViewModel by viewModels()

    @Inject
    lateinit var productsAdaptor: ProductsAdaptor


    override fun initViewModel()    : ProductViewModel = _viewModel

    override fun getLayoutId()      : Int = R.layout.fragment_product

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getProducts()
        observeLivedata()
        initViews()
        setupSearchBar()
    }

    private fun setupSearchBar() {
        binding.etSearch.setOnQueryTextListener(object : androidx.appcompat.widget.SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean = false

            override fun onQueryTextChange(newText: String?): Boolean {
                viewModel.searchProducts(newText)?.let { productsAdaptor.changeData(it) }
                return true
            }

        })
    }

    private fun observeLivedata() {
        viewModel.productsLiveData.observe(viewLifecycleOwner) { products ->
            if (products != null) {
                productsAdaptor.changeData(products)
            }
            updateUiProducts()
        }
        viewModel.productWishListUpdatePosition.observe(viewLifecycleOwner){ pos->
            productsAdaptor.notifyItemChanged(pos)
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun initViews() {
        productsAdaptor.onFavoriteClickListener = ProductsAdaptor.OnItemClickListener { product, position ->
            if (product?.isLiked == false)
                viewModel.addProductToWishList(product)
            else
                viewModel.removeProductToWishList(product)

        }

        productsAdaptor.onProductClickListener = ProductsAdaptor.OnItemClickListener { product, _ ->
            product.let {
                val act = ProductsFragmentDirections.actionProductsFragmentToProductDetailsFragment(
                    it?.id ?: ""
                )
                findNavController().navigate(act)
            }
        }

        productsAdaptor.onReachedBottom = {
            viewModel.getAllProducts()
        }

        productsAdaptor.onAddClickListener =
            ProductsAdaptor.OnAddedClickListener { product, _ ->
                        viewModel.addProductToCart(product)
                }

        binding.rvProduct.adapter = productsAdaptor
    }

    private fun updateUiProducts() {
        if (viewModel.productsLiveData.value.isNullOrEmpty()) {
            binding.apply {
                rvProduct.visibility = View.INVISIBLE
                layoutPlaceHolder.viewStub?.visibility = View.VISIBLE
            }
        } else {
            binding.apply {
                rvProduct.visibility = View.VISIBLE
                layoutPlaceHolder.viewStub?.visibility = View.GONE
            }
        }
    }


}