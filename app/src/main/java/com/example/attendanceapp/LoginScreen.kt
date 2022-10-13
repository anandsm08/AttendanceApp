package com.example.attendanceapp



import android.os.Bundle

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.attendanceapp.ui.theme.AttendanceAppTheme

//class MainActivity : ComponentActivity() {
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContent {
//            AttendanceAppTheme() {
//                // A surface container using the 'background' color from the theme
//                Surface(color = MaterialTheme.colors.background) {
//                    LoginScreen()
//                }
//            }
//        }
//    }
//
//
//
//}

//fun getColor(c:String):Color{
//    return Color(android.graphics.Color.parseColor(c))
//}

fun verifyLogin(username: String, password: String,navController: NavController){
    // Call API get response  from API
    // do whatever you want on response
    val logedInSuccess=true
    if (logedInSuccess)
        navController.navigate("dashboard")

}

@Composable
fun LoginScreen(navController: NavController) {

    // Automatically get the values from the views
    var username = remember { mutableStateOf("")}
    var password = remember { mutableStateOf("")}

    val colblue= getColor("#00BFFF")
    val colWhite=Color.White
    val gradientcol = Brush.verticalGradient(colors= listOf(colWhite,colblue), startY = 1700.0f , endY = Float.POSITIVE_INFINITY )

    // Box is like layout may
    Box (

        modifier = Modifier.background(gradientcol)
    ){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            verticalArrangement = Arrangement.Center
        ) {

            Text(text = "Hello",color = getColor("#00BFFF"), fontSize = 32.sp)
            Text(text = "There!",color = getColor("#00BFFF"), fontSize = 28.sp)
            Spacer(modifier = Modifier.height(20.dp))
            Text(text = "Login", color = getColor("#00BFFF"))
            Spacer(modifier = Modifier.height(5.dp))
            OutlinedTextField(
                value = username.value,
                onValueChange = {
                    username.value = it
                },
                label = {  // Floating label top of input box
                    Text(text = "Username")
                },
                placeholder = {   // hint
                    Text(text = "Enter user name")
                },
                modifier = Modifier.fillMaxWidth(),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = getColor("#00BFFF"),
                    unfocusedBorderColor = getColor("#00BFFF")
                )
            )
            OutlinedTextField(
                value = password.value,
                onValueChange = {
                    password.value = it
                },
                label = {
                    Text(text = "Password")
                },
                placeholder = {
                    Text(text = "Enter Password")
                },
                modifier = Modifier.fillMaxWidth(),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = getColor("#00BFFF"),
                    unfocusedBorderColor = getColor("#00BFFF")
                )
            )

            Button(
                onClick = {
                    verifyLogin(username.value, password.value ,navController)
                },
                colors = ButtonDefaults.buttonColors(getColor("#00BFFF")),
                modifier = Modifier.fillMaxWidth()) {
                Text(text = "Login",color=Color.White)

            }

        }
    }


}

