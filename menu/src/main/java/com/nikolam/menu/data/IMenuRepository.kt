package com.nikolam.menu.data

import com.nikolam.core.model.MenuItem
import kotlinx.coroutines.flow.Flow

interface IMenuRepository {

    fun fetchMenuItems() : Flow<MenuItem>

    fun fetchMenuItem(itemID : String) : Flow<MenuItem>
}