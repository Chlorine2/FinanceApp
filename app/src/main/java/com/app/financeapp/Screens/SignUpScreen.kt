package com.app.financeapp.Screens

import androidx.compose.runtime.Composable
import com.app.financeapp.Screens.additionalScreen.InUpScreen


@Composable
fun SignUpScreen(onClick : () -> Unit){
    InUpScreen(onClick = {}, isSignUp = true)
}