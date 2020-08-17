package com.nikolam.menu.ui.menu

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nikolam.core.model.MenuItem
import com.nikolam.menu.data.IMenuRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import timber.log.Timber

class MenuViewModel(private val repository: IMenuRepository) : ViewModel() {

    val menuItemsLiveData : MutableLiveData<ArrayList<MenuItem>> = MutableLiveData()
    val _menuItemsLiveData : LiveData<ArrayList<MenuItem>>
        get() = menuItemsLiveData


    fun fetchMenuItems() {
        var foodList = arrayListOf<MenuItem>()
        viewModelScope.launch(Dispatchers.IO) {
            repository.fetchMenuItems().collect{ value ->
                Timber.d(value.toString())
                foodList.add(value)
            }

            menuItemsLiveData.postValue(foodList)
        }
    }
}