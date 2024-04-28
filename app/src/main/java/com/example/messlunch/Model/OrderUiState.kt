package com.example.messlunch.Model

data class OrderUiState(
    //set of items and qty
    val entreeItems: MutableMap<MenuItem, Int> = mutableMapOf(),
    val mainItems: MutableMap<MenuItem, Int> = mutableMapOf(),
    val dessertItems: MutableMap<MenuItem, Int> = mutableMapOf(),
    val itemtotalPrice :Double =0.0,
    val tax:Double = 0.0,
    val totalPrice : Double = 0.0
) {

}
