package com.example.e_commerce_route_c40.util

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.e_commerce_route_c40.base.BaseAdapter

fun setupSwipeToDelete(recyclerView: RecyclerView, adapter: BaseAdapter<*, *>) {
    val swipeToDeleteCallback =
        object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            private val background = ColorDrawable(Color.RED)

            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                // We are not moving items, so return false
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                // Remove the swiped item from your data list and notify the adapter
                val position = viewHolder.adapterPosition
                adapter.removeItem(position) // Ensure you have a method in your adapter to delete items
            }

            override fun onChildDraw(
                c: Canvas,
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                dX: Float,
                dY: Float,
                actionState: Int,
                isCurrentlyActive: Boolean
            ) {
                val itemView = viewHolder.itemView
                if (dX > 0) {
                    // Swiping to the right
                    background.setBounds(
                        itemView.left,
                        itemView.top,
                        itemView.left + dX.toInt(),
                        itemView.bottom
                    )
                } else if (dX < 0) {
                    // Swiping to the left
                    background.setBounds(
                        itemView.right + dX.toInt(),
                        itemView.top,
                        itemView.right,
                        itemView.bottom
                    )
                }
                background.draw(c)

                // Optionally, draw delete icon (if you have one)
                super.onChildDraw(
                    c,
                    recyclerView,
                    viewHolder,
                    dX,
                    dY,
                    actionState,
                    isCurrentlyActive
                )
            }
        }

    val itemTouchHelper = ItemTouchHelper(swipeToDeleteCallback)
    itemTouchHelper.attachToRecyclerView(recyclerView)
}
