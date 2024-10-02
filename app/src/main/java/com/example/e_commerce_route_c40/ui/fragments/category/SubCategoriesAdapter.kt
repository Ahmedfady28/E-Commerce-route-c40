package com.example.e_commerce_route_c40.ui.fragments.category

import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.e_commerce_route_c40.R
import com.example.e_commerce_route_c40.base.BaseAdapter
import com.example.e_commerce_route_c40.databinding.ItemSubCategoryInCategoriesBinding
import com.route.domain.model.SubCategory

class SubCategoriesAdapter :
    BaseAdapter<SubCategory, ItemSubCategoryInCategoriesBinding>(R.anim.full_down) {

    var onItemClickListener: OnItemClickListener? = null

    override fun getBinding(parent: ViewGroup, viewType: Int): ItemSubCategoryInCategoriesBinding =
        ItemSubCategoryInCategoriesBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

    override fun bindData(
        binding: ItemSubCategoryInCategoriesBinding,
        item: SubCategory,
        position: Int
    ) {
        binding.apply {
            irvSubCategoryTxt.text = item.name
            root.setOnClickListener {
                onItemClickListener?.onItemClick(item,position)
            }
            Glide.with(binding.root.context)
                .load(item.image)
                .placeholder(R.drawable.img_coming_soon)
                .into(irvSubCategoryCardImg)
        }
    }
    fun interface OnItemClickListener{
        fun onItemClick(subCategory: SubCategory,position: Int)
    }
}