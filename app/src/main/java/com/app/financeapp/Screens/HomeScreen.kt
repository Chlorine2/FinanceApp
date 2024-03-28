package com.app.financeapp.Screens

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.rounded.AddCircle
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.app.financeapp.AppViewModelProvider
import com.app.financeapp.Navigation.ScreensEnum
import com.app.financeapp.R
import com.app.financeapp.Screens.Chart.BarChart
import com.app.financeapp.Screens.Chart.PieChart
import com.app.financeapp.ViewModels.DBViewModel

@Composable
fun HomeScreen(viewModel: DBViewModel = viewModel(factory = AppViewModelProvider.Factory)){

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
        .padding(horizontal = 20.dp)
    ){
        Column {
            Text(text = "Капітал", fontFamily = FontFamily(Font(R.font.main_text)),
                fontSize = 25.sp, modifier = Modifier.padding(top = 10.dp))
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
                0 -> Costs(viewModel)
                1 -> Income(viewModel)
            }



        }
    }

}

@Composable
fun Costs(viewModel: DBViewModel){
    Column(modifier = Modifier
        .verticalScroll(rememberScrollState())
        .padding(horizontal = 10.dp)) {


        Card(
            modifier = Modifier
                .height(280.dp)
                .fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            ),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 4.dp,
            )
        ) {
            Box(modifier = Modifier.padding(20.dp)) {
                Column() {

                    Text(
                        text = "Грудень 2023", fontFamily = FontFamily(Font(R.font.main_text)),
                        fontSize = 20.sp
                    )

                    Box(
                        modifier = Modifier
                            .height(200.dp)
                            .fillMaxWidth()
                            .padding(top = 35.dp, start = 2.dp, end = 2.dp)
                    ) {

                        val allItems = viewModel.getAllSpendingItems().collectAsState(initial = listOf())
                        val sumCategory = viewModel.getUniqueSpendingCategoriesWithTotalSums().collectAsState(initial = listOf())
                        if (allItems.value.isNotEmpty()) {

                            var iterator : Int = 0
                            val data = mutableMapOf<String, Int>()
                            while (iterator < sumCategory.value.size){
                                data[sumCategory.value[iterator].category] = sumCategory.value[iterator].total
                                iterator++

                            }

                            PieChart(
                                data = data,
                                radiusOuter = 70.dp,
                                chartBarWidth = 17.dp
                            )
                        }
                    }
                    Text(
                        text = "більше", fontFamily = FontFamily(Font(R.font.main_text)),
                        fontSize = 14.sp, textAlign = TextAlign.End, modifier = Modifier
                            .fillMaxWidth()
                            .padding(end = 10.dp)
                    )

                }
            }

        }
        
        Text(
            text = "Топ витрат", fontFamily = FontFamily(Font(R.font.main_text)),
            fontSize = 20.sp, modifier = Modifier.padding(vertical = 20.dp, horizontal = 30.dp)
        )
        Card(
            modifier = Modifier
                .height(280.dp)
                .fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            ),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 4.dp,
            )
        ) {
            Box() {
                Column() {

                    Box(modifier = Modifier.padding(start = 20.dp,top = 50.dp), contentAlignment = Alignment.Center){
                        BarChart(
                            data = mapOf(
                                Pair(0.4f, "1"),
                                Pair(0.2f, "2"),
                                Pair(0.8f, "3"),
                                Pair(0.5f, "4"),
                                Pair(0.8f, "5"),), max_value = 50
                        )
                    }
                    
                    Text(
                        text = "більше", fontFamily = FontFamily(Font(R.font.main_text)),
                        fontSize = 14.sp, textAlign = TextAlign.End, modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 10.dp, end = 30.dp)
                    )

                }
            }

        }

        Spacer(modifier = Modifier.height(50.dp))
    }
}

@Composable
fun Income(viewModel: DBViewModel){
    Column(modifier = Modifier
        .verticalScroll(rememberScrollState())
        .padding(horizontal = 10.dp)) {


        Card(
            modifier = Modifier
                .height(280.dp)
                .fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            ),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 4.dp,
            )
        ) {
            Box(modifier = Modifier.padding(20.dp)) {
                Column() {

                    Text(
                        text = "Грудень 2023", fontFamily = FontFamily(Font(R.font.main_text)),
                        fontSize = 20.sp
                    )

                    Box(
                        modifier = Modifier
                            .height(200.dp)
                            .fillMaxWidth()
                            .padding(top = 35.dp, start = 2.dp, end = 2.dp)
                    ) {

                        val allItems = viewModel.getAllSpendingItems().collectAsState(initial = listOf())
                        val sumCategory = viewModel.getUniqueIncomeCategoriesWithTotalSums().collectAsState(initial = listOf())
                        if (allItems.value.isNotEmpty()) {

                            var iterator : Int = 0
                            val data = mutableMapOf<String, Int>()
                            while (iterator < sumCategory.value.size){
                                data[sumCategory.value[iterator].category] = sumCategory.value[iterator].total
                                iterator++

                            }

                            PieChart(
                                data = data,
                                radiusOuter = 70.dp,
                                chartBarWidth = 17.dp
                            )
                        }
                    }
                    Text(
                        text = "більше", fontFamily = FontFamily(Font(R.font.main_text)),
                        fontSize = 14.sp, textAlign = TextAlign.End, modifier = Modifier
                            .fillMaxWidth()
                            .padding(end = 10.dp)
                    )

                }
            }

        }

        Text(
            text = "Топ доходів", fontFamily = FontFamily(Font(R.font.main_text)),
            fontSize = 20.sp, modifier = Modifier.padding(vertical = 20.dp, horizontal = 30.dp)
        )
        Card(
            modifier = Modifier
                .height(280.dp)
                .fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            ),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 4.dp,
            )
        ) {
            Box() {
                Column() {

                    Box(modifier = Modifier.padding(start = 20.dp,top = 50.dp), contentAlignment = Alignment.Center){
                        BarChart(
                            data = mapOf(
                                Pair(0.4f, "1"),
                                Pair(0.2f, "2"),
                                Pair(0.8f, "3"),
                                Pair(0.5f, "4"),
                                Pair(0.8f, "5"),), max_value = 50
                        )
                    }

                    Text(
                        text = "більше", fontFamily = FontFamily(Font(R.font.main_text)),
                        fontSize = 14.sp, textAlign = TextAlign.End, modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 10.dp, end = 30.dp)
                    )

                }
            }

        }

        Spacer(modifier = Modifier.height(50.dp))
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