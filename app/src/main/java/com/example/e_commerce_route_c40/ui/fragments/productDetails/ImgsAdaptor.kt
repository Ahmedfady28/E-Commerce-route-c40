package com.example.e_commerce_route_c40.ui.fragments.productDetails

import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.e_commerce_route_c40.R
import com.example.e_commerce_route_c40.base.BaseAdapter
import com.example.e_commerce_route_c40.databinding.ProductDetailsImageItemBinding

class ImgsAdaptor : BaseAdapter<String?, ProductDetailsImageItemBinding>() {
    override fun getBinding(parent: ViewGroup, viewType: Int): ProductDetailsImageItemBinding =
        ProductDetailsImageItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

    override fun bindData(binding: ProductDetailsImageItemBinding, item: String?, position: Int) {
        Glide.with(binding.root.context)
            .load(item)
            .placeholder(R.drawable.img_coming_soon)
            .into(binding.root)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val actualPosition = position % this.getItemCount()
        super.onBindViewHolder(holder, actualPosition)
    }
}