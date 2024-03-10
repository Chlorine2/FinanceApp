package com.app.financeapp.Screens


import MainCostScreen
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.financeapp.R


@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PersistScreen(){
    val fontMont = FontFamily(
        Font(R.font.main_text)
    )

    var selectedTabIndex by remember { mutableIntStateOf(0) }

    val boxBackgroundColor = if (selectedTabIndex == 0) {
        Color(0xFFFF8087)
    } else {
        Color(0xFF58F2A9)
    }

Column {
    Box(
        modifier = Modifier
            .background(boxBackgroundColor)
            .fillMaxWidth()
            .height(200.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = "Додати запис",
                fontFamily = fontMont,
                fontSize = 24.sp,
                color = Color.Black,
                modifier = Modifier
                    .padding(top = 10.dp)
            )

            TabRow(
                selectedTabIndex = selectedTabIndex,
                modifier = Modifier.padding(top = 10.dp),
                containerColor = boxBackgroundColor,
                contentColor = Color.Black,
                indicator = { tabPositions ->
                    TabRowDefaults.Indicator(
                        color = Color.Black,
                        modifier = Modifier.tabIndicatorOffset(tabPositions[selectedTabIndex])
                    )
                }
            ) {
                Tab(
                    selected = selectedTabIndex == 0,
                    onClick = { selectedTabIndex = 0 },
                    modifier = Modifier.padding(bottom = 16.dp)
                ) {
                    Text(text = "Витрати", fontSize = 20.sp, fontFamily = fontMont)
                }
                Tab(
                    selected = selectedTabIndex == 1,
                    onClick = { selectedTabIndex = 1 },
                    modifier = Modifier.padding(bottom = 16.dp)
                ) {
                    Text(text = "Доходи", fontSize = 20.sp, fontFamily = fontMont)
                }
            }

            var amountText by remember { mutableStateOf("") }
            val currencyText = "UAH"
            val focusManager = LocalFocusManager.current

            Row(
                modifier = Modifier.padding(top = 20.dp, start = 6.dp, end = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedTextField(
                    value = if (amountText.isEmpty()) {
                        if (selectedTabIndex == 0) "-53 000" else "+100 000"
                    } else {
                        amountText
                    },
                    onValueChange = { newValue -> amountText = newValue },
                    modifier = Modifier.background(boxBackgroundColor),
                    textStyle = TextStyle(fontSize = 24.sp, fontFamily = fontMont),
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Done
                    ), keyboardActions = KeyboardActions(onDone = {
                        focusManager.clearFocus()
                    }),

                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = boxBackgroundColor,
                        unfocusedBorderColor = boxBackgroundColor,
                    )
                )
                Text(
                    text = currencyText,
                    style = TextStyle(fontSize = 24.sp, fontFamily = fontMont)
                )
            }

        }

    }
    MainCostScreen(selectedTabIndex)
}
}






