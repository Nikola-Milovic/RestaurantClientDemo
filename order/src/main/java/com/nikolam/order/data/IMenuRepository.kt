package com.nikolam.order.data

import com.nikolam.core.model.MenuItem
import com.nikolam.order.data.model.Order
import kotlinx.coroutines.flow.Flow

interface IMenuRepository {
    fun placeOrder(order : Order)
}