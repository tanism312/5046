package com.example.assignemt1

import android.telecom.Call.Details
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.ui.*
import androidx.compose.foundation.layout.*


import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.SnackbarDuration
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.TextField
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusProperties
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material.rememberScaffoldState
import androidx.compose.ui.res.stringResource
import androidx.compose.material3.Text



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Details(navController: NavHostController) {
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
    )
    {
        Text(text = "Daily Tips",
            modifier = Modifier.padding(top = 16.dp), // Add some padding between title and value
            color = CustomBlack,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )


    val states = listOf(
        "Sedentary" to "You need to consume 2000 Cal each day.",
        "Light Activity" to "You need to consume 2200 Cal each day.",
        "Moderate Activity" to "You need to consume 2400 Cal each day.",
        "Very Active" to "You need to consume 2600 Cal each day.",
        "Extremely Active" to "You need to consume 2800 Cal each day."
    )

    var isExpanded by remember { mutableStateOf(false) }
    var selectedState by remember { mutableStateOf(states[0].first) }
    var tip by remember { mutableStateOf(states[0].second) }

    Column(modifier = Modifier.padding(16.dp)) {
        ExposedDropdownMenuBox(
            expanded = isExpanded,
            onExpandedChange = { isExpanded = it },
        ) {
            TextField(
                modifier = Modifier.menuAnchor().fillMaxWidth().focusProperties { canFocus = false }
                    .padding(bottom = 8.dp),
                readOnly = true,
                value = selectedState,
                onValueChange = {},
                label = { Text("Activity Level") },
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded) },
            )
            ExposedDropdownMenu(
                expanded = isExpanded,
                onDismissRequest = { isExpanded = false }
            ) {
                states.forEach { (activityLevel, _) ->
                    DropdownMenuItem(
                        text = { Text(activityLevel) },
                        onClick = {
                            selectedState = activityLevel
                            tip = states.find { it.first == activityLevel }?.second ?: ""
                            isExpanded = false
                        },
                        contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding,
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(55.dp))
        if (tip.isNotEmpty()) {

            Text(
                text = "Tip: $tip",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                color = CustomBlack
            )
        }


    }
        Spacer(modifier = Modifier.height(55.dp))
        Button(onClick = { navController.popBackStack() }) {
            Text("Back to Profile")
        }
}

}

