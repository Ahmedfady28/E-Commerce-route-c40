package com.example.e_commerce_route_c40.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.e_commerce_route_c40.base.BaseAdapter
import com.example.e_commerce_route_c40.databinding.ItemCategoryBinding
import com.route.domain.model.Category


class HomeCategoriesAdapter : BaseAdapter<Category, ItemCategoryBinding>() {
    var onItemClickListener: OnItemClickListener? = null

    override fun getBinding(parent: ViewGroup, viewType: Int): ItemCategoryBinding =
        ItemCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)


    override fun bindData(binding: ItemCategoryBinding, item: Category, position: Int) {

        binding.apply {
            Glide.with(root.context)
                .load(item.image)
                .into(ivCategories)

            tvCategories.text = item.name

            onItemClickListener.let {
                root.setOnClickListener {
                    onItemClickListener?.onItemClick(item, position)
                }
            }
        }
    }

    fun interface OnItemClickListener {
        fun onItemClick(category: Category?, position: Int)
    }
}