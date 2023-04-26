package com.example.unitbooks.util

import java.text.NumberFormat
import java.util.*

const val PRICE = 10.00

fun getBookPrice(): String{
    return NumberFormat.getCurrencyInstance(Locale("pt", "br")).format(PRICE)
}