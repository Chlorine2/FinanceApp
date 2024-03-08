package com.app.financeapp.Navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.app.financeapp.Screens.HomeScreen
import com.app.financeapp.Screens.PersistScreen
import com.app.financeapp.Screens.StatisticScreen

@Composable
fun MainNavGraph(navController: NavHostController) {

    NavHost(
        navController = navController,
        route = Graphs.MAIN,
        startDestination = ScreensEnum.HOME.name
    ) {
        composable(ScreensEnum.HOME.name){
            HomeScreen()
        }
        composable(ScreensEnum.PERSIST.name){
            PersistScreen()
        }
        composable(ScreensEnum.STATISTIC.name){
            StatisticScreen()
        }
    }
}
