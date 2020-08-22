package com.nikolam.core.model

data class MenuItem(var name : String = "", var itemID : String = "")

//, var prices : ArrayList<Price>

data class Price(var option : String = "", var price : Int = 0)