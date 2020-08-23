package com.nikolam.order.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nikolam.core.model.MenuItem
import com.nikolam.core.model.OrderItem
import com.nikolam.core.order.OrderPersistant
import com.nikolam.order.data.IMenuRepository
import com.nikolam.order.data.model.Order
import timber.log.Timber

class OrderViewModel(private val orderPersistant: OrderPersistant, private val repository : IMenuRepository) : ViewModel() {


    val orderItemsLiveData : MutableLiveData<ArrayList<OrderItem>> = MutableLiveData()
    val _orderItemsLiveData : LiveData<ArrayList<OrderItem>>
        get() = orderItemsLiveData


    fun getOrderItems() {

        orderItemsLiveData.value = orderPersistant.getOrderItems()

    }

    fun orderPlaced(tableNumber : String){

        val orderItems = arrayListOf<String>()

        orderPersistant.getOrderItems().forEach {
            orderItems.add(it.text)
        }

        val order = Order(orderItems, tableNumber)
        repository.placeOrder(order)
    }


}