package com.nikolam.menu.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nikolam.core.model.MenuItem
import com.nikolam.core.order.OrderPersistant
import com.nikolam.menu.data.IMenuRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class ItemDetailViewModel(private val repository : IMenuRepository, private val orderPersistant: OrderPersistant) : ViewModel() {

    val itemLiveData : MutableLiveData<MenuItem> = MutableLiveData()
    val _itemLiveData : LiveData<MenuItem>
        get() = itemLiveData


    fun fetchMenuItem(itemID : String) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.fetchMenuItem(itemID).collect {
                itemLiveData.postValue(it)
            }
        }
    }

    fun orderMenuItem(menuItem: MenuItem){
        orderPersistant.addOrderItem(menuItem)
    }
}