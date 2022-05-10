package com.example.internship2

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.internship2.DataModel.PostBodyItem
import com.example.internship2.DataModel.ResponseStocksBodyItem
import com.example.internship2.Networking.NetworkUtils
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

class MainActivityViewModel: ViewModel() {

    var stocksList = mutableStateListOf<ResponseStocksBodyItem>()

    init {
        doNetworkCalls(0)
    }

    fun doNetworkCalls(i: Int) {
        viewModelScope.launch {
            try {
                var requestList: ArrayList<PostBodyItem> = ArrayList()
                var postBodyItem: PostBodyItem = PostBodyItem(
                    "ALL", false, i, "", "9a917180-f9b7-465f-b1d6-dd1b4cb5a9e6"
                )
                requestList.add(postBodyItem)
                var call = NetworkUtils().getApiInterfaceInstance().getStocks(requestList)
                call.enqueue(object: Callback<ArrayList<ResponseStocksBodyItem>> {
                    override fun onResponse(
                        call: Call<ArrayList<ResponseStocksBodyItem>>,
                        response: Response<ArrayList<ResponseStocksBodyItem>>
                    ) {
                        var updatedList = response.body()
                        if (updatedList != null) {
                            stocksList.addAll(updatedList)
                        }
                    }

                    override fun onFailure(
                        call: Call<ArrayList<ResponseStocksBodyItem>>,
                        t: Throwable
                    ) {
                        Log.i("error msg=> ", "" + t)
                    }

                })
            } catch (e: Exception) {
                Log.i("Exception: ", "" + e.message)
            }
        }
    }

}