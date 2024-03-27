package com.app.financeapp.Screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.app.financeapp.Screens.additionalScreen.InUpScreen

@Composable
fun SignInScreen(onClick: () -> Unit) {
    InUpScreen(onClick = {onClick()}, isSignUp = false)
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun ShowSignIn(){
    SignInScreen(onClick = {})
}