package com.app.financeapp.Screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material.icons.rounded.AddCircle
import androidx.compose.material.icons.rounded.DateRange
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.financeapp.Navigation.ScreensEnum
import com.app.financeapp.R
import com.app.financeapp.Screens.Chart.PieChart

@Composable
fun HomeScreen(){
    var tabIndex by remember{
        mutableIntStateOf(0)
    }

    Box(modifier = Modifier
        .fillMaxSize()
        .paint(
            painterResource(
                id = R.drawable.background
            ),
            contentScale = ContentScale.FillBounds
        )
        .padding(vertical = 10.dp, horizontal = 20.dp)
    ){
        Column {
            Text(text = "Капітал", fontFamily = FontFamily(Font(R.font.main_text)),
                fontSize = 25.sp)
            Spacer(modifier = Modifier.height(20.dp))
            Row()
            {
                Card(modifier = Modifier
                    .height(120.dp)
                    .weight(1f),
                    colors = CardDefaults.cardColors(
                        containerColor = Color.White
                    )) {
                    Column(modifier = Modifier.padding(15.dp)) {
                        Icon(
                            painterResource(id = R.drawable.baseline_money_24),
                            contentDescription = "add account",
                        )
                        Text(text = "Готівка", fontFamily = FontFamily.SansSerif,
                            fontSize = 15.sp)

                        Text(text = "253 000,00 UAH", fontFamily = FontFamily(Font(R.font.main_text)),
                            fontSize = 19.sp)
                    }

                }
                Spacer(modifier = Modifier.width(35.dp))
                Card(modifier = Modifier
                    .height(120.dp)
                    .weight(1f)
                    .clickable {

                    },
                    colors = CardDefaults.cardColors(
                        containerColor = Color.White
                    ),
                    ){
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Icon(imageVector = Icons.Default.AddCircle,
                                contentDescription = "add account",
                                tint = Color(0xFFaa8eff)
                            )
                            Text(text = "Додати рахунок", fontFamily = FontFamily.SansSerif,
                                fontSize = 15.sp)
                        }
                    }

                }
            }
            Spacer(modifier = Modifier.height(20.dp))
            TabRow(selectedTabIndex = tabIndex,
                contentColor = Color.Black,
                containerColor = Color(0xFFd5c9f6),
                indicator = {},
                divider = {}) {
                homeTabRowItems.forEachIndexed{ index, item ->
                    Tab(selected = tabIndex == index,
                        onClick = { tabIndex = index;},
                        text = {
                            Text(
                                text = item.name,
                                fontFamily = FontFamily(Font(R.font.main_text)),
                                fontSize = 19.sp,
                                color = if (tabIndex == index) Color.Black else Color.Gray
                            )
                        }
                    )


                }
            }
            Spacer(modifier = Modifier.height(10.dp))
            when(tabIndex){
                0 -> Costs()
                1 -> Income()
            }



        }
    }

}

@Composable
fun Costs(){
    Card(modifier = Modifier
        .height(280.dp)
        .fillMaxWidth()
        .padding(horizontal = 10.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
    ){
        Box(modifier = Modifier.padding(20.dp)){
            Column() {

                Text(text = "Грудень 2023", fontFamily = FontFamily(Font(R.font.main_text)),
                    fontSize = 18.sp)

                Box(modifier = Modifier.height(220.dp).fillMaxWidth().padding(vertical = 40.dp, horizontal = 5.dp)) {
                    PieChart(data = mapOf(
                        Pair("Sample-1", 150),
                        Pair("Sample-2", 120),
                        Pair("Sample-3", 110),
                        Pair("Sample-4", 170),
                        Pair("Sample-5", 120),
                    ),
                        radiusOuter = 70.dp,
                        chartBarWidth = 20.dp)
                }

            }
        }

    }
}

@Composable
fun Income(){
    Card(modifier = Modifier
        .height(300.dp)
        .fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
    ){
        Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()){
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Icon(imageVector = Icons.Default.AddCircle,
                    contentDescription = "add account",
                    tint = Color(0xFFaa8eff)
                )
                Text(text = "Додати рахунок", fontFamily = FontFamily.SansSerif,
                    fontSize = 15.sp)
            }
        }

    }
}
data class HomeTabRowItem(
    val name: String,
    val route: String,
    val icon: ImageVector,
)

val homeTabRowItems = listOf(
    HomeTabRowItem(
        name = "Витрати",
        route = ScreensEnum.HOME.name,
        icon = Icons.Rounded.Home,
    ),
    HomeTabRowItem(
        name = "Доходи",
        route = ScreensEnum.PERSIST.name,
        icon = Icons.Rounded.AddCircle,
    )

)


@Preview(showSystemUi = true, showBackground = true)
@Composable
fun Preview(){
    HomeScreen()
}