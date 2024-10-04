package com.example.e_commerce_route_c40.ui.fragments.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.e_commerce_route_c40.R
import com.example.e_commerce_route_c40.base.BaseFragment
import com.example.e_commerce_route_c40.databinding.FragmentHomeBinding
import com.example.e_commerce_route_c40.ui.fragments.home.adaptors.AdapterMostSeller
import com.example.e_commerce_route_c40.ui.fragments.home.adaptors.BrandAdapter
import com.example.e_commerce_route_c40.ui.fragments.home.adaptors.HomeCategoriesAdapter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>() {

    @Inject
    lateinit var adapterCategories: HomeCategoriesAdapter

    @Inject
    lateinit var adapterMostSeller: AdapterMostSeller

    @Inject
    lateinit var adapterBrands: BrandAdapter

    private val _viewModel: HomeViewModel by viewModels()

    override fun initViewModel(): HomeViewModel = _viewModel

    override fun getLayoutId(): Int = R.layout.fragment_home

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        observeLiveData()
        viewModel.getCategoryList()
        viewModel.getBrandsList()
        viewModel.getMostSellerList()
    }


    private fun observeLiveData() {
        viewModel.categoriesLiveData.observe(viewLifecycleOwner) { categories ->
            if (categories != null) adapterCategories.changeData(categories)

        }
        viewModel.brandsLiveData.observe(viewLifecycleOwner) { brands ->
            if (brands != null) adapterBrands.changeData(brands)
        }

        viewModel.productsLiveData.observe(viewLifecycleOwner) { products ->
            if (products != null) adapterMostSeller.changeData(products)
        }
        viewModel.productWishListUpdatePosition.observe(viewLifecycleOwner) { pos ->
            adapterMostSeller.notifyItemChanged(pos)
        }
    }

    private fun initViews() {

        adapterCategories.onItemClickListener =
            HomeCategoriesAdapter.OnItemClickListener { category, pos ->
                    val action =
                        HomeFragmentDirections.actionHomeFragmentToCategoryFragment(category, pos)
                    Navigation.findNavController(binding.root).navigate(action)

                }

        adapterBrands.onBrandClickListener =
            BrandAdapter.OnItemClickListener { brand, _ ->
                val action =
                    HomeFragmentDirections.actionHomeFragmentToProductsFragment(brand = brand)
                Navigation.findNavController(binding.root).navigate(action)

            }
        adapterMostSeller.onFavoriteClickListener =
            AdapterMostSeller.OnItemClickListener { product, _ ->
                if (product?.isLiked == false)
                    viewModel.addProductToWishList(product)
                else
                    viewModel.removeProductToWishList(product)

            }

        adapterMostSeller.onAddCartClickListener =
            AdapterMostSeller.OnItemClickListener { product, _ ->
                viewModel.addProductToCart(product)
            }


        adapterMostSeller.onProductClickListener =
            AdapterMostSeller.OnItemClickListener { product, _ ->
                val action =
                    HomeFragmentDirections.actionHomeFragmentToProductDetailsFragment(
                        id = product?.id ?: ""
                    )
                Navigation.findNavController(binding.root).navigate(action)

            }



        binding.apply {
            rvCategories.adapter = adapterCategories
            rvBrands.adapter = adapterBrands
            rvHomeAppliance.adapter = adapterMostSeller

            tvViewAllCategories.setOnClickListener {
                val action = HomeFragmentDirections.actionHomeFragmentToCategoryFragment()
                Navigation.findNavController(binding.root).navigate(action)
            }
            tvViewAllBrands.setOnClickListener {
                val action = HomeFragmentDirections.actionHomeFragmentToProductsFragment()
                Navigation.findNavController(binding.root).navigate(action)
            }
            btnCart.setOnClickListener {

                val action = HomeFragmentDirections.actionHomeFragmentToCartFragment()
                Navigation.findNavController(binding.root).navigate(action)
            }
        }

    }
}