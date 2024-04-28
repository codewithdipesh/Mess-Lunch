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

    private val _uiState = MutableStateFlow(OrderUiState())
     val uiState : StateFlow<OrderUiState> = _uiState.asStateFlow()

     fun updateEntree(entree: Entree, quantity: Int) {
         Log.d("QUANTITY UPDATE","called")
        _uiState.update { currentState ->
            val updatedEntreeItems = currentState.entreeItems.orEmpty().toMutableMap()
            updatedEntreeItems[entree] = quantity
            updateState(currentState.copy(entreeItems = updatedEntreeItems))
        }
    }
     fun updateMain(main : Main, quantity : Int){
        _uiState.update {currentState ->
            val updatedMainItems = currentState.mainItems.orEmpty().toMutableMap()
            updatedMainItems[main]= quantity
            updateState(currentState.copy(mainItems = updatedMainItems))
        }

    }
     fun updateDessert(dessert : Dessert,quantity : Int){
        _uiState.update {currentState ->
            val updateDessertItems = currentState.dessertItems.orEmpty().toMutableMap()
            updateDessertItems[dessert]= quantity
            updateState(currentState.copy(dessertItems = updateDessertItems))
        }
    }

    fun resetOrder() {
        _uiState.value = OrderUiState()
    }


    private fun updateState(newState: OrderUiState):OrderUiState{
        Log.d("QUANTITY UPDATE","update state called")
        val itemtotalPrice = calculateTotalItemPrice(newState)
        Log.d("total price",itemtotalPrice.toString())
        val tax = itemtotalPrice * taxrate
        val totalPrice = itemtotalPrice + tax
        return newState.copy(
            itemtotalPrice = itemtotalPrice,
            tax = tax,
            totalPrice = totalPrice
        )

    }

     fun calculateTotalItemPrice(uiState: OrderUiState):Double{
        return calculateItemPrice(uiState.entreeItems) + calculateItemPrice(uiState.mainItems) + calculateItemPrice(uiState.dessertItems)
    }

    private fun calculateItemPrice(categoryItems: MutableMap<MenuItem, Int>) : Double{
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