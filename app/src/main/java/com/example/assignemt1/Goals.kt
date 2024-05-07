package com.example.assignemt1

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Goals(navController: NavHostController) {

    Image(
        painter = painterResource(id = R.drawable.login),
        contentDescription = "Background Image",
        contentScale = ContentScale.Crop,
        modifier = Modifier.fillMaxWidth())


        Column( // Use Column for vertical arrangement
            modifier = Modifier
                .fillMaxWidth()  // Make the column fill the entire screen
                .padding(16.dp), // Add some padding around content
            horizontalAlignment = Alignment.CenterHorizontally, // Center content horizontally
            verticalArrangement = Arrangement.spacedBy(16.dp) // Add spacing between boxes
        )
        {
    Text(text = "Health History",
        modifier = Modifier.padding(top = 16.dp), // Add some padding between title and value
        color = CustomBlack,
        fontSize = 20.sp,
        fontWeight = FontWeight.Bold
    )

            Box( // Use a Box for each section to apply background color and rounded corners
                modifier = Modifier
                    .fillMaxWidth() // Make the box occupy full width
                    .height(150.dp) // Set a height for the box
                    .background(CustomBlue) // Set the background color to blue
                    .clip(shape = RoundedCornerShape(8.dp))
            ) {
                Row(horizontalArrangement = Arrangement.SpaceEvenly)
                {

                    Spacer(modifier = Modifier.width(42.dp))
                    Column() {

                        Text(
                            text = "Date",
                            modifier = Modifier
                                .padding(top = 20.dp), // Add some padding between title and value
                            color = CustomWhite,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "24/01/04",
                            modifier = Modifier
                                .padding(top = 30.dp), // Add some padding between title and value
                            color = CustomWhite,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold
                        )


                    }
                    Spacer(modifier = Modifier.width(55.dp))
                    Column() {
                        Text(
                            text = "Weight",
                            modifier = Modifier
                                // Align text in the center of the box
                                .padding(top = 20.dp), // Add some top padding
                            color = CustomWhite,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "75.3 kg",
                            modifier = Modifier

                                .padding(top = 30.dp), // Add some padding between title and value
                            color = CustomWhite,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Spacer(modifier = Modifier.width(55.dp))
                    Column() {
                        Text(
                            text = "Fat",
                            modifier = Modifier
                                // Align text in the center of the box
                                .padding(top = 20.dp), // Add some top padding
                            color = CustomWhite,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "20%",
                            modifier = Modifier

                                .padding(top = 30.dp), // Add some padding between title and value
                            color = CustomWhite,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }

            }

            Box( // Use a Box for each section to apply background color and rounded corners
                modifier = Modifier
                    .fillMaxWidth() // Make the box occupy full width
                    .height(150.dp) // Set a height for the box
                    .background(CustomBlue) // Set the background color to blue
                    .clip(RoundedCornerShape(8.dp)) // Apply rounded corners with 8dp radius
            ) {
                // Text content for the first section (Posts)
                Row(horizontalArrangement = Arrangement.SpaceEvenly)
                {
                    Spacer(modifier = Modifier.width(42.dp))
                    Column() {
                        Text(
                            text = "Date",
                            modifier = Modifier

                                .padding(top = 20.dp), // Add some padding between title and value
                            color = CustomWhite,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "23/01/04",
                            modifier = Modifier

                                .padding(top = 30.dp), // Add some padding between title and value
                            color = CustomWhite,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Spacer(modifier = Modifier.width(55.dp))
                    Column() {
                        Text(
                            text = "Weight",
                            modifier = Modifier
                                // Align text in the center of the box
                                .padding(top = 20.dp), // Add some top padding
                            color = CustomWhite,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "75.3 kg",
                            modifier = Modifier

                                .padding(top = 30.dp), // Add some padding between title and value
                            color = CustomWhite,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Spacer(modifier = Modifier.width(55.dp))
                    Column() {
                        Text(
                            text = "Fat",
                            modifier = Modifier
                                // Align text in the center of the box
                                .padding(top = 20.dp), // Add some top padding
                            color = CustomWhite,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "20%",
                            modifier = Modifier

                                .padding(top = 30.dp), // Add some padding between title and value
                            color = CustomWhite,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }

            }

            Box( // Use a Box for each section to apply background color and rounded corners
                modifier = Modifier
                    .fillMaxWidth() // Make the box occupy full width
                    .height(150.dp) // Set a height for the box
                    .background(CustomBlue) // Set the background color to blue
                    .clip(RoundedCornerShape(8.dp)) // Apply rounded corners with 8dp radius
            ) {
                // Text content for the first section (Posts)
                Row(horizontalArrangement = Arrangement.SpaceEvenly)
                {
                    Spacer(modifier = Modifier.width(42.dp))
                    Column() {
                        Text(
                            text = "Date",
                            modifier = Modifier

                                .padding(top = 20.dp), // Add some padding between title and value
                            color = CustomWhite,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "22/01/04",
                            modifier = Modifier

                                .padding(top = 30.dp), // Add some padding between title and value
                            color = CustomWhite,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Spacer(modifier = Modifier.width(55.dp))
                    Column() {
                        Text(
                            text = "Weight",
                            modifier = Modifier
                                // Align text in the center of the box
                                .padding(top = 20.dp), // Add some top padding
                            color = CustomWhite,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "75.3 kg",
                            modifier = Modifier

                                .padding(top = 30.dp), // Add some padding between title and value
                            color = CustomWhite,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Spacer(modifier = Modifier.width(55.dp))
                    Column() {
                        Text(
                            text = "Fat",
                            modifier = Modifier
                                // Align text in the center of the box
                                .padding(top = 20.dp), // Add some top padding
                            color = CustomWhite,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "20%",
                            modifier = Modifier

                                .padding(top = 30.dp), // Add some padding between title and value
                            color = CustomWhite,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }

            }
            Spacer(modifier = Modifier.width(55.dp))
            Button(onClick = { navController.popBackStack() }) {
                Text("Back to Profile")
            }

        }


    }

