package com.example.firebaseauth.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.firebaseauth.presentation.navgraph.Route

@Composable
fun HomeScreen(
    viewModel: HomeViewModel,
    navController: NavController
){
    val screenState by viewModel.profileState.collectAsState()
    LaunchedEffect(screenState.isSuccess) {
        if(screenState.isSuccess){
            navController.navigate(Route.LoginScreen.route){
                popUpTo(Route.HomeScreen.route){inclusive = true}
            }
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(text = "Welcome")
        Spacer(modifier = Modifier.height(20.dp))

        if(screenState.error!=null){
            Text(text = screenState.error.toString())
        }

        if(!screenState.isLoading) {
            Button(onClick = { viewModel.logOut() }) {
                Text(text = "Log out")
            }
        }else{
            CircularProgressIndicator()
        }
    }
}