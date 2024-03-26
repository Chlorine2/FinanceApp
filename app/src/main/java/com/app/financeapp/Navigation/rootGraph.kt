package com.app.financeapp.Navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.app.financeapp.Screens.MainSetup

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun RootNavigationGraph(navController: NavHostController){
    NavHost(navController = navController, route = Graphs.ROOT, startDestination = Graphs.AUTHENTICATION){
        authNavGraph(navController = navController)
        composable(route = Graphs.MAIN){
            MainSetup()
        }

    }
}