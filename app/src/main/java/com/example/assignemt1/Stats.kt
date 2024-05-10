package com.example.assignemt1

import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.assignemt1.ui.theme.Purple80

@Composable
fun PieChart(
    data:Map<String, Int>,
    radiusQuter: Dp = 90.dp,
    chartBarWidth: Dp = 20.dp,
    animDuration: Int = 1000
){
    Image(
        painter = painterResource(id = R.drawable.login),
        contentDescription = "Background Image",
        contentScale = ContentScale.Crop,
        modifier = Modifier.fillMaxWidth())

    val totalSum = data.values.sum()
    val floatValue = mutableListOf<Float>()

    data.values.forEachIndexed{
        index, values ->
        floatValue.add(index, 360 * values.toFloat()/ totalSum.toFloat())
    }


    val colors = listOf(
        Customwhit,
        CustomBlue,
        Purple80,
        CustomRed,
        CustomGray
    )

    var animationPlayed by remember {mutableStateOf(false)}

    var lastValue = 0f

    val animateSize by animateFloatAsState(
        targetValue = if (animationPlayed) radiusQuter.value * 2f else 0f,
        animationSpec = tween(
            durationMillis = animDuration,
            delayMillis = 0,
            easing = LinearOutSlowInEasing
        )
    )

    val animateRotation by animateFloatAsState(targetValue = if (animationPlayed) 90f * 11f else 0f,
        animationSpec = tween(
            durationMillis = animDuration,
            delayMillis = 0,
            easing = LinearOutSlowInEasing
    )
    )


    LaunchedEffect(key1 = true){
        animationPlayed = true
    }


    Column(modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally)
    {
        Column( // Use Column for vertical arrangement
            modifier = Modifier
                .fillMaxWidth()  // Make the column fill the entire screen
                .padding(16.dp), // Add some padding around content
            horizontalAlignment = Alignment.CenterHorizontally, // Center content horizontally
            verticalArrangement = Arrangement.spacedBy(16.dp) // Add spacing between boxes
        ) { // Use a Column for vertical arrangement
            Text(
                text = "Report", // Your desired text
                modifier = Modifier.padding(16.dp),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = CustomBlack
            )
        }
//        Spacer(modifier = Modifier.padding(20.dp))
        Box(modifier = Modifier.size(animateSize.dp),
            contentAlignment = Alignment.Center)
        {
            Text(text = "1600 Cal",fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = CustomWhite)
            Canvas(
                modifier = Modifier
                    .size(radiusQuter * 1.5f)
                    .rotate(animateRotation)
            ){
                floatValue.forEachIndexed{ index, value ->
                    drawArc(
                        color = colors[index],
                        lastValue,
                        value,
                        useCenter = false,
                        style = Stroke(chartBarWidth.toPx(), cap = StrokeCap.Butt)
                    )
                    lastValue += value
                }
            }
        }
        DetailsPieChart(data = data, colors = colors)
    }
}

@Composable
fun DetailsPieChart(
    data:Map<String, Int>,
    colors:List<Color>
){

    Column (modifier = Modifier
        .padding(top = 15.dp)
        .fillMaxWidth())
    {
        Text(text = "Daily Calorie Intake", color = CustomWhite, fontSize = 20.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(start = 25.dp))
        data.values.forEachIndexed{index, value -> DetailsPieChartItem(
                data = Pair(data.keys.elementAt(index), value),
                color = colors[index]
        )
        }
    }

}

@Composable
fun DetailsPieChartItem(
    data:Pair<String, Int>,
    height:Dp = 45.dp,
    color: Color
){
    Surface(
        modifier = Modifier.padding(vertical = 10.dp, horizontal = 30.dp),
        color = Color.Transparent
    ){
        Row(modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically)
        {
            Box(modifier = Modifier
                .background(
                    color = color,
                    shape = RoundedCornerShape(10.dp)
                )
                .size(height)
            )
            Column(modifier = Modifier.fillMaxWidth())
            {
                Text(modifier = Modifier.padding(start = 15.dp),
                    text = data.first,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    color = CustomWhite
                )
                Text(modifier = Modifier.padding(start = 15.dp),
                    text = data.second.toString() + " cal",
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    color = CustomWhite
                )
            }
        }
    }

}

@Composable
fun Stats(navController: NavHostController){
    PieChart(data = mapOf(
        Pair("Fruits and Vegetables", 270),
        Pair("Grains", 800),
        Pair("Proteins", 110),
        Pair("Fats and Oils", 300),
        Pair("Sweets and Sugars", 120),
    ))
}


@Preview(showSystemUi = true, showBackground = true)
@Composable
fun ShowChart(){
    PieChart(data = mapOf(
        Pair("Fruits and Vegetables", 270),
        Pair("Grains", 800),
        Pair("Proteins", 110),
        Pair("Fats and Oils", 300),
        Pair("Sweets and Sugars", 120),
    ))
}