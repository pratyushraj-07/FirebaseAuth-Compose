package com.example.firebaseauth.presentation.navgraph

sealed class Route(val route :String) {

    data object SignupScreen : Route("signup")
    data object LoginScreen : Route("login")
    data object HomeScreen: Route("home")

}