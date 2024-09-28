package com.example.e_commerce_route_c40.ui.fragments.home

import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.e_commerce_route_c40.R
import com.example.e_commerce_route_c40.base.BaseAdapter
import com.example.e_commerce_route_c40.databinding.ItemHomeMostSellerBinding
import com.route.domain.model.Product


class AdapterMostSeller : BaseAdapter<Product, ItemHomeMostSellerBinding>() {


    var onProductClickListener: OnItemClickListener? = null
    var onFavoriteClickListener: OnItemClickListener? = null
    var onAddCartClickListener: OnItemClickListener? = null
    override fun getBinding(parent: ViewGroup, viewType: Int): ItemHomeMostSellerBinding =
        ItemHomeMostSellerBinding.inflate(LayoutInflater.from(parent.context), parent, false)

    override fun bindData(binding: ItemHomeMostSellerBinding, item: Product, position: Int) {
        binding.apply {
            Glide.with(root)
                .load(item.imageCover)
                .into(ivProduct)

            tvProductName.text = item.title
            tvReviewCount.text = item.ratingsAverage.toString()

            tvPrice.text = buildString {
                append(item.price?.toString())
                append(" $")

                icFavoriteBlue.setImageResource(
                    if (item.isLiked) R.drawable.ic_favoriets_selectet
                    else R.drawable.ic_favoriets_unselected
                )


                root.setOnClickListener {
                    onProductClickListener?.onItemClick(item, position)
                }
                icFavoriteBlue.setOnClickListener {
                    onFavoriteClickListener?.onItemClick(item, position)
                }

                icFavoriteBlue.setOnClickListener {
                    onFavoriteClickListener?.onItemClick(item, position)
                }
                btnAddCart.setOnClickListener {
                    onAddCartClickListener?.onItemClick(item, position)
                }
            }

        }
    }

    fun interface OnItemClickListener {
        fun onItemClick(product: Product?, position: Int)
    }
}