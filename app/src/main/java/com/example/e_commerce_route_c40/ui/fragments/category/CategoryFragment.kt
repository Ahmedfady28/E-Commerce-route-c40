package com.example.e_commerce_route_c40.ui.fragments.category


import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.e_commerce_route_c40.R
import com.example.e_commerce_route_c40.base.BaseFragment
import com.example.e_commerce_route_c40.databinding.FragmentCategoryBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class CategoryFragment : BaseFragment<FragmentCategoryBinding,CategoriesViewModel>() {

    override fun getLayoutId(): Int = R.layout.fragment_category

    private val _viewModel :CategoriesViewModel by viewModels ()

    override fun initViewModel(): CategoriesViewModel = _viewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        observeLivedata()
        viewModel.getCategories()
    }

    private fun observeLivedata() {
        viewModel.categoriesLiveData.observe(viewLifecycleOwner){categories->
            categories?.let {
                categoriesAdapter.changeData(categories)
            }
        }
        viewModel.subCategoriesLiveData.observe(viewLifecycleOwner) { subCategories ->
            subCategories?.let {
                subCategoriesAdapter.changeData(subCategories)
            }
            updateUiSubCategories()
        }
    }
    @Inject
     lateinit var categoriesAdapter:CategoriesAdapter
    @Inject
     lateinit var subCategoriesAdapter:SubCategoriesAdapter

    private fun initViews() {
        categoriesAdapter.onItemClickListener = CategoriesAdapter.OnItemClickListener { category, _ ->
            viewModel.getSubCategories(category?.id ?:"")
        }

        subCategoriesAdapter.onItemClickListener =
            SubCategoriesAdapter.OnItemClickListener { subCategory, _ ->

                val act =
                    CategoryFragmentDirections.actionCategoryFragmentToProductsFragment(subCategory)
                Navigation.findNavController(binding.root).navigate(act)
            }

        binding.apply {
            categoriesAdapter.selectedPosition = viewModel.selectedPos
            rvCategory.adapter =categoriesAdapter
            rvSubCategory.adapter = subCategoriesAdapter
        }
    }

    private fun updateUiSubCategories() {
        if (viewModel.subCategoriesLiveData.value.isNullOrEmpty()) {
            binding.apply {
                rvSubCategory.visibility = View.GONE
                layoutPlaceHolder.viewStub?.visibility = View.VISIBLE
            }
        } else {
            binding.apply {
                rvSubCategory.visibility = View.VISIBLE
                layoutPlaceHolder.viewStub?.visibility = View.GONE
            }

        }

    }
}