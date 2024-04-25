package com.example.messlunch.Model

data class UiState(
    //set of items and qty
    val entreeItems: MutableMap<MenuItem.Entree, Int?>? = null,
    val mainItems: MutableMap<MenuItem.Main, Int?>? = null,
    val dessertItems: MutableMap<MenuItem.Dessert, Int?>? = null,
    val itemtotalPrice :Double =0.0,
    val tax:Double = 0.0,
    val totalPrice : Double = 0.0
)
