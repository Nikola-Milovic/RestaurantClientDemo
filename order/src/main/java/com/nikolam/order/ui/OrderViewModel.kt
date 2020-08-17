package com.nikolam.order.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nikolam.core.model.MenuItem
import com.nikolam.core.model.OrderItem
import com.nikolam.core.order.OrderPersistant

class OrderViewModel(private val orderPersistant: OrderPersistant) : ViewModel() {


    val orderItemsLiveData : MutableLiveData<ArrayList<OrderItem>> = MutableLiveData()
    val _orderItemsLiveData : LiveData<ArrayList<OrderItem>>
        get() = orderItemsLiveData


    fun getOrderItems() {

        orderItemsLiveData.value = orderPersistant.getOrderItems()

    }


}