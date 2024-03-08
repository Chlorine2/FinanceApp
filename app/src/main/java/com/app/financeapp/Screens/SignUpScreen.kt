package com.app.financeapp.Screens

import androidx.compose.foundation.clickable
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun SignUpScreen(onClick : () -> Unit){
    Text("Sign Up Screen", modifier = Modifier.clickable {
        onClick()
    })
}