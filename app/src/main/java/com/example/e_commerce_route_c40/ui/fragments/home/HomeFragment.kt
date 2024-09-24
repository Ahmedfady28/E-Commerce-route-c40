package com.example.e_commerce_route_c40.ui.fragments.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.e_commerce_route_c40.R
import com.example.e_commerce_route_c40.adapters.HomeCategoriesAdapter
import com.example.e_commerce_route_c40.base.BaseFragment
import com.example.e_commerce_route_c40.databinding.FragmentHomeBinding
import com.example.e_commerce_route_c40.ui.activities.MainActivity
import com.example.e_commerce_route_c40.util.makeNavyBottomVisible
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>() {

    @Inject
    lateinit var adapterCategories: HomeCategoriesAdapter


    @Inject
    lateinit var adapterBrands: BrandAdapter

    val _viewModel :HomeViewModel by viewModels()

    override fun initViewModel(): HomeViewModel {
        return _viewModel
    }

    override fun getLayoutId(): Int = R.layout.fragment_home

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        observeLiveData()
        viewModel.getCategoryList()
        viewModel.getBrandsList()
    }


    private fun observeLiveData() {
        viewModel.categoriesLiveData.observe(viewLifecycleOwner){categories->
            if (categories != null) {
                adapterCategories.changeData(categories)
            }
        }
        viewModel.brandsLiveData.observe(viewLifecycleOwner) { brands ->
            if (brands != null)
            {
                adapterBrands.changeData(brands)
            }
        }
    }

    private fun initViews() {
        (activity as MainActivity).makeNavyBottomVisible(true)
        adapterCategories = HomeCategoriesAdapter()

        adapterCategories.onItemClickListener =
                HomeCategoriesAdapter.OnItemClickListener { category, _ ->
                    val action =
                            HomeFragmentDirections.actionHomeFragmentToCategoryFragment(category)
                    Navigation.findNavController(binding.root).navigate(action)

                }



        binding.apply {
            rvCategories.adapter = adapterCategories
            rvBrands.adapter = adapterBrands

            tvViewAllCategories.setOnClickListener {
                val action = HomeFragmentDirections.actionHomeFragmentToCategoryFragment()
                Navigation.findNavController(binding.root).navigate(action)
            }

            etSearch.setOnClickListener {
                // Handle search button click
            }
            btnCart.setOnClickListener {
                // action to cart fragment
            }
            rvBrands.setOnClickListener {
                // action to specific fragment
            }

            rvCategories.setOnClickListener {
                // action to specific category fragment
            }


        }

    }
}