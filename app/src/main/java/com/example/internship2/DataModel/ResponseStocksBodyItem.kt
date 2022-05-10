package com.example.internship2.DataModel

data class ResponseStocksBodyItem(
    val availableCount: String,
    val isItemCountLow: Boolean,
    val lastUpdatedTime: String,
    val lowCountLimit: Double,
    val mrp: String,
    val name: String,
    val priceUnit: String,
    val purchasePrice: String,
    val sellingPrice: String,
    val stockItemId: String,
    val storeId: String
)