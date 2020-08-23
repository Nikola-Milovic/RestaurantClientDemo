package com.nikolam.core.model


data class MenuItem(
    var name: String = "",
    var prices: ArrayList<Price> = arrayListOf(),
    var itemID: String = ""
)


data class Price(var option: String = "", var price: Int = 0, var amount : Int = 0)


