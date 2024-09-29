package com.example.e_commerce_route_c40.ui.fragments.favorite



import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.e_commerce_route_c40.R
import com.example.e_commerce_route_c40.base.BaseFragment
import com.example.e_commerce_route_c40.databinding.FragmentFavorateBinding
import com.example.e_commerce_route_c40.util.SwipeToDelete
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class FavoriteFragment : BaseFragment<FragmentFavorateBinding,FavoriteViewModel>() {
    override fun getLayoutId(): Int = R.layout.fragment_favorate

    @Inject
    lateinit var adapterProduct: FavoriteProductAdapter

    private val _viewModel: FavoriteViewModel by viewModels()

    private lateinit var swipeClickListener: SwipeToDelete

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

        swipeClickListener = initSwipeAction(viewModel, adapterProduct)


        val itemTouchHelper = ItemTouchHelper(swipeClickListener)
        itemTouchHelper.attachToRecyclerView(binding.revWishList)
    }

    private fun observeLiveData() {
        viewModel.wishlistLiveData.observe(viewLifecycleOwner) { products ->
            products?.let { adapterProduct.changeData(it) }
        }
    }


    private fun initSwipeAction(viewModel: FavoriteViewModel, adapter: FavoriteProductAdapter) =
        object : SwipeToDelete() {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                viewModel.removeProductToCart(adapter.getItem(viewHolder.adapterPosition))
                Log.e("id", adapter.getItem(viewHolder.adapterPosition)?.title.toString())
            }
        }
}