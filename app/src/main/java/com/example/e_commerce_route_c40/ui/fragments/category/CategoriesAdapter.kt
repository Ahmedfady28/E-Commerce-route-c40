package com.example.e_commerce_route_c40.ui.fragments.category


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.e_commerce_route_c40.R
import com.example.e_commerce_route_c40.base.BaseAdapter
import com.example.e_commerce_route_c40.databinding.ItemCategoryInCategoriesBinding
import com.route.domain.model.Category

class CategoriesAdapter : BaseAdapter<Category, ItemCategoryInCategoriesBinding>() {

    var selectedPosition = 0

    var onItemClickListener: OnItemClickListener? = null

    override fun getBinding(parent: ViewGroup, viewType: Int): ItemCategoryInCategoriesBinding =
        ItemCategoryInCategoriesBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )

    override fun addDataToList(items: List<Category>) {
        super.addDataToList(items)

        if(items.isEmpty()) return

        onItemClickListener?.onItemClick(
            items[0],
            position = 0
        )

    }

    override fun bindData(
        binding: ItemCategoryInCategoriesBinding,
        item: Category,
        position: Int
    ) {

        binding.tvItemCategoryInCategories.text = item.name
        handleSelectedView(binding, item, position)

        binding.root.setOnClickListener {
            updateSelectedPosition(position)
            onItemClickListener?.onItemClick(item,position)
        }
    }


    private fun handleSelectedView(binding: ItemCategoryInCategoriesBinding, category: Category?,position: Int) {
        if (position == selectedPosition) {
            binding.selectedView.visibility = View.VISIBLE
            binding.root.setBackgroundResource(R.color.white)
            onItemClickListener?.onItemClick(category,position)
        } else {
            binding.selectedView.visibility = View.GONE
            binding.root.setBackgroundResource(R.color.transparent)
        }
    }

    private fun updateSelectedPosition(position: Int) {
        val previousPosition = selectedPosition
        selectedPosition = position
        notifyItemChanged(previousPosition)
        notifyItemChanged(selectedPosition)
    }
    fun interface OnItemClickListener{
        fun onItemClick(category: Category?,position: Int)
    }
}