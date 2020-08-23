package com.nikolam.core.order

import com.nikolam.core.model.MenuItem
import com.nikolam.core.model.OrderItem

class OrderPersistant () {

    private val orderItems  = arrayListOf<OrderItem>()

    fun addOrderItem(menuItem: MenuItem){

        menuItem.prices.forEach {price ->
            if (price.amount > 0) {
                val orderItem = OrderItem()
                orderItem.price = price.price * price.amount
                orderItem.name = menuItem.name + " " + price.option + "       X " + price.amount.toString() + "     " + orderItem.price.toString() + "RSD"
                orderItems.add(orderItem)
            }
        }
    }

    fun removeOrderItem(orderItem: OrderItem){

    }

    fun getOrderItems() : ArrayList<OrderItem>{
        return orderItems
    }


}
