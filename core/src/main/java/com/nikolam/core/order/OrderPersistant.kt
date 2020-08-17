package com.nikolam.core.order

import com.nikolam.core.model.MenuItem
import com.nikolam.core.model.OrderItem

class OrderPersistant () {

    private val orderItems  = arrayListOf<OrderItem>(OrderItem("Pizza", 2, ""), OrderItem("Pasta", 1 , ""))

    fun addOrderItem(menuItem: MenuItem){
        //
    }

    fun removeOrderItem(orderItem: OrderItem){

    }

    fun getOrderItems() : ArrayList<OrderItem>{
        return orderItems
    }


}


interface orderManager

interface orderListener: orderManager

interface menuListener :orderManager