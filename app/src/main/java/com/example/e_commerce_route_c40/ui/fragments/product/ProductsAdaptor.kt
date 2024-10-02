package com.example.e_commerce_route_c40.ui.fragments.product

import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.e_commerce_route_c40.R
import com.example.e_commerce_route_c40.base.BaseAdapter
import com.example.e_commerce_route_c40.databinding.ItemProductBinding
import com.route.domain.model.Product

class ProductsAdaptor :
    BaseAdapter<Product, ItemProductBinding>(R.anim.full_down) {


    override fun getBinding(parent: ViewGroup, viewType: Int): ItemProductBinding {
        return ItemProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    }

    override fun bindData(binding: ItemProductBinding, item: Product, position: Int) {
        binding.tvProductDetails.text = item.title
        binding.tvProductPrice.text = buildString {
            append(item.price?.toString())
            append(" $")
        }

        binding.tvProductReviewValue.text = item.ratingsAverage.toString()

        Glide.with(binding.root.context)
            .load(item.imageCover)
            .into(binding.imgProduct)
        binding.imgFavoriteProduct.apply {
            setImageResource(
                if(item.isLiked) R.drawable.ic_favoriets_selectet
                else R.drawable.ic_favoriets_unselected
            )
        }
        binding.imgFavoriteProduct.setOnClickListener {
            onFavoriteClickListener?.onItemClick(item,position)
        }
        binding.imgAddToCard.setOnClickListener {
            onAddClickListener?.onItemClick(item, position)
        }
        binding.root.setOnClickListener {
            onProductClickListener?.onItemClick(item, position)
        }

        if (position == itemCount - 1){
            onReachedBottom?.let {
                it()
            }
        }
    }


    var onFavoriteClickListener : OnItemClickListener? = null
    var onProductClickListener: OnItemClickListener? = null

    var onAddClickListener: OnAddedClickListener? = null

    fun interface OnItemClickListener {
        fun onItemClick(product: Product?, position: Int)
    }

    fun interface OnAddedClickListener
    {
        fun onItemClick(product: Product?, position: Int)
    }

    var onReachedBottom: (() -> Unit)? = null
}