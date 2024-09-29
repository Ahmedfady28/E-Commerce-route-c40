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


}

//    val itemTouchHelper = ItemTouchHelper(swipeToDeleteCallback)
//    itemTouchHelper.attachToRecyclerView(recyclerView)

