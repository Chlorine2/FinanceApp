package com.app.financeapp.Screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.AddCircle
import androidx.compose.material.icons.rounded.DateRange
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.app.financeapp.Navigation.MainNavGraph
import com.app.financeapp.Navigation.ScreensEnum

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainSetup(navController: NavHostController = rememberNavController()){

 var navigationSelectedItem by remember { mutableStateOf(0) }


    Scaffold (
        bottomBar = {
            NavigationBar{
                bottomNavItems.forEachIndexed { index, navigationItem ->

                    NavigationBarItem(selected = index == navigationSelectedItem,
                        onClick = {
                        navigationSelectedItem = index
                        navController.navigate(navigationItem.route)
                                },
                        label = { Text(text = navigationItem.name)},
                        icon = {
                          Icon(navigationItem.icon, contentDescription = navigationItem.name)
                        }
                    )
                }
            }
        }
    ){
      paddingValues ->
      Box(modifier = Modifier.padding(paddingValues)) {
        MainNavGraph(navController = navController)
      }
    }

}

data class BottomNavItem(
 val name: String,
 val route: String,
 val icon: ImageVector,
)

val bottomNavItems = listOf(
 BottomNavItem(
  name = "Home",
  route = ScreensEnum.HOME.name,
  icon = Icons.Rounded.Home,
 ),
 BottomNavItem(
  name = "Persist",
  route = ScreensEnum.PERSIST.name,
  icon = Icons.Rounded.AddCircle,
 ),
 BottomNavItem(
  name = "Statistic",
  route = ScreensEnum.STATISTIC.name,
  icon = Icons.Rounded.DateRange,
 ),
)
