package com.nikolam.menu.ui.detail.adapter

import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nikolam.core.model.MenuItem
import com.nikolam.core.model.Price
import com.nikolam.core.utils.bindings
import com.nikolam.menu.R
import com.nikolam.menu.databinding.OptionsItemDetailBinding
import timber.log.Timber


class OptionsDetailAdapter() :
    RecyclerView.Adapter<OptionsDetailAdapter.OptionsDetailViewHolder>(), DataChangeListener {

    private var prices: ArrayList<Price> = arrayListOf()

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): OptionsDetailViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.options_item_detail, parent, false)
        return OptionsDetailViewHolder(view, this)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holderDetail: OptionsDetailViewHolder, position: Int) {
        val data = prices[position]
        try {
            holderDetail.bindData(data)
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


    fun getPrices(): ArrayList<Price> {
        Timber.d("prices are $prices")
        return prices
    }


    fun addNewOption() {
        val price = Price("Promeniti", 0)
        while (prices.contains(price)) {
            val rnds = (0..100).random()
            price.option = "Promeniti$rnds"
        }
        prices.add(price)
        this.notifyDataSetChanged()
    }

    inner class OptionsDetailViewHolder(val view: View, val listener: DataChangeListener) :
        RecyclerView.ViewHolder(view) {

        private lateinit var data: Price
        private val binding: OptionsItemDetailBinding by bindings(view)

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


                addActionButton.setOnClickListener {

                    listener.add(data)

                }
            }
        }
    }

    override fun addItem(item: MenuItem) {
        TODO("Not yet implemented")
    }

    override fun subtractItem(item: MenuItem) {
        TODO("Not yet implemented")
    }

}


interface DataChangeListener {
    fun addItem(item : MenuItem)

    fun subtractItem(item : MenuItem)

}
