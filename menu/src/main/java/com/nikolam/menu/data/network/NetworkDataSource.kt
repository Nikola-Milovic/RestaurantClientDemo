package com.nikolam.menu.data.network

import com.nikolam.core.model.MenuItem
import kotlinx.coroutines.flow.Flow

interface NetworkDataSource {

    fun fetchMenuItems() : Flow<MenuItem>

    fun fetchMenuItem(itemID : String) : Flow<MenuItem>
}