package com.nikolam.menu.ui.menu.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nikolam.core.model.Price
import com.nikolam.core.utils.bindings
import com.nikolam.menu.R
import com.nikolam.menu.databinding.OptionsItemBinding


class OptionsAdapter() :
    RecyclerView.Adapter<OptionsAdapter.OptionsViewHolder>() {

    private var prices: ArrayList<Price> = arrayListOf()

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): OptionsViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.options_item, parent, false)
        return OptionsViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: OptionsViewHolder, position: Int) {
        val data = prices[position]
        try {
            holder.bindData(data)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun addPriceOptions(priceOptionList: ArrayList<Price>) {
        prices.clear()
        prices.addAll(priceOptionList)
        this.notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return prices.size
    }


    inner class OptionsViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

        private lateinit var data: Price
        private val binding: OptionsItemBinding by bindings(view)

        fun bindData(data: Any) {
            if (data is Price) {
                this.data = data

                drawItemUI()
            }
        }

        private fun drawItemUI() {
            binding.apply {
                price = data
                executePendingBindings()
            }
        }
    }
}
