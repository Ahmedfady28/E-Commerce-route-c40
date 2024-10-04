package com.example.e_commerce_route_c40.base


import android.annotation.SuppressLint
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding


abstract class BaseAdapter<TypeItemList, VB : ViewBinding>(private val animationEffect: Int? = null) :
    RecyclerView.Adapter<BaseAdapter<TypeItemList, VB >.ViewHolder>() {
    var item: MutableList<TypeItemList>? = null

    inner class ViewHolder(val binding: VB) : RecyclerView.ViewHolder(binding.root)

    abstract fun getBinding(parent: ViewGroup, viewType: Int): VB

    abstract fun bindData(binding: VB, item: TypeItemList, position: Int)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = getBinding(parent, viewType)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = item?.get(position)
        bindData(holder.binding, item!!, position)
        if (animationEffect != null)
            holder.itemView.animation =
                AnimationUtils.loadAnimation(holder.itemView.context, animationEffect)
    }

    override fun getItemCount(): Int = item?.size ?: 0

    fun addDataToList(item: TypeItemList) {
        this.item?.add(item)
        notifyItemChanged(this.item?.size!! - 1)
    }

    @SuppressLint("NotifyDataSetChanged")
    open fun addDataToList(items: List<TypeItemList>) {
        if (this.item == null)
        {
            this.item = mutableListOf()
        }
        this.item?.addAll(items)
        notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    open fun changeData(items: List<TypeItemList>)
    {
        this.item = items.toMutableList()
        notifyDataSetChanged()
    }

    fun changeItemDate(position: Int) = notifyItemChanged(position)

    fun removeItem(position: Int) {
        item?.removeAt(position)
        notifyItemChanged(position)
    }

    fun removeItem(item: TypeItemList) {
        val index = this.item?.indexOf(item) ?: -1
        if (index != -1)
            this.item!!.removeAt(index)
        return
    }

    fun getItem(index: Int): TypeItemList? {
        if (index > -1 && index < item?.size!!) return item!![index]
        return null

    }

    fun getItems(): MutableList<TypeItemList>? = item
}