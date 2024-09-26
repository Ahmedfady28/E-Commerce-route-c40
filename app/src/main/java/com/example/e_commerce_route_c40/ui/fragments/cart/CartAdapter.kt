package com.example.e_commerce_route_c40.ui.fragments.cart

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.e_commerce_route_c40.databinding.ItemCartBinding
import com.route.domain.model.Product

class CartAdapter(
    private var products: List<Product> = listOf()
) : RecyclerView.Adapter<CartAdapter.ProductViewHolder>()
{

    inner class ProductViewHolder(val binding: ItemCartBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder
    {
        val binding = ItemCartBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int)
    {
        val product = products[position]
        holder.binding.apply {
            productNamee.text = product.title
            productPricee.text = "EGP ${product.price}"
            Glide.with(root.context)
                .load(product.imageCover)
                .into(productImagee)

        }
    }

    override fun getItemCount(): Int = products.size

    fun updateProducts(newProducts: List<Product>)
    {
        products = newProducts
        notifyDataSetChanged()
    }
}
