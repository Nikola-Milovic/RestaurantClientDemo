package com.nikolam.order.data

import com.nikolam.core.model.MenuItem
import com.nikolam.order.data.IMenuRepository
import com.nikolam.order.data.model.Order
import com.nikolam.order.data.network.NetworkDataSource
import kotlinx.coroutines.flow.Flow

class ImplMenuRepository(private val firebaseDataSource : NetworkDataSource): IMenuRepository {
    override fun placeOrder(order: Order) {
        firebaseDataSource.placeOrder(order)
    }

}