package com.example.e_commerce_route_c40.ui.fragments.cart

import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.e_commerce_route_c40.R
import com.example.e_commerce_route_c40.base.BaseAdapter
import com.example.e_commerce_route_c40.databinding.ItemCartBinding
import com.route.domain.model.ProductItemCart

class CartAdapter : BaseAdapter<ProductItemCart, ItemCartBinding>() {

    var onIncrementClickListener: OnItemClickListener? = null
    var onDecrementClickListener: OnItemClickListener? = null

    override fun getBinding(parent: ViewGroup, viewType: Int): ItemCartBinding =
        ItemCartBinding.inflate(LayoutInflater.from(parent.context), parent, false)


    override fun bindData(binding: ItemCartBinding, item: ProductItemCart, position: Int) {
        binding.apply {
            productNamee.text = item.product?.title
            productPricee.text = buildString {
                append(item.price.toString())
                append(" $")
            }

            Glide.with(root.context)
                .load(item.product?.imageCover)
                .placeholder(R.drawable.img_coming_soon)
                .into(productImagee)

            tvCounterItemCart.text = item.count.toString()

            btnIncItemCart.setOnClickListener {
                onIncrementClickListener?.onItemClick(item, position)
            }
            btnDecItemCart.setOnClickListener {
                onDecrementClickListener?.onItemClick(item, position)
            }

        }


    }

    fun interface OnItemClickListener {
        fun onItemClick(product: ProductItemCart, position: Int)
    }
}
