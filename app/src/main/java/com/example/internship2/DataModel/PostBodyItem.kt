package com.example.internship2.DataModel

data class PostBodyItem(
    val categoryId: String,
    val isLowCountItems: Boolean,
    val offset: Int,
    val searchString: String,
    val storeId: String
)