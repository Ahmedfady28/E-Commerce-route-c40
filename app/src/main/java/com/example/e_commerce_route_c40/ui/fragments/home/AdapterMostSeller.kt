package com.example.e_commerce_route_c40.ui.fragments.home

import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.e_commerce_route_c40.base.BaseAdapter
import com.example.e_commerce_route_c40.databinding.ItemHomeMostSellerBinding
import com.route.domain.model.Product


class AdapterMostSeller : BaseAdapter<Product, ItemHomeMostSellerBinding>() {


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
            }

        }
    }


}