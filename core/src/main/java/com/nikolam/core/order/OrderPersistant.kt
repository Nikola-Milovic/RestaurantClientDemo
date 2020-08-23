package com.nikolam.core.order

import com.nikolam.core.model.MenuItem
import com.nikolam.core.model.OrderItem

class OrderPersistant () {

    private val orderItems  = arrayListOf<OrderItem>()

    fun addOrderItem(menuItem: MenuItem){

        menuItem.prices.forEach {price ->
            var added = false
            val fullName = menuItem.name + " " + price.option
            if (price.amount > 0) {
                for (i in orderItems.indices){
                    if (orderItems[i].name == fullName) {
                        orderItems[i].price += price.price * price.amount
                        orderItems[i].amount += price.amount
                        orderItems[i].text = fullName + "       X " + orderItems[i].amount.toString() + "     " + orderItems[i].price.toString() + "RSD"
                        added = true
                    }
                }
                if (!added) {
                    val orderItem = OrderItem()
                    orderItem.name = fullName
                    orderItem.amount = price.amount
                    orderItem.price = price.price * price.amount
                    orderItem.text =
                        fullName + "       X " + orderItem.amount.toString() + "     " + orderItem.price.toString() + "RSD"
                    orderItems.add(orderItem)
                }
            }
        }
    }

    fun removeOrderItem(orderItem: OrderItem){

    }

    fun getOrderItems() : ArrayList<OrderItem>{
        return orderItems
    }


}
