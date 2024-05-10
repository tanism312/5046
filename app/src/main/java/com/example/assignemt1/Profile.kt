package com.example.assignemt1

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.SnackbarDuration
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController


val CustomFont = Color(0xDA494848)

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable

fun Profile(navController: NavHostController) {


    var isEditing by remember { mutableStateOf(false) }
    var showSnackbar by remember { mutableStateOf(false) }
    val scaffoldState = rememberScaffoldState()


        var isEditingPerson by remember { mutableStateOf(false) }
        var isEditingGender by remember { mutableStateOf(false) }
        var isEditingHome by remember { mutableStateOf(false) }
        var isEditingWeight by remember { mutableStateOf(false) }
        var isEditingAge by remember { mutableStateOf(false) }


        var person by remember { mutableStateOf("Mr. Butch") }
        var gender by remember { mutableStateOf("Male") }
        var weight by remember { mutableStateOf("73 Kg") }
        var age by remember { mutableStateOf("29") }

        var tempPerson by remember { mutableStateOf(person) }
        var tempGender by remember { mutableStateOf(gender) }
        var tempWeight by remember { mutableStateOf(weight) }
        var tempAge by remember { mutableStateOf(age) }

        val genderIcon = painterResource(id = R.drawable.gender)
        val weightIcon = painterResource(id = R.drawable.weight)
        val ageIcon = painterResource(id = R.drawable.age)

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
        Image(
            painter = painterResource(id = R.drawable.followery),
            contentDescription = "Profile Picture",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(120.dp)
                .height(60.dp)
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
                    Text(text = "Daily Tip", modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(top = 25.dp), color = CustomWhite, fontSize = 20.sp, fontWeight = FontWeight.Bold)
                    Text(text = "3", modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(top = 5.dp)
                        .clickable { navController.navigate(Routes.Details.value) }, color = CustomWhite, fontSize = 20.sp, fontWeight = FontWeight.Bold)
                }

                Column() {
                    Text(text = "Followers", modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(top = 25.dp), color = CustomWhite, fontSize = 20.sp, fontWeight = FontWeight.Bold)
                    Text(text = "16", modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(top = 5.dp)
                        .clickable { navController.navigate(Routes.FollowersList.value) }, color = CustomWhite, fontSize = 20.sp, fontWeight = FontWeight.Bold)

                }
                Column() {
                    Text(text = "Target", modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(top = 25.dp), color = CustomWhite, fontSize = 20.sp, fontWeight = FontWeight.Bold)
                    Text(text = "17", modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(top = 5.dp)
                        .clickable { navController.navigate(Routes.Goals.value) }, color = CustomWhite, fontSize = 20.sp, fontWeight = FontWeight.Bold)

                }
            }
        }
        Spacer(modifier = Modifier.height(26.dp))


        Column {
            Row (horizontalArrangement  =  Arrangement.SpaceEvenly) {
                    Icon(
                        Icons.Rounded.Person,
                        contentDescription = "Back",
                        modifier = Modifier
                            .padding(10.dp)
                            .size(35.dp),
                        tint = CustomWhite
                    )
                if (!isEditingPerson) {
                    Text(text = person,
                        modifier = Modifier
                            .align(Alignment.CenterVertically)
                            .padding(start = 5.dp),
                        color = Color.DarkGray,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold)
                }
                else {
                    TextField(
                        value = tempPerson,
                        onValueChange = { tempPerson = it },
                        modifier = Modifier
                            .align(Alignment.CenterVertically)
                            .padding(start = 5.dp),
                        textStyle = TextStyle(color = Color.DarkGray, fontSize = 20.sp, fontWeight = FontWeight.Bold)
                    )
                }

                Icon(
                    Icons.Rounded.Edit,
                    contentDescription = "Edit Person",
                    modifier = Modifier
                        .padding(15.dp)
                        .size(25.dp)
                        .clickable { isEditingPerson = !isEditingPerson },
                    tint = CustomWhite
                )
            }

            }

        Spacer(modifier = Modifier.height(15.dp))
            Row(horizontalArrangement  =  Arrangement.SpaceEvenly) {
                Icon(painter = genderIcon,
                    contentDescription = "Gender",
                    modifier = Modifier
                        .padding(10.dp)
                        .size(35.dp),
                    tint = CustomWhite
                )

                if (!isEditingGender) {
                    Text(text = gender,
                        modifier = Modifier
                            .align(Alignment.CenterVertically)
                            .padding(start = 5.dp),
                        color = Color.DarkGray,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold)
                } else {
                    TextField(
                        value = tempGender,
                        onValueChange = { tempGender = it },
                        modifier = Modifier
                            .align(Alignment.CenterVertically)
                            .padding(start = 5.dp),
                        textStyle = TextStyle(color = Color.DarkGray, fontSize = 20.sp, fontWeight = FontWeight.Bold)
                    )
                }
                Spacer(modifier = Modifier.width(45.dp))
                Icon(
                    Icons.Rounded.Edit,
                    contentDescription = "Edit Gender",
                    modifier = Modifier
                        .padding(10.dp)
                        .size(25.dp)
                        .clickable { isEditingGender = !isEditingGender },
                    tint = CustomWhite
                )
            }
        Spacer(modifier = Modifier.height(15.dp))
            Row(horizontalArrangement  =  Arrangement.SpaceEvenly) {
                Icon(
                    painter = ageIcon,
                    contentDescription = "Edit Age",
                    modifier = Modifier
                        .padding(10.dp)
                        .size(35.dp),
                    tint = CustomWhite
                )

                if (!isEditingAge) {
                    Text(text = age,
                        modifier = Modifier
                            .align(Alignment.CenterVertically)
                            .padding(start = 5.dp),
                        color = Color.DarkGray,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold)
                }
                else {
                    TextField(
                        value = tempAge,
                        onValueChange = { tempAge = it },
                        modifier = Modifier
                            .align(Alignment.CenterVertically)
                            .padding(start = 5.dp),
                        textStyle = TextStyle(color = Color.DarkGray, fontSize = 20.sp, fontWeight = FontWeight.Bold)
                    )
                }
                Spacer(modifier = Modifier.width(65.dp))
                Icon(
                    Icons.Rounded.Edit,
                    contentDescription = "Edit Age",
                    modifier = Modifier
                        .padding(10.dp) // Add spacing after the number
                        .size(25.dp)
                        .clickable { isEditingAge = !isEditing },
                    tint = CustomWhite
                )
            }
        Spacer(modifier = Modifier.height(15.dp))
        Row(horizontalArrangement  =  Arrangement.SpaceEvenly) {
            Icon(
                painter = weightIcon,
                contentDescription = "Back",
                modifier = Modifier
                    .padding(10.dp)
                    .size(35.dp),
                tint = CustomWhite
            )

            if (!isEditingWeight) {
                Text(text = weight,
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .padding(start = 5.dp),
                    color = Color.DarkGray,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold)
            }
            else {
                TextField(
                    value = tempWeight,
                    onValueChange = { tempWeight = it },
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .padding(start = 5.dp),
                    textStyle = TextStyle(color = Color.DarkGray, fontSize = 20.sp, fontWeight = FontWeight.Bold)
                )
            }
            Spacer(modifier = Modifier.width(35.dp))
            Icon(
                Icons.Rounded.Edit,
                contentDescription = "Edit Weight",
                modifier = Modifier
                    .padding(10.dp) // Add spacing after the number
                    .size(25.dp)
                    .clickable { isEditingWeight = !isEditing },
                tint = CustomWhite
            )
        }


        LaunchedEffect(scaffoldState) {
            if (showSnackbar) {
                scaffoldState.snackbarHostState.showSnackbar(
                    message = "Changes Saved Successfully!",
                    duration = SnackbarDuration.Short
                )
                showSnackbar = false
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        Button(
            onClick = {
                person = tempPerson
                gender = tempGender
                age = tempAge
                weight = tempWeight

                isEditingPerson = false
                isEditingGender = false
                isEditingAge = false
                isEditingWeight = false
            }
        )
        {
            Text("Save Changes")
        }
    }
}



