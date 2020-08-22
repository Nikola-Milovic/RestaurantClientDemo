package com.nikolam.menu.data

import com.nikolam.core.model.MenuItem
import com.nikolam.menu.data.network.NetworkDataSource
import kotlinx.coroutines.flow.Flow

class ImplMenuRepository(private val firebaseDataSource : NetworkDataSource): IMenuRepository {

    override fun fetchMenuItems(): Flow<MenuItem> {
        return firebaseDataSource.fetchMenuItems()
    }

    override fun fetchMenuItem(itemID: String) : Flow<MenuItem> {
        return firebaseDataSource.fetchMenuItem(itemID)
    }
}