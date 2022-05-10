package com.example.internship2.Networking

import androidx.lifecycle.MutableLiveData
import com.example.internship2.DataModel.PostBodyItem
import com.example.internship2.DataModel.ResponseStocksBodyItem
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface ApiInterface {

    @Headers("Content-Type: application/json")
    @POST("/api/v4/stockItem/getPaginatedItemsList")
    fun getStocks(@Body postBodyItem: ArrayList<PostBodyItem>): Call<ArrayList<ResponseStocksBodyItem>>

}