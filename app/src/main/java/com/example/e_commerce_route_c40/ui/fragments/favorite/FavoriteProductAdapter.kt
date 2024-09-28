package com.example.e_commerce_route_c40.ui.fragments.favorite

import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.e_commerce_route_c40.base.BaseAdapter
import com.example.e_commerce_route_c40.databinding.ItemProductFavoriteBinding
import com.route.domain.model.Product

class FavoriteProductAdapter : BaseAdapter<Product, ItemProductFavoriteBinding>() {

    var onAddToCartClickListener: OnItemClickListener? = null

    override fun getBinding(parent: ViewGroup, viewType: Int): ItemProductFavoriteBinding =
        ItemProductFavoriteBinding.inflate(LayoutInflater.from(parent.context), parent, false)


    override fun bindData(binding: ItemProductFavoriteBinding, item: Product, position: Int) {
        binding.apply {
            textViewTitle.text = item.title
            textViewSubtitle.text = item.description
            textViewNewPrice.text = buildString {
                append(item.price?.toString())
                append(" $")

                addToCartButton.setOnClickListener {
                    onAddToCartClickListener?.onItemClick(item, position)
                }
            }
            Glide.with(root.context)
                .load(item.imageCover)
                .into(productImage)
        }
    }

    fun interface OnItemClickListener {
        fun onItemClick(product: Product, position: Int)
    }
}
