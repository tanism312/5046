package com.example.assignemt1

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.core.text.isDigitsOnly
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.assignemt1.data.CalorieRecord
import com.example.assignemt1.data.Items
import com.example.assignemt1.data.Tracker
import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.magnifier
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.darkColors
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.time.Instant

@RequiresApi(Build.VERSION_CODES.O)
@ExperimentalMaterial3Api
@Composable
fun FoodInput(tracker: Tracker,
              viewModel: RetrofitViewModel,
              calorieRecordViewModel: CalorieRecordViewModel,
              startOfDay: Long,
              endOfDay: Long,
              onComplete:()->Unit
) {
    var food by remember { mutableStateOf("") }
    val itemsReturned by viewModel.retrofitResponse
    val calorieRecordsOfDateAndMealType by
    calorieRecordViewModel.getCalorieRecordsByDateAndMealType(startOfDay, endOfDay,tracker.name).observeAsState(emptyList())
    var serveSize by remember { mutableStateOf(0.0) }
    val list = itemsReturned.items
    var index by remember { mutableStateOf(0) }
    var selectedItem by remember { mutableStateOf<Items?>(null) }
    var showDialog by remember { mutableStateOf(false) }
//    var showError by remember { mutableStateOf(false) }
    var counter by remember { mutableStateOf(0) }
//
//    LaunchedEffect(itemsReturned){
//        if(list.isEmpty() /*&& counter !=0*/)
//        {
//            showError = true
//            counter++
//        }
//    }

    Column(
        Modifier
            .background(color = Color.White, RoundedCornerShape(10.dp))
            .padding(10.dp)
    ) {
        TextField(
            value = food,
            onValueChange = { food = it },
            label = { Text("What did you eat for ${tracker.name.lowercase()}?") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        Text(text = "Better to have the quantity of food.", color = Color.Gray)
        Text(text = "E.g. \"300g tomatoes and 0.5 kg brisket.\"",color = Color.Gray)

//        if (showError)
//        {
//            Text(text = "Invalid Input. Please try again.", color = Color.Red)
//        }

        Button(modifier = Modifier
            .align(Alignment.CenterHorizontally)
            .padding(10.dp),
            onClick = {
                viewModel.getResponse(food)
//                counter++
//                if(list.isEmpty() /*&& counter !=0*/)
//                {
//                    showError = true
//                    counter++
//                }
            }
        ) {
            Text("Submit")
        }

        LazyColumn{

            itemsIndexed(calorieRecordsOfDateAndMealType){
                    index,calorieRecord ->
                CalorieRecordItem(calorieRecord = calorieRecord,
                    onDelete = {calorieRecordViewModel.deleteCalorieRecord(calorieRecord)})
                if(index < calorieRecordsOfDateAndMealType.size - 1)
                {
                    Spacer(modifier = Modifier.height(10.dp))
                }
            }
        }
    }

    if (list.isNotEmpty()) {
//        showError = false
        selectedItem = list[index] // or however you choose an item from the list
        showDialog = true

    }

    var itemIndexConfirmed by remember { mutableStateOf(-1) }
    if (showDialog && selectedItem != null && itemIndexConfirmed != index) {
        ServeSizeDialog(

            item = selectedItem!!,
            onConfirm = { double ->
                serveSize = double
                calorieRecordViewModel.insertCalorieRecord(
                    CalorieRecord(
                        userInput = food,
                        calorie = selectedItem!!.calories * serveSize / 100,
                        ingredient = selectedItem!!.name,
                        mealType = tracker.name,
                        date = startOfDay
                    )
                )
            },
            onDismiss = {
                food = ""
                itemIndexConfirmed = index
                if(index<list.size-1){
                    selectedItem = list[index++]
                }
                else{onComplete()}
                showDialog = false
            }
        )
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ServeSizeDialog(onConfirm:(Double)->Unit, onDismiss:()->Unit, item:Items) {
    var tempServeSize by remember { mutableStateOf(item.serving_size_g)}
//                isDismissed = false
//                isServeSizeConfirmed = false
    LaunchedEffect(item){
        tempServeSize = item.serving_size_g
    }

    AlertDialog(
        onDismissRequest = { onDismiss()
//            showServeSizeDialog = false
//            isServeSizeConfirmed = false
//            isDismissed = true
        },
        confirmButton = {
            Button(onClick = {
                onConfirm(tempServeSize)
                onDismiss()
                tempServeSize = item.serving_size_g
//                serveSize = tempServeSize
////                isServeSizeConfirmed = true
//                showServeSizeDialog = false
            }) {
                Text(text = "Confirm")
            }
        },
        dismissButton = {
            Button(onClick = { onDismiss()
//                showServeSizeDialog = false
//                isServeSizeConfirmed = false
//                isDismissed = true
            }) {
                Text(text = "Dismiss")
            }
        },
        title = { Text(text = "Confirm serve size(g)")},

        text = {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = item.name, fontSize = 25.sp, modifier = Modifier.padding(bottom = 20.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth(0.9f)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center

                ){
                    Button(onClick = { tempServeSize -= 10 }) {
                        Text("-", fontSize = 20.sp)
                    }

                    TextField(
                      colors = TextFieldDefaults.textFieldColors(disabledTextColor = Color.Black),
                        modifier = Modifier.width(100.dp),
                        value = tempServeSize.toString(),
                        onValueChange = { newValue ->
                            if (newValue.isDigitsOnly()) {
                                tempServeSize = newValue.toDouble()
                            }
                        },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        enabled = false,
                    )

                    Button(onClick = { tempServeSize += 10 }) {
                        Text("+")
                    }
                }
            }

        },
        containerColor = Color.White
    )
}

@Composable
fun CalorieRecordItem(calorieRecord: CalorieRecord,onDelete :()->Unit) {
    Row(horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .background(Color.LightGray, RoundedCornerShape(10.dp))
            .padding(10.dp)
            .fillMaxWidth()
    )
    {
        calorieRecord.ingredient?.let { Text(text = it, modifier = Modifier.padding(5.dp,0.dp)) }
        calorieRecord.calorie?.let { Text(text = String.format("%.2f",it)+"Cal") }
        IconButton(onClick = {onDelete()} ) {
            Icon(Icons.Default.Delete,
                contentDescription = "Delete",
                Modifier
                    .background(Color.White, CircleShape)
                    .size(40.dp)
                    .padding(5.dp),
                tint = Color.Red
                )
        }
    }
}


