package com.app.financeapp.Screens

import androidx.compose.foundation.clickable
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.app.financeapp.Screens.additionalScreen.InUpScreen


@Composable
fun SignUpScreen(onClick : () -> Unit){

    InUpScreen(onClick = {onClick()}, isSignUp = true)
}