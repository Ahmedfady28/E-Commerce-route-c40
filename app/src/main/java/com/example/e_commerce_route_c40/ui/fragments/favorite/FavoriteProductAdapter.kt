package com.example.e_commerce_route_c40.ui.fragments.favorite

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.e_commerce_route_c40.databinding.ItemProductFavoriteBinding
import com.route.domain.model.Product

class FavoriteProductAdapter(
    private var products: List<Product> = listOf()
) : RecyclerView.Adapter<FavoriteProductAdapter.ProductViewHolder>() {

    inner class ProductViewHolder(val binding: ItemProductFavoriteBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding = ItemProductFavoriteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = products[position]
        holder.binding.apply {

        textViewTitle.text = product.title
            textViewSubtitle.text = product.description
            textViewNewPrice.text = "EGP ${product.price}"
            Glide.with(root.context)
                .load(product.imageCover)
                .into(productImage)
        }
    }

    override fun getItemCount(): Int = products.size

    fun updateProducts(newProducts: List<Product>) {
        products = newProducts
        notifyDataSetChanged()
    }
}
