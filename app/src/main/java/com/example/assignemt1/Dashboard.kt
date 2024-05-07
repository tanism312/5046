@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.assignemt1

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import java.time.LocalDate




@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Dashboard(navHostController: NavHostController?) {
    val calorieRecordViewModel: CalorieRecordViewModel = viewModel()
    var selectedDate:Long by remember { mutableStateOf(Instant.now().toEpochMilli()) }

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
                .padding(30.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            DateForStats(selectedDate){ date ->
                selectedDate = date
            }
//            CalorieBudget()

            selectedDate?.let { FoodTrackers(calorieRecordViewModel, it) }

//    FoodInput(tracker = Trackers[0], RetrofitViewModel(), calorieRecordViewModel)


            }
        }
    }


@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DateForStats(selectedDate: Long?, onDateSelected:(Long) -> Unit){
    //variables to move out
    val calendar = Calendar.getInstance()
    calendar.set(2024, 0, 1) // month (0) is January
    val datePickerState = rememberDatePickerState(
        initialSelectedDateMillis = Instant.now().toEpochMilli()
    )
    var showDatePicker by remember {
        mutableStateOf(false)
    }
//    var selectedDate by remember {
//        mutableStateOf(calendar.timeInMillis)
//    }


    Surface (shape = RoundedCornerShape(6.dp), color = CustomBlue){
        Row (modifier = Modifier.fillMaxWidth(0.9f)
            .clickable { /* to a previous day */ }){
            Column (modifier = Modifier.weight(2f),){
                Icon(
                    Icons.Rounded.KeyboardArrowLeft,
                    contentDescription = "Back",
                    modifier = Modifier
                        .align(Alignment.Start)
                        .padding(10.dp),
                    tint = CustomWhite,

                )
            }
            Column(modifier = Modifier.weight(6f).clickable { showDatePicker = true }){
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
                    Text(text = "Today", modifier = Modifier.align(Alignment.CenterVertically),color = CustomWhite, fontSize = 20.sp, fontWeight = FontWeight.Bold)
                }
            }
            Column (modifier = Modifier.weight(2f).clickable { /* to a later day */}){
                Icon(
                    Icons.Rounded.KeyboardArrowRight,
                    contentDescription = "Back",
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
                    showDatePicker = false
                    //selectedDateMillis!! null safety because type declared as Long?
                    onDateSelected(datePickerState.selectedDateMillis!!)
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
            DatePicker(
                state = datePickerState
            )
        }

    }
}

@Composable
fun CalorieBudget(){
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
                Text("Calorie Budget", modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(8.dp), color = CustomWhite, fontSize = 25.sp, fontWeight = FontWeight.Bold)
                Text("2415", modifier = Modifier.align(Alignment.CenterHorizontally), fontWeight = FontWeight.Bold, color = CustomWhite, fontSize = 20.sp, fontStyle = FontStyle.Italic)
            }
        }
    }
}

@Composable
fun SingleTracker(tracker: Tracker,calorieRecordViewModel: CalorieRecordViewModel, selectedDate: Long) {
    var showDialog by remember { mutableStateOf(false) }

    Surface(
        Modifier
            .padding(5.dp)
            .fillMaxWidth()
            .height(60.dp)
            .clickable { showDialog = true }
            ,
        color = Customwhit,
        shape = RoundedCornerShape(5.dp)

        )
    {
        Row (horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically){
            Image(modifier = Modifier
                .padding(7.dp)
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
                Text(text = tracker.progress.toString())
            }
            Row {
                if (tracker.name=="Water")
                {

                }
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add Icon",
                    tint = Color.Black,
                    modifier = Modifier
                        .size(48.dp)
                        .padding(end = 10.dp)
                )
            }

        }
    }

    if (showDialog){
        Dialog(onDismissRequest = { showDialog = false }) {
            FoodInput(tracker, RetrofitViewModel(),calorieRecordViewModel,selectedDate,
                onComplete = { showDialog = false })
        }
    }
}

@Composable
fun FoodTrackers(calorieRecordViewModel: CalorieRecordViewModel,selectedDate: Long){
    LazyColumn(horizontalAlignment = Alignment.CenterHorizontally){
        items(Trackers){
            SingleTracker(tracker = it,calorieRecordViewModel,selectedDate)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun preview() {

}
//@Composable
//fun DashboardButton(bottomName:String, @DrawableRes id: Int,/* onClickAction: Unit*/){
//    Surface (modifier = Modifier
//        .padding(20.dp)
//        .height(120.dp)
//        .width(110.dp),
//        color = Customwhit,
//        shape = RoundedCornerShape(5.dp),
//        shadowElevation = 20.dp,
//        onClick = { /* onClickAction*/ }
//    ){
//        Column {
//            Image( painter = painterResource(id = id),
//                contentDescription = "Button Image",
//                modifier = Modifier
//                    .height(90.dp)
//                    .align(Alignment.CenterHorizontally)
//                    .padding(10.dp))
//            Text(text = bottomName,
//                color = CustomBlack,
//                fontSize = 18.sp,
//                fontWeight = FontWeight.Bold,
//                modifier = Modifier.align(Alignment.CenterHorizontally))
//        }
//    }
//}
//@Composable
//fun MenuOfDBButtons(){
//    Column (
//        ){
//        Row(
//            horizontalArrangement = Arrangement.Center,
//            modifier = Modifier.fillMaxWidth(),
//        ){
//            DashboardButton(bottomName = "Steps", id = R.drawable.footprint)
//            DashboardButton(bottomName = "Breakfast", id = R.drawable.breakfast)
//        }
//        Row(
//            horizontalArrangement = Arrangement.Center,
//            modifier = Modifier.fillMaxWidth(),
//        ){
//            DashboardButton(bottomName = "Water", id = R.drawable.water)
//            DashboardButton(bottomName = "Lunch", id = R.drawable.lunch)
//        }
//        Row(
//            horizontalArrangement = Arrangement.Center,
//            modifier = Modifier.fillMaxWidth(),
//        ) {
//            DashboardButton(bottomName = "Fruit", id = R.drawable.fruits)
//            DashboardButton(bottomName = "Dinner", id = R.drawable.dinner)
//        }
//    }
//}




