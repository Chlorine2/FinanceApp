import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.financeapp.R
import com.maxkeppeker.sheets.core.models.base.rememberUseCaseState
import com.maxkeppeler.sheets.calendar.CalendarDialog
import com.maxkeppeler.sheets.calendar.models.CalendarConfig
import com.maxkeppeler.sheets.calendar.models.CalendarSelection
import com.maxkeppeler.sheets.calendar.models.CalendarStyle
import java.time.LocalDate

val fontMont = FontFamily(
    Font(R.font.main_text)
)






@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ListItem(
    item: String,
    expandedItems: MutableState<Set<String>>,
    tabIndex: Int,
    selectedOptions: MutableState<Map<String, Any>>,
    selectedOptions1: MutableState<Map<String, Any>>,
    ) {
    val isExpanded = remember { mutableStateOf(expandedItems.value.contains(item)) }
    val selectDate = remember { mutableStateOf(LocalDate.now()) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 26.dp, end = 10.dp, top = 10.dp, bottom = 10.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .clickable { isExpanded.value = !isExpanded.value }
                .fillMaxWidth()
        ) {
            val icon = when (item) {
                "Рахунок" -> R.drawable.wallet
                "Категорія" -> R.drawable.category
                "Дата Транзакції" -> R.drawable.calendar
                else -> R.drawable.wallet
            }
            Icon(
                painter = painterResource(id = icon),
                contentDescription = null,
                modifier = Modifier.size(24.dp)
            )
            Text(text = item, fontFamily = fontMont, fontSize = 18.sp, modifier = Modifier.padding(start = 10.dp))

            Icon(
                imageVector = if (isExpanded.value) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown,
                contentDescription = null,
            )
            if (selectedOptions.value.containsKey(item)) {
                Text(
                    text = selectedOptions.value[item].toString(),
                    fontFamily = fontMont,
                    fontSize = 16.sp,
                    modifier = Modifier.padding(start = 40.dp, end = 0.dp)
                )
            }
        }



        if (isExpanded.value) {
            when (item) {
                "Рахунок" -> {
                    val accountOptions = listOf(
                        "Готівка" to R.drawable.cash,
                        "MonoBank" to R.drawable.card
                    )
                    accountOptions.forEach { (option, icon) ->
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .clickable {
                                    selectedOptions.value = selectedOptions.value + (item to option)
                                    selectedOptions.value = selectedOptions.value
                                    selectedOptions1.value = selectedOptions1.value + (item to option)
                                }
                                .padding(top = 10.dp)
                        ) {
                            Icon(
                                painter = painterResource(id = icon),
                                contentDescription = null,
                                modifier = Modifier
                                    .size(24.dp)
                                    .padding(start = 10.dp)
                            )
                            Spacer(modifier = Modifier.width(10.dp))
                            Text(
                                text = option,
                                fontFamily = fontMont,
                                fontSize = 16.sp
                            )
                        }
                    }
                }
                "Категорія" -> {
                    val categoryOptions = if (tabIndex == 0) {
                        ListItems()
                    } else {
                        ListItems2()
                    }
                    categoryOptions.forEach  { (option, icon) ->
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .clickable {
                                    selectedOptions.value = selectedOptions.value + (item to option)
                                    selectedOptions.value = selectedOptions.value
                                    selectedOptions1.value = selectedOptions1.value + (item to option)
                                }
                                .padding(top = 10.dp)
                        ) {
                            Icon(
                                painter = painterResource(id = icon),
                                contentDescription = null,
                                modifier = Modifier
                                    .size(24.dp)
                                    .padding(start = 10.dp)
                            )
                            Spacer(modifier = Modifier.width(10.dp))
                            Text(
                                text = option,
                                fontFamily = fontMont,
                                fontSize = 16.sp
                            )
                        }
                    }
                }

                "Дата Транзакції" -> {
                    CustomDatePicker(selectDate)
                }
                else -> {}
            }
        }

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CustomDatePicker(selectedOptions: MutableState<LocalDate>) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 26.dp, end = 10.dp, top = 10.dp, bottom = 10.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(
                text = selectedOptions.value.toString(),
                fontFamily = fontMont,
                fontSize = 16.sp,
                modifier = Modifier.padding(end = 25.dp)
            )
        }
        Spacer(modifier = Modifier.height(10.dp))


        CalendarDialog(
            state = rememberUseCaseState(visible = true, true, onCloseRequest = { }),
            config = CalendarConfig(
                yearSelection = true,
                style = CalendarStyle.MONTH,
            ),
            selection = CalendarSelection.Date(
                selectedDate = selectedOptions.value
            ) { newDate ->
                selectedOptions.value = newDate
            },
        )

    }
}

@Composable
fun FilledTonalButtonExample(
    onClick: () -> Unit
) {
    FilledTonalButton(
        onClick = {
            onClick()
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 10.dp, end = 10.dp),
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFFD5C9F6),
            contentColor = Color.Black
        )
    ) {
        Text("Зберегти",fontFamily = fontMont, fontSize = 18.sp)
    }
}


@Composable
fun Additional() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Row(
            modifier = Modifier.padding(top = 10.dp),
            horizontalArrangement = Arrangement.spacedBy(14.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            MyButton("Фото", R.drawable.gallery, Color(0xFFFFDF8F), {})
            MyButton("Локація", R.drawable.location, Color(0xFFD5C9F6), {})
            MyButton("Нотатка", R.drawable.bulb, Color(0xFF58F2A9), {})
        }
        Spacer(modifier = Modifier.height(4.dp))
        Row(
            modifier = Modifier.horizontalScroll(rememberScrollState()),
            horizontalArrangement = Arrangement.spacedBy(14.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            MyButton("Регулярний", R.drawable.regular, Color(0xFFE17ED1), {})
            MyButton("Регулярний", R.drawable.regular, Color(0xFFE17ED1), {})
            MyButton("Регулярний", R.drawable.regular, Color(0xFFE17ED1), {})

        }

        Spacer(modifier = Modifier.height(10.dp))
    }
}


@Composable
fun MyButton(
    text: String,
    icon: Int,
    color: Color,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .size(100.dp)
            .background(color, shape = RoundedCornerShape(8.dp))
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center,
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                painter = painterResource(id = icon),
                contentDescription = null,
                modifier = Modifier.size(28.dp)
            )
            Text(text = text, modifier = Modifier.padding(top = 4.dp), fontFamily = fontMont)
        }
    }
}


@Composable
private fun ListItems(): List<Pair<String, Int>> {
    return listOf(
        "Харчування" to R.drawable.food,
        "Житло" to R.drawable.house,
        "Розваги" to R.drawable.thunder,
        "Товари для дому" to R.drawable.home
    )
}

@Composable
private fun ListItems2(): List<Pair<String, Int>> {
    return listOf(
        "Заробітна плата" to R.drawable.salary,
        "Подарунок" to R.drawable.gift,
        "Повернення боргу" to R.drawable.borrow
    )
}
