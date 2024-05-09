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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
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
    val calorieRecords by calorieRecordViewModel.allCalorieRecords.observeAsState(emptyList())
    val calorieRecordsOfDateAndMealType by
    calorieRecordViewModel.getCalorieRecordsByDateAndMealType(startOfDay, endOfDay,tracker.name).observeAsState(emptyList())
    var serveSize by remember { mutableStateOf(0.0) }
    val list = itemsReturned.items
    var index by remember { mutableStateOf(0) }

    // Add these two lines
    var selectedItem by remember { mutableStateOf<Items?>(null) }
    var showDialog by remember { mutableStateOf(false) }

    Column(Modifier.background(color = Color.Gray)) {
        TextField(
            value = food,
            onValueChange = { food = it },
            label = { Text("What did you eat for ${tracker.name.lowercase()}?") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        Text(text = "Better to have the quantity of food.")
        Text(text = "E.g. \"300g tomatoes and 0.5 kg brisket.\"")

        Button(onClick = {
                viewModel.getResponse(food)
        }) {
            Text("Submit")
        }

        LazyColumn{
            itemsIndexed(calorieRecordsOfDateAndMealType){
                    index,calorieRecord ->
                CalorieRecordItem(calorieRecord = calorieRecord,
                    onDelete = {calorieRecordViewModel.deleteCalorieRecord(calorieRecord)})
                Divider(color = Color.Magenta, thickness = 5.dp)
            }
        }
    }


    if (list.isNotEmpty()) {
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
                        date = Instant.now().toEpochMilli()
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
        title = { Text(text = "Confirm Serve Size(g) of ${item.name}")},
        text = {
//            Text(text = "")
            Row(
                modifier = Modifier.fillMaxWidth(0.9f)
            ){
                Button(onClick = { tempServeSize -= 10 }) {
                    Text("-")
                }

                TextField(
                    modifier = Modifier.width(100.dp),
                    value = tempServeSize.toString(),
                    onValueChange = { newValue ->
                        if (newValue.isDigitsOnly()) {
                            tempServeSize = newValue.toDouble()
                        }
                    },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    enabled = false
                )

                Button(onClick = { tempServeSize += 10 }) {
                    Text("+")
                }
            }
        },
        containerColor = Color.White
    )
}

@Composable
fun CalorieRecordItem(calorieRecord: CalorieRecord,onDelete :()->Unit) {
    Row{
        Text(text = "(${calorieRecord.userInput})")
        calorieRecord.ingredient?.let { Text(text = it) }
        calorieRecord.calorie?.let { Text(text = it.toString()) }

        IconButton(onClick = onDelete) {
            Icon(Icons.Default.Delete, contentDescription = "Delete")
        }
    }
}


