package com.app.financeapp.Screens.additionalScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.financeapp.R
import fontMont


@Composable
fun InUpScreen(
    onClick: () -> Unit,
    isSignUp: Boolean = true // додано параметр для визначення типу екрану
) {
    var textValue by remember { mutableStateOf("") }

    Column {
        Box(
            modifier = Modifier
                .background(Color(0xFFD5C9F6))
                .fillMaxWidth()
                .fillMaxHeight(0.5f)
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = if (isSignUp) "Create Account" else "Sign In", // змінено заголовок відповідно до типу екрану
                    fontFamily = fontMont,
                    fontSize = 24.sp,
                    color = Color.Black,
                    modifier = Modifier
                        .padding(top = 132.dp, bottom = 30.dp)
                )


                OutlinedEditText(
                    value = textValue,
                    onValueChange = { newText -> textValue = newText },
                    placeholder = { Text("Email Address") }
                )

                OutlinedEditText(
                    value = textValue,
                    onValueChange = { newText -> textValue = newText },
                    placeholder = { Text("Password") },
                    trailingIcon = {
                        Icon(
                            painterResource(id = R.drawable.baseline_remove_red_eye_24),
                            contentDescription = "Icon"
                        )
                    }
                )
            }
        }

        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            SignButton(
                text = if (isSignUp) "Sign Up" else "Sign In", // змінено текст кнопки відповідно до типу екрану
                onClick = {},
            )

            ReDirect(
                text = if (isSignUp) "I’m already a member." else "I don't have an account.", // змінено текст переадресації відповідно до типу екрану
                clickableText = if (isSignUp) " Sign in" else " Sign up", // змінено текст посилання відповідно до типу екрану
                onClick = { onClick() }
            )

            DoubleHorizontalLinesWithText()
            SocialMediaIconsRow()
        }
    }
}





@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OutlinedEditText(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    label: @Composable (() -> Unit)? = null,
    placeholder: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 33.dp)
            .background(Color.White, shape = RoundedCornerShape(16.dp)),
        label = label,
        placeholder = placeholder,
        singleLine = true,
        trailingIcon  = trailingIcon,
        textStyle = TextStyle(
            fontSize = 16.sp,
            color = Color.Black
        ),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            unfocusedBorderColor = Color.Transparent,
            focusedBorderColor = Color.Transparent
        )
    )
}

@Composable
fun SignButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    textStyle: TextStyle = TextStyle(
        fontSize = 18.sp,
        color = Color.Black,
        fontFamily = fontMont
    ),
    backgroundColor: Color = Color(0xFFD5C9F6), // Світло-фіолетовий колір
    contentColor: Color = Color.Black
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 30.dp, horizontal = 33.dp)
            .background(backgroundColor, shape = RoundedCornerShape(12.dp)),
        //.border(1.dp, Color.Gray, shape = RoundedCornerShape(16.dp)),
        enabled = enabled,
        colors = ButtonDefaults.buttonColors(containerColor = backgroundColor, contentColor = contentColor),
    ) {
        Text(text, style = textStyle)
    }
}

@Composable
fun ReDirect(
    text: String,
    clickableText: String,
    onClick: () -> Unit,
    textStyle: TextStyle = TextStyle(
        fontSize = 14.sp,
        color = Color.Black,
        fontFamily = fontMont
    )
){
    Row {
        Text(text, style = textStyle)

        ClickableText(
            text = AnnotatedString(clickableText),
            onClick = { onClick() },
            style = TextStyle(color = Color.Blue, fontSize = 14.sp, fontFamily = fontMont)
        )
    }
}


@Composable
fun DoubleHorizontalLinesWithText() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 63.dp, vertical = 30.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .weight(1f)
                .height(1.dp)
                .background(Color(0xFFC6C6C6))
        )
        Text(
            text = "OR",
            modifier = Modifier
                .padding(horizontal = 12.dp),
            style = TextStyle(
                fontSize = 15.sp,
                color = Color.Black,
                fontFamily = fontMont
            )
        )
        Box(
            modifier = Modifier
                .weight(1f)
                .height(1.dp)
                .background(Color(0xFFC6C6C6))
        )
    }


}



@Composable
fun SocialMediaIcon(
    painter: Painter,
    contentDescription: String,
    onClick: () -> Unit
) {
    Image(
        painter = painter,
        contentDescription = contentDescription,
        modifier = Modifier
            .size(32.dp)
            .clip(CircleShape)
            .clickable(onClick = onClick)
    )
}

@Composable
fun SocialMediaIconsRow() {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 63.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        SocialMediaIcon(
            painter = painterResource(id = R.drawable.ic_google),
            contentDescription = "Google",
            onClick = { /* обробник кліку для Google */ }
        )
        Spacer(
            modifier = Modifier.width(10.dp)
        )
        SocialMediaIcon(
            painter = painterResource(id = R.drawable.ic_facebook),
            contentDescription = "Facebook",
            onClick = { /* обробник кліку для Facebook */ }
        )
    }
}


