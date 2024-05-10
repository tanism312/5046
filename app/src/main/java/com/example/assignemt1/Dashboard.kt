@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)

package com.example.assignemt1

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.rounded.DateRange
import androidx.compose.material.icons.rounded.KeyboardArrowLeft
import androidx.compose.material.icons.rounded.KeyboardArrowRight
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.assignemt1.data.Tracker
import com.example.assignemt1.data.Trackers
import java.time.Instant
import java.util.Calendar
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.DatePickerState
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import java.time.LocalDate
import java.time.LocalTime
import java.time.ZoneId
import java.util.concurrent.TimeUnit


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Dashboard(navHostController: NavHostController?) {
    val calorieRecordViewModel: CalorieRecordViewModel = viewModel()
    var startOfDay:Long? by remember { mutableStateOf(null) }
    var endOfDay:Long? by remember { mutableStateOf(null) }

    Box(
        contentAlignment = Alignment.TopCenter, // Aligns the content (the Row) at the top center
        modifier = Modifier.fillMaxSize() // Makes the Box fill the entire screen
    ) {
        Image(
            painter = painterResource(id = R.drawable.login),
            contentDescription = "Background Image",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxWidth())

        Column(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Column( // Use Column for vertical arrangement
                modifier = Modifier
                    .fillMaxWidth()  // Make the column fill the entire screen
                    .padding(16.dp), // Add some padding around content
                horizontalAlignment = Alignment.CenterHorizontally, // Center content horizontally
                verticalArrangement = Arrangement.spacedBy(16.dp) // Add spacing between boxes
            ) { // Use a Column for vertical arrangement
                Text(
                    text = "Dashboard", // Your desired text
                    modifier = Modifier.padding(16.dp),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = CustomBlack
                )
            }

            DateForStats(){ (start,end) ->
                startOfDay = start
                endOfDay = end
            }
            var caloriesPerDay by remember {
                mutableStateOf(0.0)
            }
//            CalorieBudget(caloriesPerDay)
//            Spacer(modifier = Modifier.height(5.dp))

            startOfDay?.let { endOfDay?.let { it1 -> FoodTrackers(calorieRecordViewModel, it, it1,
                /*{cpd -> caloriesPerDay = cpd}*/) } }

        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
fun returnDatePair(date: LocalDate,
                   datePickerState: DatePickerState,
                   onDateSelected:(Pair<Long, Long>) -> Unit){
    val startOfDay = date.atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli()
    val endOfDay = date.atTime(LocalTime.MAX).atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()
    datePickerState.setSelection(startOfDay)
    onDateSelected(Pair(startOfDay,endOfDay))
}

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DateForStats(onDateSelected:(Pair<Long, Long>) -> Unit){
    val datePickerState = rememberDatePickerState(initialSelectedDateMillis = Instant.now().toEpochMilli())
    var date by remember { mutableStateOf(Instant.ofEpochMilli(datePickerState.selectedDateMillis!!).atZone(
        ZoneId.systemDefault()).toLocalDate()) }
    var showDatePicker by remember { mutableStateOf(false) }

    returnDatePair(date, datePickerState,onDateSelected)

    Surface (shape = RoundedCornerShape(6.dp), color = CustomBlue){
        Row (modifier = Modifier.fillMaxWidth(0.9f)){
            Column (modifier = Modifier
                .weight(2f)
                .clickable {
                    date = date.minusDays(1)
                    returnDatePair(date, datePickerState, onDateSelected)
                })
            {
                Icon(
                    Icons.Rounded.KeyboardArrowLeft,
                    contentDescription = "Back date by one day",
                    modifier = Modifier
                        .align(Alignment.Start)
                        .padding(10.dp),
                    tint = CustomWhite,

                )
            }
            Column(modifier = Modifier
                .weight(6f)
                .clickable { showDatePicker = true }){
                Row (modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(10.dp)){
                    Icon(
                        Icons.Rounded.DateRange,
                        contentDescription = "Back",
                        modifier = Modifier
                            .align(Alignment.CenterVertically)
                            .padding(end = 5.dp),
                        tint = CustomWhite
                    )
                    Text(text = date.toString(), modifier = Modifier.align(Alignment.CenterVertically),color = CustomWhite, fontSize = 20.sp, fontWeight = FontWeight.Bold)
                }
            }
            Column (modifier = Modifier
                .weight(2f)
                .clickable {
                    date = date.plusDays(1)
                    returnDatePair(date, datePickerState, onDateSelected)
                }){
                Icon(
                    Icons.Rounded.KeyboardArrowRight,
                    contentDescription = "Forward Date by one day",
                    modifier = Modifier
                        .align(Alignment.End)
                        .padding(10.dp),
                    tint = CustomWhite
                )
            }
        }
    }

    if (showDatePicker) {
        DatePickerDialog(
            onDismissRequest = {
                showDatePicker = false
            },
            confirmButton = {
                TextButton(onClick = {
                    date = Instant.ofEpochMilli(datePickerState.selectedDateMillis!!).atZone(ZoneId.systemDefault()).toLocalDate()
                    showDatePicker = false
                    returnDatePair(date,datePickerState,onDateSelected)
                }) {
                    Text(text = "OK")
                }
            },
            dismissButton = {
                TextButton(onClick = {
                    showDatePicker = false
                }) {
                    Text(text = "Cancel")
                }
            }
        ) //end of dialog
        { //still column scope
            DatePicker(state = datePickerState)
        }
    }
}

@Composable
fun CalorieBudget( caloriesPerDay: Double ){
    Surface (modifier = Modifier
        .padding(15.dp),
        color = Color.Transparent){
        Row {
            Image( painter = painterResource(id = R.drawable.calories),
                contentDescription = "Button Image",
                modifier = Modifier
                    .height(80.dp)
                    .padding(5.dp))
            Column {
                Text("Calorie Consumption", modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(8.dp), color = CustomWhite, fontSize = 25.sp, fontWeight = FontWeight.Bold)

                Text(String.format("%.2f",caloriesPerDay)+"Cal",
                    modifier = Modifier.align(Alignment.CenterHorizontally), fontWeight = FontWeight.Bold, color = CustomWhite, fontSize = 20.sp, fontStyle = FontStyle.Italic)
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun SingleTracker(tracker: Tracker,calorieRecordViewModel: CalorieRecordViewModel, startOfDay:Long,
                  endOfDay:Long, returnCalories:(Double)->Unit) {
    var showDialog by remember { mutableStateOf(false) }

    val calorieRecordsOfDateAndMealType by
    calorieRecordViewModel.getCalorieRecordsByDateAndMealType(startOfDay, endOfDay,tracker.name).observeAsState(emptyList())

    var caloriesPerMeal by remember { mutableStateOf(0.0) }

    LaunchedEffect(calorieRecordsOfDateAndMealType) {
        caloriesPerMeal = calorieRecordsOfDateAndMealType.sumByDouble { it.calorie!! }
        returnCalories(caloriesPerMeal)
    }

    Surface(
        Modifier
            .padding(10.dp)
            .fillMaxWidth()
            .height(80.dp)
            .clickable { showDialog = true }
            ,
        color = Customwhit,
        shape = RoundedCornerShape(5.dp)

        )
    {
        Row (horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(10.dp, 10.dp, 20.dp,10.dp)){
            Image(modifier = Modifier
                .fillMaxHeight(),
                painter = painterResource(id = tracker.image),
                contentDescription = tracker.description)
            Column(
                Modifier
                    .padding(top = 5.dp)
                    .align(Alignment.CenterVertically),
                horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                Text(text = tracker.name,
                    color = CustomBlack,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold)
                Text(text = String.format("%.2f",caloriesPerMeal)+"Cal")
            }

            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "Add Icon",
                tint = Color.Black,
                modifier = Modifier
                    .size(40.dp)
                    .background(color = Color(0x4D000000), shape = CircleShape)
            )

        }
    }

    if (showDialog){
        Dialog(onDismissRequest = { showDialog = false }) {
            FoodInput(tracker, RetrofitViewModel(),calorieRecordViewModel, startOfDay, endOfDay,
                onComplete = { showDialog = false })
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun FoodTrackers(calorieRecordViewModel: CalorieRecordViewModel,startOfDay: Long,endOfDay: Long){
    var caloriesPerDay by remember {
        mutableStateOf(0.0)
    }

    LaunchedEffect(startOfDay)
    {
        caloriesPerDay = 0.0
    }

    CalorieBudget(caloriesPerDay)

    LazyColumn(horizontalAlignment = Alignment.CenterHorizontally){

        items(Trackers){
            SingleTracker(tracker = it,calorieRecordViewModel,startOfDay, endOfDay){
                caloriesPerDay += it
            }
        }
    }
//    returnCalories(caloriesPerDay)
}
