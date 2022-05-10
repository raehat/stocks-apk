package com.example.internship2

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.internship2.DataModel.ResponseStocksBodyItem


class MainActivity : ComponentActivity() {

    val mainViewModel: MainActivityViewModel by viewModels()
    var i: Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp(lst = mainViewModel.stocksList, this)
        }
    }
    @Composable
    fun MyApp(lst: SnapshotStateList<ResponseStocksBodyItem>, context: Context) {
        MaterialTheme {
            StocksList(lst, context)
        }
    }

    @Composable
    fun StocksList(lst: SnapshotStateList<ResponseStocksBodyItem>, context: Context) {
        val listState = rememberLazyListState()
        LazyColumn(state = listState) {
            items(lst) { item : ResponseStocksBodyItem ->
                StockCard(item = item)
            }
        }
        if (listState.isScrolledToTheEnd()) {
            if (i<3) {
                mainViewModel.doNetworkCalls(i++)
                Toast.makeText(context, "" + i, Toast.LENGTH_SHORT).show()
            }
        }
    }

    @Composable
    private fun CustomLinearProgressBar(){
        Column(modifier = Modifier.fillMaxWidth()) {
            LinearProgressIndicator(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(15.dp),
                backgroundColor = Color.LightGray,
                color = Color.Red //progress color
            )
        }
    }

    fun LazyListState.isScrolledToTheEnd() = layoutInfo.visibleItemsInfo.lastOrNull()?.index == layoutInfo.totalItemsCount - 1

    @Composable
    fun StockCard(item: ResponseStocksBodyItem) {
        Card(
            elevation = 4.dp,
            modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth()
                .wrapContentHeight()
                .background(color = Color.White)
        ) {
            Box(modifier = Modifier.padding(16.dp)) {
                Column() {
                    Text(text = "" + item.name,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold)
                    Row(modifier = Modifier
                        .fillMaxWidth()
                        .padding(0.dp, 10.dp, 0.dp, 0.dp), horizontalArrangement = Arrangement.SpaceBetween) {
                        Column() {
                            Text("" + item.sellingPrice, fontWeight = FontWeight.Bold)
                            Text("Selling Price", fontWeight = FontWeight.Light, fontSize = 12.sp)
                        }
                        Column() {
                            Text("" + item.purchasePrice, fontWeight = FontWeight.Bold)
                            Text("Stock Value", fontWeight = FontWeight.Light, fontSize = 12.sp)
                        }
                        Column() {
                            Text("" + item.availableCount + " " + item.priceUnit, fontWeight = FontWeight.Bold)
                            Text("Remaining Stock", fontWeight = FontWeight.Light, fontSize = 12.sp)
                        }
                    }
                }
            }
        }
    }
}