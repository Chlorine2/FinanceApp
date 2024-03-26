package com.app.financeapp.Screens

import androidx.compose.foundation.clickable
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.app.financeapp.AppViewModelProvider
import com.app.financeapp.Screens.additionalScreen.InUpScreen
import com.app.financeapp.ViewModels.PersistViewModel

@Composable
fun SignInScreen(onClick: () -> Unit) {
    InUpScreen(onClick = {onClick()}, isSignUp = false)
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun ShowSignIn(){
    SignInScreen(onClick = {})
}