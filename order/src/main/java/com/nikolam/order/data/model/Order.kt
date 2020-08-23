package com.nikolam.order.data.model

import com.nikolam.core.model.OrderItem

data class Order(var orderItems : ArrayList<String> = arrayListOf(), var tableNumber : String = "")