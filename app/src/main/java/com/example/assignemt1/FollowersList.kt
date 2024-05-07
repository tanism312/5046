package com.example.assignemt1

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource

data class Follower(val name: String, val imageResId: Int)
@Composable
fun FollowersList(navController: NavHostController) {

    val followerNames = listOf(
        Follower("Alice Gler",R.drawable.followerz), Follower("Bob Wright",R.drawable.followerb), Follower("Charlie Hl",R.drawable.followerc), Follower("David S.",R.drawable.followerd),
        Follower("Emily Blue",R.drawable.followere), Follower("Ross Jackson",R.drawable.followerf), Follower("Mike Matheso",R.drawable.followerg), Follower("Jane Eric",R.drawable.followerh),
        Follower("Jordan Norman",R.drawable.followeri), Follower("Kate Chaplin",R.drawable.followerj), Follower("Jennifer Aniston",R.drawable.followerk), Follower("Sara William",R.drawable.followerj),
        Follower("John Chris", R.drawable.followerk),Follower("Chris Mcz",R.drawable.followerm), Follower("William Morgan",R.drawable.followern), Follower("Katy Fox",R.drawable.followero)
    )

    Image(
        painter = painterResource(id = R.drawable.login),
        contentDescription = "Background Image",
        contentScale = ContentScale.Crop,
        modifier = Modifier.fillMaxWidth()
    )
    Column( // Use Column for vertical arrangement
        modifier = Modifier
            .fillMaxWidth()  // Make the column fill the entire screen
            .padding(16.dp), // Add some padding around content
        horizontalAlignment = Alignment.CenterHorizontally, // Center content horizontally
        verticalArrangement = Arrangement.spacedBy(16.dp) // Add spacing between boxes
    ) { // Use a Column for vertical arrangement
        Text(
            text = "Followers List", // Your desired text
            modifier = Modifier.padding(16.dp),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = CustomBlack
        )

        Button(onClick = { navController.popBackStack() }) {
            Text("Back to Profile")
        }

        Surface(shape = RoundedCornerShape(6.dp), color = Color.Transparent) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween  // Align on opposite sides
            ) {

                LazyColumn(modifier = Modifier.fillMaxWidth()) {

                    items(followerNames) { Follower ->
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Image(
                                painter = painterResource(id = Follower.imageResId), // Load image resource
                                contentDescription = "Follower Photo",
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .size(50.dp)
                                    .clip(CircleShape)

                            )
                            Text(text = Follower.name, modifier = Modifier.padding(16.dp))
                        }
                    }

                }

            }
        }
    }
}

