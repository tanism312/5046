package com.example.assignemt1

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.DateRange
import androidx.compose.material.icons.rounded.KeyboardArrowLeft
import androidx.compose.material.icons.rounded.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

val CustomWhite = Color(0xDAEFF8EF)
val CustomBlue = Color(0xFF6C90DA)
val Customwhit = Color(0xFFF5EAAD)
val CustomBlack = Color(0xFF1B1B1B)
val CustomRed = Color(0xFFDB6969)
val CustomGray = Color(0xFFB1EC75)

@Composable
fun Dashboard(navController: NavHostController) {

    Box(
        contentAlignment = Alignment.TopCenter, // Aligns the content (the Row) at the top center
        modifier = Modifier.fillMaxSize() // Makes the Box fill the entire screen
    ) {
        Image(
            painter = painterResource(id = R.drawable.login),
            contentDescription = "Background Image",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxWidth())

        Column {
            Spacer(modifier = Modifier.height(30.dp))
            Surface (shape = RoundedCornerShape(6.dp), color = CustomBlue){
                Row (modifier = Modifier.fillMaxWidth(0.9f)){
                    Column (modifier = Modifier.weight(2f)){
                        Icon(
                            Icons.Rounded.KeyboardArrowLeft,
                            contentDescription = "Back",
                            modifier = Modifier
                                .align(Alignment.Start)
                                .padding(10.dp),
                            tint = CustomWhite
                        )
                    }
                    Column(modifier = Modifier.weight(6f)){
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
                    Column (modifier = Modifier.weight(2f)){
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

            Surface (modifier = Modifier
                .padding(15.dp)
                .align(Alignment.CenterHorizontally), color = Color.Transparent){
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

            Row (modifier = Modifier.fillMaxWidth(0.9f),
                horizontalArrangement = Arrangement.SpaceBetween){
                Surface (modifier = Modifier
                    .padding(start = 30.dp, top = 20.dp, end = 30.dp, bottom = 20.dp)
                    .height(120.dp)
                    .width(110.dp),color = Customwhit, shape = RoundedCornerShape(5.dp), shadowElevation = 20.dp){
                    Column {
                        Image( painter = painterResource(id = R.drawable.footprint),
                            contentDescription = "Button Image",
                            modifier = Modifier
                                .height(80.dp)
                                .align(Alignment.CenterHorizontally)
                                .padding(5.dp))
                        Text(text = "Steps", color = CustomBlack, fontSize = 18.sp, fontWeight = FontWeight.Bold, modifier = Modifier.align(Alignment.CenterHorizontally))
                    }
                }

                Surface (modifier = Modifier
                    .padding(start = 30.dp, top = 20.dp, end = 30.dp, bottom = 20.dp)
                    .height(120.dp)
                    .width(110.dp),color = Customwhit, shape = RoundedCornerShape(5.dp), shadowElevation = 20.dp){
                    Column {
                        Image( painter = painterResource(id = R.drawable.breakfast),
                            contentDescription = "Button Image",
                            modifier = Modifier
                                .height(80.dp)
                                .align(Alignment.CenterHorizontally)
                                .padding(5.dp))
                        Text(text = "Breakfast", color = CustomBlack, fontSize = 18.sp, fontWeight = FontWeight.Bold, modifier = Modifier.align(Alignment.CenterHorizontally))
                    }
                }
            }

            Row (modifier = Modifier.fillMaxWidth(0.9f),
                horizontalArrangement = Arrangement.SpaceBetween){
                Surface (modifier = Modifier
                    .padding(start = 30.dp, top = 20.dp, end = 30.dp, bottom = 20.dp)
                    .height(120.dp)
                    .width(110.dp),color = Customwhit, shape = RoundedCornerShape(5.dp), shadowElevation = 20.dp){
                    Column {
                        Image( painter = painterResource(id = R.drawable.water),
                            contentDescription = "Button Image",
                            modifier = Modifier
                                .height(80.dp)
                                .align(Alignment.CenterHorizontally)
                                .padding(5.dp))
                        Text(text = "Water", color = CustomBlack, fontSize = 18.sp, fontWeight = FontWeight.Bold, modifier = Modifier.align(Alignment.CenterHorizontally))
                    }
                }

                Surface (modifier = Modifier
                    .padding(start = 30.dp, top = 20.dp, end = 30.dp, bottom = 20.dp)
                    .height(120.dp)
                    .width(110.dp),color = Customwhit, shape = RoundedCornerShape(5.dp), shadowElevation = 20.dp){
                    Column {
                        Image( painter = painterResource(id = R.drawable.lunch),
                            contentDescription = "Button Image",
                            modifier = Modifier
                                .height(80.dp)
                                .align(Alignment.CenterHorizontally)
                                .padding(5.dp))
                        Text(text = "Lunch", color = CustomBlack, fontSize = 18.sp, fontWeight = FontWeight.Bold, modifier = Modifier.align(Alignment.CenterHorizontally))
                    }
                }
            }

            Row (modifier = Modifier.fillMaxWidth(0.9f),
                horizontalArrangement = Arrangement.SpaceBetween){
                Surface (modifier = Modifier
                    .padding(start = 30.dp, top = 20.dp, end = 30.dp, bottom = 20.dp)
                    .height(120.dp)
                    .width(110.dp),color = Customwhit, shape = RoundedCornerShape(5.dp), shadowElevation = 20.dp){
                    Column {
                        Image( painter = painterResource(id = R.drawable.fruits),
                            contentDescription = "Button Image",
                            modifier = Modifier
                                .height(80.dp)
                                .align(Alignment.CenterHorizontally)
                                .padding(5.dp))
                        Text(text = "Fruits", color = CustomBlack, fontSize = 18.sp, fontWeight = FontWeight.Bold, modifier = Modifier.align(Alignment.CenterHorizontally))
                    }
                }

                Surface (modifier = Modifier
                    .padding(start = 30.dp, top = 20.dp, end = 30.dp, bottom = 20.dp)
                    .height(120.dp)
                    .width(110.dp),color = Customwhit, shape = RoundedCornerShape(5.dp), shadowElevation = 20.dp){
                    Column {
                        Image( painter = painterResource(id = R.drawable.dinner),
                            contentDescription = "Button Image",
                            modifier = Modifier
                                .height(80.dp)
                                .align(Alignment.CenterHorizontally)
                                .padding(5.dp))
                        Text(text = "Dinner", color = CustomBlack, fontSize = 18.sp, fontWeight = FontWeight.Bold, modifier = Modifier.align(Alignment.CenterHorizontally))
                    }
                }
            }
        }
    }
}


