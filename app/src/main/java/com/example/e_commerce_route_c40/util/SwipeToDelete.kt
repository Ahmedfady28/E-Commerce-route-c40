package com.example.e_commerce_route_c40.util

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView

abstract class SwipeToDelete :
    ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean = false

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                // Remove the swiped item from your data list and notify the adapter
                val position = viewHolder.adapterPosition
               // adapter.removeItem(position)
               // Ensure you have a method in your adapter to delete items
            }

}

//    val itemTouchHelper = ItemTouchHelper(swipeToDeleteCallback)
//    itemTouchHelper.attachToRecyclerView(recyclerView)