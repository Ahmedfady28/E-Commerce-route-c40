package com.example.e_commerce_route_c40.ui.fragments.home


import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.e_commerce_route_c40.base.BaseAdapter
import com.example.e_commerce_route_c40.databinding.ItemBrandBinding
import com.route.domain.model.Brand


class BrandAdapter : BaseAdapter<Brand, ItemBrandBinding>()
{
    var onBrandClickListener: OnItemClickListener? = null

    override fun getBinding(parent: ViewGroup, viewType: Int): ItemBrandBinding =
            ItemBrandBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )


    override fun bindData(binding: ItemBrandBinding, item: Brand, position: Int)
    {
        binding.apply {
            Glide.with(root.context)
                .load(item.image)
                .into(ivBrand)
            tvBrands.text = item.name

            root.setOnClickListener {
                onBrandClickListener?.onItemClick(item, position)
            }


        }

    }

    fun interface OnItemClickListener {
        fun onItemClick(brand: Brand?, position: Int)
    }

}

