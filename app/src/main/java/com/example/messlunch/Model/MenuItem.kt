package com.example.messlunch.Model

import java.text.NumberFormat

sealed class MenuItem (
  open val title : String,
  open val description : String,
  open val price : Double
) {
  fun formattedPrice () : String = NumberFormat.getCurrencyInstance().format(price)
}
  data class Entree(
    override val title: String,
    override val description: String,
    override val price: Double
  ):MenuItem(title,description, price){}

  data class Main(
    override val title: String,
    override val description: String,
    override val price: Double
  ):MenuItem(title,description, price){}

  data class Dessert(
    override val title: String,
    override val description: String,
    override val price: Double
  ):MenuItem(title,description, price){}




