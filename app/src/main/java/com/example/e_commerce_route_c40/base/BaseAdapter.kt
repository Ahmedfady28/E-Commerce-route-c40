package com.example.e_commerce_route_c40.base


import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding


abstract class BaseAdapter<TypeItemList, VB : ViewBinding> :
    RecyclerView.Adapter<BaseAdapter<TypeItemList, VB >.ViewHolder>() {
    private var items: MutableList<TypeItemList>? = null

    inner class ViewHolder(val binding: VB) : RecyclerView.ViewHolder(binding.root)

    abstract fun getBinding(parent: ViewGroup, viewType: Int): VB

    abstract fun bindData(binding: VB, item: TypeItemList, position: Int)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = getBinding(parent, viewType)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items?.get(position)
        bindData(holder.binding, item!!, position)
    }

    override fun getItemCount(): Int = items?.size ?: 0

    fun addDataToList(item: TypeItemList) {
        items?.add(item)
        notifyItemChanged(items?.size!! - 1)
    }

    @SuppressLint("NotifyDataSetChanged")
    open fun addDataToList(items: List<TypeItemList>) {
        if(this.items==null){
            this.items = mutableListOf()
        }
        this.items?.addAll(items)
        notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun changeData(items: List<TypeItemList>) {
        this.items = items.toMutableList()
        notifyDataSetChanged()
    }

    fun changeItemDate(position: Int) = notifyItemChanged(position)

    fun removeItem(position: Int) {
        items?.removeAt(position)
        notifyItemChanged(position)
    }

    fun removeItem(item: TypeItemList) {
        val index = items?.indexOf(item) ?: -1
        if (index != -1)
            items!!.removeAt(index)
        return
    }
}