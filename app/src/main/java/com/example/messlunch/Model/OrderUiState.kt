package com.example.messlunch.Model

data class OrderUiState(
    val selectedItems: MutableList<OrderItem> = mutableListOf(),
    val itemtotalPrice: Double = 0.0,
    val tax: Double = 0.0,
    val totalPrice: Double = 0.0
) {

}
data class OrderItem(
    val menuItem: MenuItem,
    var quantity: Int
)
