package com.example.messlunch.Model

data class OrderUiState(
    val entreeItems: MutableList<Pair<MenuItem, Int>> = mutableListOf(),
    val mainItems: MutableList<Pair<MenuItem, Int>> = mutableListOf(),
    val dessertItems: MutableList<Pair<MenuItem, Int>> = mutableListOf(),
    val itemtotalPrice: Double = 0.0,
    val tax: Double = 0.0,
    val totalPrice: Double = 0.0
) {

}
