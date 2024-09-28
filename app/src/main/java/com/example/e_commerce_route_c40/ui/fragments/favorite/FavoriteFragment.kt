package com.example.e_commerce_route_c40.ui.fragments.favorite



import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.example.e_commerce_route_c40.R
import com.example.e_commerce_route_c40.base.BaseFragment
import com.example.e_commerce_route_c40.databinding.FragmentFavorateBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class FavoriteFragment : BaseFragment<FragmentFavorateBinding,FavoriteViewModel>() {
    override fun getLayoutId(): Int {
        return R.layout.fragment_favorate
    }

    @Inject
    lateinit var adapterProduct: FavoriteProductAdapter

    private val _viewModel : FavoriteViewModel by viewModels()

    override fun initViewModel(): FavoriteViewModel = _viewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.revWishList.adapter = adapterProduct
        observeLiveData()
        viewModel.getWishlist()

        adapterProduct.onAddToCartClickListener =
            FavoriteProductAdapter.OnItemClickListener { product, _ ->
                viewModel.addProductToCart(product)
            }
    }

    private fun observeLiveData() {
        viewModel.wishlistLiveData.observe(viewLifecycleOwner) { products ->
            products?.let { adapterProduct.changeData(it) }
        }
    }
}