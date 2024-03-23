package com.app.financeapp.Screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.app.financeapp.Screens.additionalScreen.InUpScreen

@Composable
fun SignInScreen(onClick: () -> Unit) {
    InUpScreen(onClick = {}, isSignUp = false)
}

@Preview
@Composable
fun ShowSignIn(){
    SignInScreen(onClick = {})
}