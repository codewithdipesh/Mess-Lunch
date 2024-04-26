package com.example.messlunch.Datasource

import com.example.messlunch.Model.Dessert
import com.example.messlunch.Model.Main
import com.example.messlunch.Model.Entree

object Data {
    val entreeItems = listOf(
        Entree(
            title = "Ata Roti",
            description = "Round rotis made by ata , light and best for health",
            price = 1.25
        ),
        Entree(
            title = "Tandoori Roti",
            description = "Rotis made in tandoori ,full on butter with corriender garnishing",
            price = 2.99
        ),
        Entree(
            title = "Plain Rice",
            description = "Steam Basmati Rice best for healthfreaks ",
            price = 4.00
        ),
        Entree(
            title = "Fried Rice",
            description = "Rice with some veggies and eggs with the  beautifull aroma of dry fruits",
            price = 6.00
        ),

    )
    val MainItems = listOf(
        Main(
            title = "Chicken Curry",
            description = "Boneless tandoori chicken cooked with aromatic tasty gravy",
            price = 6.00
        ),
        Main(
            title = "Chilli Pannerr",
            description = "marinated roasted Paneer sauted with chili flavours ",
            price = 7.00
        ),
        Main(
            title = "Dam Aloo",
            description = "Big aloo pieces with touch of kshmiri flavours",
            price = 1.25
        )
    )

    val DessertItems = listOf(
        Dessert(
            title = "Gulab Jamuns",
            description = "Blackish Pink colored bengali sweet",
            price = 0.69
        ),
        Dessert(
            title = "Rosogulla",
            description = "Indian cottage chesse balls dipped in sugar syrup",
            price = 1.00
        ),
        Dessert(
            title = "Ladoo",
            description = "Laddoo made of Bundi ",
            price = 0.70
        )
    )

}