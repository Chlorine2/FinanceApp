package com.app.financeapp.Screens.Chart

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun BarChart(
    data: Map<Float, String>,
    max_value: Int
) {

    val context = LocalContext.current
    // BarGraph Dimensions
    val barGraphHeight by remember { mutableStateOf(150.dp) }
    val barGraphWidth by remember { mutableStateOf(20.dp) }
    // Scale Dimensions
    val scaleYAxisWidth by remember { mutableStateOf(50.dp) }
    val scaleLineWidth by remember { mutableStateOf(2.dp) }

    Column(
        verticalArrangement = Arrangement.Top
    ) {

        Row(
            modifier = Modifier
                .height(barGraphHeight),
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.Start
        ) {
            // scale Y-Axis
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(scaleYAxisWidth),
                contentAlignment = Alignment.BottomCenter
            ) {
                Column(
                    modifier = Modifier.fillMaxHeight(),
                    verticalArrangement = Arrangement.Bottom
                ) {
                    Text(text = max_value.toString())
                    Spacer(modifier = Modifier.fillMaxHeight())
                }

                Column(
                    modifier = Modifier.fillMaxHeight(),
                    verticalArrangement = Arrangement.Bottom
                ) {
                    Text(text = (max_value / 2).toString())
                    Spacer(modifier = Modifier.fillMaxHeight(0.5f))
                }

            }



            // graph
            data.forEach {
                Box(
                    modifier = Modifier
                        .padding(start = barGraphWidth, bottom = 5.dp)
                        .clip(shape = RoundedCornerShape(15.dp, 15.dp, 0.dp, 0.dp))
                        .width(barGraphWidth)
                        .fillMaxHeight(it.key)
                        .background(Color(0xFFd5c9f6))
                        .clickable {
                            Toast
                                .makeText(context, it.key.toString(), Toast.LENGTH_SHORT)
                                .show()
                        }
                )
            }

        }


        // Scale X-Axis
        Row(
            modifier = Modifier
                .padding(start = scaleYAxisWidth+barGraphWidth+scaleLineWidth)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(barGraphWidth)
        ) {

            data.values.forEach {
                Text(
                    modifier = Modifier.width(barGraphWidth),
                    text = it,
                    textAlign = TextAlign.Center
                )
            }

        }

    }

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun BarPreview(){
    BarChart(data = mapOf(
        Pair(0.4f, "1"),
        Pair(0.2f, "2"),
        Pair(0.8f, "3"),
        Pair(0.1f, "4")), max_value = 10040)
}