package com.example.messlunch.Model

data class OrderUiState(
    //set of items and qty
    val entreeItems: MutableMap<MenuItem, Int>? = null,
    val mainItems: MutableMap<MenuItem, Int>? = null,
    val dessertItems: MutableMap<MenuItem, Int>? = null,
    val itemtotalPrice :Double =0.0,
    val tax:Double = 0.0,
    val totalPrice : Double = 0.0
)
