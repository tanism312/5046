package com.example.assignemt1

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Call
import androidx.compose.material.icons.rounded.Email
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

val CustomFont = Color(0xDA494848)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Profile(navController: NavHostController) {
    var text by remember { mutableStateOf(TextFieldValue("")) }
    Image(
        painter = painterResource(id = R.drawable.login),
        contentDescription = "Background Image",
        contentScale = ContentScale.Crop,
        modifier = Modifier.fillMaxWidth())
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(26.dp))
        Image(
            painter = painterResource(id = R.drawable.butch),
            contentDescription = "Profile Picture",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(120.dp)
                .height(120.dp)
                .clip(CircleShape),
        )
        Text(text = "Mr.Butch", modifier = Modifier
            .align(Alignment.CenterHorizontally)
            .padding(top = 15.dp),color = CustomWhite, fontSize = 20.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(16.dp))
        Surface(
            modifier = Modifier
                .height(110.dp)
                .width(330.dp),
            color = CustomBlue,
            shadowElevation = 3.dp,
            shape = RoundedCornerShape(5.dp)
        ) {
            Row (horizontalArrangement  =  Arrangement.SpaceEvenly
            ){
                Column() {
                    Text(text = "Posts", modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(top = 25.dp), color = CustomWhite, fontSize = 20.sp, fontWeight = FontWeight.Bold)
                    Text(text = "327", modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(top = 5.dp), color = CustomWhite, fontSize = 20.sp, fontWeight = FontWeight.Bold)
                }

                Column() {
                    Text(text = "Followers", modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(top = 25.dp), color = CustomWhite, fontSize = 20.sp, fontWeight = FontWeight.Bold)
                    Text(text = "184", modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(top = 5.dp), color = CustomWhite, fontSize = 20.sp, fontWeight = FontWeight.Bold)

                }
                Column() {
                    Text(text = "Goals", modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(top = 25.dp), color = CustomWhite, fontSize = 20.sp, fontWeight = FontWeight.Bold)
                    Text(text = "17", modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(top = 5.dp), color = CustomWhite, fontSize = 20.sp, fontWeight = FontWeight.Bold)

                }
            }
        }
        Spacer(modifier = Modifier.height(26.dp))
        Column {
            Row (){
                Icon(
                    Icons.Rounded.Email,
                    contentDescription = "Back",
                    modifier = Modifier
                        .padding(10.dp)
                        .size(35.dp),
                    tint = CustomWhite
                )
                Text(text = "hirn8930@gmail.com", modifier = Modifier.align(Alignment.CenterVertically).padding(start = 5.dp), color = Color.DarkGray, fontSize = 15.sp, fontWeight = FontWeight.Bold)
            }
            Row {
                Icon(
                    Icons.Rounded.Call,
                    contentDescription = "Back",
                    modifier = Modifier
                        .padding(10.dp)
                        .size(35.dp),
                    tint = CustomWhite
                )
                Text(text = "0487983923", modifier = Modifier.align(Alignment.CenterVertically).padding(start = 5.dp), color = Color.DarkGray, fontSize = 15.sp, fontWeight = FontWeight.Bold)
            }
            Row {
                Icon(
                    Icons.Rounded.Home,
                    contentDescription = "Back",
                    modifier = Modifier
                        .padding(10.dp)
                        .size(35.dp),
                    tint = CustomWhite
                )
                Text(text = "394/32 Blackburn St", modifier = Modifier.align(Alignment.CenterVertically).padding(start = 5.dp), color = Color.DarkGray, fontSize = 15.sp, fontWeight = FontWeight.Bold)
            }
            Row {
                Icon(
                    Icons.Rounded.Person,
                    contentDescription = "Back",
                    modifier = Modifier
                        .padding(10.dp)
                        .size(35.dp),
                    tint = CustomWhite
                )
                Text(text = "Other info......", modifier = Modifier.align(Alignment.CenterVertically).padding(start = 5.dp), color = Color.DarkGray, fontSize = 15.sp, fontWeight = FontWeight.Bold)
            }
        }


    }
}

