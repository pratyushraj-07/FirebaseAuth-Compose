package com.example.firebaseauth.presentation.navgraph

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.firebaseauth.presentation.HomeScreen
import com.example.firebaseauth.presentation.HomeViewModel
import com.example.firebaseauth.presentation.authscreen.loginscreen.LoginScreen
import com.example.firebaseauth.presentation.authscreen.loginscreen.LoginViewModel
import com.example.firebaseauth.presentation.authscreen.signupscreen.SignUpViewModel
import com.example.firebaseauth.presentation.authscreen.signupscreen.SignupScreen

@Composable
fun Navigation(){

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Route.SignupScreen.route) {
        composable(
            route = Route.SignupScreen.route,
        ){
            val viewModel : SignUpViewModel = hiltViewModel()
            SignupScreen(
                navController = navController,
                viewModel = viewModel,
                event = viewModel::onEvent
            )
        }

        composable(
            route = Route.LoginScreen.route,
        ){
            val viewModel: LoginViewModel = hiltViewModel()
            LoginScreen(navController = navController, viewModel = viewModel, event = viewModel::onEvent)
        }

        composable(
            route = Route.HomeScreen.route
        ){
            val viewModel : HomeViewModel = hiltViewModel()
            HomeScreen(viewModel = viewModel, navController)
        }

    }
}