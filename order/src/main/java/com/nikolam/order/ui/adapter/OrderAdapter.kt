package com.nikolam.order.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.nikolam.core.model.OrderItem
import com.nikolam.core.utils.bindings
import com.nikolam.order.R
import com.nikolam.order.databinding.OrderItemBinding
import timber.log.Timber


class OrderAdapter() :
    RecyclerView.Adapter<OrderAdapter.OrderViewHolder>() {

    private var menuItems : ArrayList<OrderItem> = arrayListOf()

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): OrderViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.order_item, parent, false)
        return OrderViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {
        val data = menuItems[position]
        try {
            Timber.d("binding data ${data.toString()}")
            holder.bindData(data)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun addOrderItems(menuItemsList : ArrayList<OrderItem>) {
        menuItems.clear()
        menuItems.addAll(menuItemsList)
        this.notifyDataSetChanged()
    }

    override fun getItemCount() : Int {
        return menuItems.size
    }


    inner class OrderViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private lateinit var data: OrderItem
        private val binding: OrderItemBinding by bindings(view)

        fun bindData(data: Any) {
            if (data is OrderItem) {
                this.data = data
                drawItemUI()
            }
        }

        private fun drawItemUI() {
            binding.apply {
                item = data
                executePendingBindings()
            }
        }

    }

}
