package com.example.messlunch.ui

import android.nfc.Tag
import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.messlunch.Model.Dessert
import com.example.messlunch.Model.MenuItem
import com.example.messlunch.Model.Entree
import com.example.messlunch.Model.Main
import com.example.messlunch.Model.OrderItem
import com.example.messlunch.Model.OrderUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.text.NumberFormat


class OrderViewModel : ViewModel(){

    private val taxrate = 0.08
     var currentState: OrderUiState = OrderUiState()


    private val _uiState = MutableStateFlow(currentState)
     val uiState : StateFlow<OrderUiState> = _uiState.asStateFlow()

    fun updateItem(item: MenuItem, quantity: Int) {

        Log.d("currentState_before", currentState.toString())
        val updatedItems = currentState.selectedItems.toMutableList()
        val existingItem = updatedItems.find { it.menuItem == item }
        if(existingItem != null){
            updatedItems.remove(existingItem)
            updatedItems.add(existingItem.copy(quantity = quantity))
        }else{
            updatedItems.add(OrderItem(item,quantity))
        }

        val newState = currentState.copy(
            selectedItems = updatedItems
        )
        currentState = newState
        Log.d("currentState_after", currentState.toString())
        updateState()
    }


    fun resetOrder() {
        currentState = OrderUiState()
        _uiState.value = currentState
    }


    private fun updateState() {
        Log.d("QUANTITY UPDATE","update state called")
        val itemtotalPrice = calculateTotalItemPrice()
        Log.d("total price",itemtotalPrice.toString())
        val tax = itemtotalPrice * taxrate
        val totalPrice = itemtotalPrice + tax
        Log.d("UpdateState", "itemtotalPrice: $itemtotalPrice, tax: $tax, totalPrice: $totalPrice")

        currentState = currentState.copy(
            itemtotalPrice = itemtotalPrice,
            tax = tax,
            totalPrice = totalPrice
        )
        Log.d("total price",currentState.itemtotalPrice.toString())
        _uiState.value = currentState


    }

     fun calculateTotalItemPrice():Double{

         var totalprice = 0.0
         currentState.selectedItems.forEach { item->
             totalprice += calculateItemPrice(item)
         }
         return totalprice
     }

    private fun calculateItemPrice(categoryItem: OrderItem) : Double{
        return categoryItem.menuItem.price * categoryItem.quantity

    }
}

fun Double.formatPrice(): String {
    return NumberFormat.getCurrencyInstance().format(this)
}