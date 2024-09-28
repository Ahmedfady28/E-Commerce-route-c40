package com.example.e_commerce_route_c40.ui.fragments.cart


import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.example.e_commerce_route_c40.R
import com.example.e_commerce_route_c40.base.BaseFragment
import com.example.e_commerce_route_c40.databinding.FragmentCartBinding
import com.route.domain.model.Product
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CartFragment : BaseFragment<FragmentCartBinding, CartViewModel>()
{

    private val _viewModel: CartViewModel by viewModels()

    override fun initViewModel(): CartViewModel = _viewModel

    override fun getLayoutId(): Int = R.layout.fragment_cart

    @Inject
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
        viewModel.cartLiveData.observe(viewLifecycleOwner) { actionCart ->
            actionCart?.products?.let { cartAdapter.changeData(it) }
            actionCart?.totalCartPrice.let {
                binding.totalPrice.text = buildString {
                    append(it.toString())
                    append(" $")
                }
            }

            viewModel.cartMessageLiveData.observe(viewLifecycleOwner) {
                Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
            }


        }
    }

    private fun initViews()
    {
        binding.cartRecyclerViewe.adapter = cartAdapter

        cartAdapter.onIncrementClickListener =
            CartAdapter.OnItemClickListener { product, position ->
                updateCountProduct(product.product!!, position, product.count!! + 1)
            }
        cartAdapter.onDecrementClickListener =
            CartAdapter.OnItemClickListener { product, position ->
                updateCountProduct(product.product!!, position, product.count!! - 1)
            }

        binding.cleanCart.setOnClickListener {
            viewModel.clearCart(viewModel.cartLiveData.value?.cartId!!)
            cartAdapter.changeData(emptyList())
            binding.totalPrice.text = buildString {
                append(0.toString())
                append(" $")
            }
        }
    }

    private fun updateCountProduct(
        product: Product,
        position: Int,
        quantity: Int = product.count!! + 1
    ) {
        viewModel.updateCart(product.id!!, quantity)
        cartAdapter.changeItemDate(position)
    }


}