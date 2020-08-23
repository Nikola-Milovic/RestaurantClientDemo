package com.nikolam.order.data.network

import com.nikolam.core.model.MenuItem
import com.nikolam.order.data.model.Order
import kotlinx.coroutines.flow.Flow

interface NetworkDataSource {
    fun placeOrder(order : Order)
}