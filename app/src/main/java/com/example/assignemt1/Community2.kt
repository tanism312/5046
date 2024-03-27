package com.example.assignemt1

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AccountBox
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.InspectableModifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

val ColorTextField = Color(0x4F414141)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Community(navController: NavHostController) {
    Image(
        painter = painterResource(id = R.drawable.login),
        contentDescription = "Background Image",
        contentScale = ContentScale.Crop,
        modifier = Modifier.fillMaxWidth()
    )

    Column () {
        Surface (modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .padding(10.dp), color = CustomBlue, shape = RoundedCornerShape(5.dp)
        ){
            Column {
                Text(text = "Healthy Diet Community", modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 5.dp),color = CustomWhite, fontSize = 20.sp, fontWeight = FontWeight.Bold)
                Row (modifier = Modifier.background(Color.Transparent)){
                    Icon(
                        Icons.Rounded.AccountBox,
                        contentDescription = "Back",
                        modifier = Modifier
                            .align(Alignment.CenterVertically)
                            .padding(top = 10.dp, start = 80.dp, end = 2.dp),
                        tint = CustomWhite
                    )
                    var text by remember { mutableStateOf("") }

                    Surface (modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .padding(top = 8.dp) ,color = Color.Transparent){
                        TextField(
                            value = text,
                            onValueChange = { text = it },
                            label = { Text(text = "Share your healthy diet journey here", fontSize = 9.sp, color = CustomWhite) },
                            modifier = Modifier
                                .align(Alignment.CenterVertically)
                                .height(30.dp)
                                .width(190.dp),
                            shape = RoundedCornerShape(5.dp),
                            colors = TextFieldDefaults.textFieldColors(
                                containerColor = ColorTextField
                            )
                        )
                    }
                }
            }
        }

        Surface (modifier = Modifier
            .fillMaxWidth()
            .padding(start = 10.dp, end = 10.dp, bottom = 5.dp)
            .border(1.dp, CustomWhite),
            color = ColorTextField,
            shape = RoundedCornerShape(3.dp)
        ){
            Column {
                Row {
                    Image(
                        painter = painterResource(id = com.example.assignemt1.R.drawable.pepe),
                        contentDescription = null,
                        modifier = Modifier
                            .size(40.dp)
                            .clip(CircleShape)
                            .padding(top = 5.dp)
                    )
                    Text(text = "Pepe", modifier = Modifier.padding(start = 10.dp,top = 8.dp),
                        color = Color.Black, fontWeight = FontWeight.Bold)
                }
                Row {
                    Column {
                        Text(text = "Here is my secret recipe for brekkie~ It contains some grilled chicken, onion, tomato and avocado! Hope you guys like it!", modifier = Modifier.padding(start = 50.dp))
                        Image(
                            painter = painterResource(id = com.example.assignemt1.R.drawable.healthyfood),
                            contentDescription = null,
                            modifier = Modifier
                                .size(200.dp)
                                .clip(CircleShape)
                                .padding(start = 50.dp, top = 5.dp)
                        )
                    }
                }
                Row {
                    Spacer(Modifier.weight(0.9f))
                    Icon(
                        Icons.Rounded.Edit,
                        contentDescription = "Back",
                        modifier = Modifier
                            .align(Alignment.CenterVertically)
                            .padding(5.dp),
                        tint = CustomWhite
                    )
                    Icon(
                        Icons.Rounded.Favorite,
                        contentDescription = "Back",
                        modifier = Modifier
                            .align(Alignment.CenterVertically)
                            .padding(end = 5.dp),
                        tint = CustomWhite
                    )
                }
            }
        }

        Surface (
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, end = 10.dp, bottom = 5.dp)
                .border(1.dp, CustomWhite),
            color = ColorTextField,
            shape = RoundedCornerShape(3.dp)
        ){
            Column {
                Row {
                    Image(
                        painter = painterResource(id = com.example.assignemt1.R.drawable.tom),
                        contentDescription = null,
                        modifier = Modifier
                            .size(40.dp)
                            .clip(CircleShape)
                            .padding(top = 5.dp)
                    )
                    Text(text = "Tom", modifier = Modifier.padding(start = 10.dp,top = 8.dp),
                        color = Color.Black, fontWeight = FontWeight.Bold)
                }
                Row {
                    Text(text = "I started my weight loss journey about six months ago, motivated by a desire to improve my health and overall well-being.", modifier = Modifier.padding(start = 50.dp, end = 5.dp))
                }
                Row {
                    Spacer(Modifier.weight(0.9f))
                    Icon(
                        Icons.Rounded.Edit,
                        contentDescription = "Back",
                        modifier = Modifier
                            .align(Alignment.CenterVertically)
                            .padding(5.dp),
                        tint = CustomWhite
                    )
                    Icon(
                        Icons.Rounded.Favorite,
                        contentDescription = "Back",
                        modifier = Modifier
                            .align(Alignment.CenterVertically)
                            .padding(end = 5.dp),
                        tint = CustomWhite
                    )
                }
            }
        }

        Surface (
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, end = 10.dp, bottom = 5.dp)
                .border(1.dp, CustomWhite),
            color = ColorTextField,
            shape = RoundedCornerShape(3.dp)
        ){
            Column {
                Row {
                    Image(
                        painter = painterResource(id = com.example.assignemt1.R.drawable.jerry),
                        contentDescription = null,
                        modifier = Modifier
                            .size(40.dp)
                            .clip(CircleShape)
                            .padding(top = 5.dp)
                    )
                    Text(text = "Jerry", modifier = Modifier.padding(start = 10.dp,top = 8.dp),
                        color = Color.Black, fontWeight = FontWeight.Bold)
                }
                Row {
                    Text(text = "I've been part of this community for a while now, reading through your inspiring posts every day. Today, I decided it's time I reached out for some advice and support myself.", modifier = Modifier.padding(start = 50.dp, end = 5.dp))
                }
                Row {
                    Spacer(Modifier.weight(0.9f))
                    Icon(
                        Icons.Rounded.Edit,
                        contentDescription = "Back",
                        modifier = Modifier
                            .align(Alignment.CenterVertically)
                            .padding(5.dp),
                        tint = CustomWhite
                    )
                    Icon(
                        Icons.Rounded.Favorite,
                        contentDescription = "Back",
                        modifier = Modifier
                            .align(Alignment.CenterVertically)
                            .padding(end = 5.dp),
                        tint = CustomWhite
                    )
                }

            }

        }



    }
}

