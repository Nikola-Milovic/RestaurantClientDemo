package com.nikolam.menu.ui.menu.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.nikolam.menu.R
import com.nikolam.core.model.MenuItem
import com.nikolam.menu.databinding.MenuItemBinding
import com.nikolam.core.utils.bindings
import com.nikolam.menu.ui.menu.MenuFragmentDirections


class MenuAdapter() :
    RecyclerView.Adapter<MenuAdapter.MenuItemViewHolder>() {

    private var menuItems : ArrayList<MenuItem> = arrayListOf()

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MenuItemViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.menu_item, parent, false)
        return MenuItemViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: MenuItemViewHolder, position: Int) {
        val data = menuItems[position]
        try {
            holder.bindData(data)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun addMenuItems(menuItemsList : ArrayList<MenuItem>) {
        menuItems.clear()
        menuItems.addAll(menuItemsList)
        this.notifyDataSetChanged()
    }

    override fun getItemCount() : Int {
        return menuItems.size
    }


    inner class MenuItemViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

        private lateinit var data: MenuItem
        private val binding: MenuItemBinding by bindings(view)

        fun bindData(data: Any) {
            if (data is MenuItem) {
                this.data = data
                drawItemUI()
                view.setOnClickListener {
                    val directions = MenuFragmentDirections.actionMenuFragmentToItemDetail(data.itemID)
                    view.findNavController().navigate(directions)
                }
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
