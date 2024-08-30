package com.example.firebaseauth.presentation.authscreen.signupscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.firebaseauth.R
import com.example.firebaseauth.presentation.navgraph.Route
import com.example.firebaseauth.ui.theme.DeepPurple
import com.example.firebaseauth.ui.theme.Indigo
import com.example.firebaseauth.ui.theme.LightPink
import com.example.firebaseauth.ui.theme.PaleGreen
import com.example.firebaseauth.ui.theme.SunsetOrange

@Composable
fun SignupScreen(
    viewModel: SignUpViewModel,
    navController: NavController,
    event: (SignUpEvent) -> Unit
) {

    val signUpState by viewModel.signUpState.collectAsState()
    val screenState by viewModel.signUpScreenState.collectAsState()

    LaunchedEffect(screenState.isSuccess) {
        if (screenState.isSuccess) {
            navController.navigate(Route.HomeScreen.route) {
                popUpTo(Route.SignupScreen.route) { inclusive = true }
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.linearGradient(
                    colors =
                    listOf(LightPink, PaleGreen)
                )
            )
    ) {
        Column(
            modifier = Modifier
                .padding(top = 48.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Welcome",
                fontSize = 42.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                style = TextStyle(shadow = Shadow(offset = Offset(3f, 3f)))
            )

            Text(
                text = "To",
                fontSize = 30.sp,
                color = Color.Black,
                style = TextStyle(shadow = Shadow(offset = Offset(2f, 2f)))
            )

            Text(
                text = "MessageHub",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Transparent,
                modifier = Modifier
                    .padding(top = 7.dp)
                    .background(
                        brush = Brush.horizontalGradient(
                            listOf(SunsetOrange, Indigo)
                        ),
                        shape = RoundedCornerShape(28.dp)
                    )
                    .padding(12.dp),
                style = TextStyle(
                    shadow = Shadow(
                        color = Color.White
                    )
                )
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        Card(
            modifier = Modifier
                .size(400.dp)
                .padding(start = 16.dp, end = 16.dp),
            colors = CardDefaults.cardColors(containerColor = colorResource(id = R.color.CoolGray)),
            shape = RoundedCornerShape(28.dp),
            elevation = CardDefaults.cardElevation(4.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        brush = Brush.linearGradient(
                            listOf(LightPink, PaleGreen)
                        )
                    ),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                OutlinedTextField(
                    value = signUpState.name,
                    onValueChange = { event(SignUpEvent.UpdateName(it)) },
                    singleLine = true,
                    shape = RoundedCornerShape(28.dp),
                    textStyle = TextStyle(
                        color = Color.Black,
                        fontSize = 16.sp
                    ),
                    placeholder = { Text(text = "Name", color = Color.Black) },
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color.DarkGray
                    )
                )

                Spacer(modifier = Modifier.height(20.dp))

                OutlinedTextField(
                    value = signUpState.email,
                    onValueChange = { event(SignUpEvent.UpdateMail(it)) },
                    singleLine = true,
                    shape = RoundedCornerShape(28.dp),
                    textStyle = TextStyle(
                        color = Color.Black,
                        fontSize = 16.sp
                    ),
                    placeholder = { Text(text = "E-mail", color = Color.Black) },
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color.DarkGray
                    )
                )

                Spacer(modifier = Modifier.height(20.dp))

                OutlinedTextField(
                    value = signUpState.password,
                    onValueChange = { event(SignUpEvent.UpdatePass(it)) },
                    singleLine = true,
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color.DarkGray
                    ),
                    textStyle = TextStyle(
                        color = Color.Black,
                        fontSize = 16.sp
                    ),
                    shape = RoundedCornerShape(28.dp),
                    placeholder = { Text(text = "Password", color = Color.Black) },
                    visualTransformation = if (signUpState.isPassVisible) VisualTransformation.None else PasswordVisualTransformation(),
                    trailingIcon = {
                        IconButton(onClick = { event(SignUpEvent.IsPassVisible) }) {
                            val icon =
                                if (signUpState.isPassVisible) R.drawable.baseline_visibility_24 else R.drawable.baseline_visibility_off_24
                            Icon(
                                painter = painterResource(id = icon),
                                contentDescription = "hide/show password",
                                tint = Color.Black
                            )
                        }
                    }
                )

                Spacer(modifier = Modifier.height(12.dp))

                if (screenState.error != null) {
                    Text(
                        text = screenState.error.toString(),
                        modifier = Modifier.padding(start = 6.dp, end = 6.dp),
                        color = Color.Red
                    )
                }

                if (!screenState.isLoading) {
                    Button(
                        onClick = {
                            event(SignUpEvent.SignUpUser)
                        },
                        colors = ButtonDefaults.buttonColors(DeepPurple)
                    ) {
                        Text(text = "SignUp", color = Color.White)
                    }
                } else {
                    CircularProgressIndicator(color = DeepPurple)
                }

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = "Already have an account? Login.",
                    color = Color.Blue,
                    modifier = Modifier.clickable {
                        navController.navigate(Route.LoginScreen.route) {
                            popUpTo(Route.SignupScreen.route) { inclusive = true }
                        }
                    }
                )
            }
        }
    }
}