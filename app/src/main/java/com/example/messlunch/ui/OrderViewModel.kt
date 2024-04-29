package com.example.messlunch.ui

import android.nfc.Tag
import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.messlunch.Model.Dessert
import com.example.messlunch.Model.MenuItem
import com.example.messlunch.Model.Entree
import com.example.messlunch.Model.Main
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

    fun updateEntree(entree: Entree, quantity: Int) {
        val updatedItems = currentState.entreeItems.toMutableList()

        // Check if the item already exists in the list
        val existingItem = updatedItems.find { it.first == entree }

        if (existingItem != null) {
            val updatedQuantity = existingItem.second + quantity
            updatedItems.remove(existingItem)
            updatedItems.add(entree to updatedQuantity)
        } else {
            updatedItems.add(entree to quantity)
        }

        currentState = currentState.copy(
            entreeItems = updatedItems
        )

        updateState()
    }


     fun updateMain(main : Main, quantity : Int){


    }
     fun updateDessert(dessert : Dessert,quantity : Int){

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
        currentState = currentState.copy(
            itemtotalPrice = itemtotalPrice,
            tax = tax,
            totalPrice = totalPrice
        )


    }

     fun calculateTotalItemPrice():Double{
        return calculateItemPrice(currentState.entreeItems) + calculateItemPrice(currentState.mainItems) + calculateItemPrice(currentState.dessertItems)
    }

    private fun calculateItemPrice(categoryItems: MutableList<Pair<MenuItem, Int>>) : Double{
        var totalprice = 0.0
        if(categoryItems.isEmpty()){
            return 0.0
        }
            categoryItems.forEach { (menuItem, quantity) ->
                totalprice += menuItem.price * quantity

        }
        return totalprice
    }
}

fun Double.formatPrice(): String {
    return NumberFormat.getCurrencyInstance().format(this)
}