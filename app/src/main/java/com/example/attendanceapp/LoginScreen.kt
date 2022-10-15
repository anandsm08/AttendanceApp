package com.example.attendanceapp



import android.os.Bundle
import android.util.Log

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.attendanceapp.data.datasource
import com.example.attendanceapp.ui.theme.AttendanceAppTheme
import kotlin.math.log

class LoginScreen : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AttendanceAppTheme() {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    Setup()
//                    LoginScreens(navController = rememberNavController())
                }
            }
        }
    }



}
@Composable
fun Setup(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "login"){
        composable("login"){LoginScreens( navController=navController)}
        composable("attendance"){AttendancePage(navController=navController, RecordList = datasource().loadAttendance())}
        composable("dashboard"){Home(navController = navController)}
        composable("absent"){MarkedPage(true,true, navController=navController, datasource().loadAttendance())}
        composable("present"){MarkedPage(false,true,navController=navController, datasource().loadAttendance())}
    }
}
//fun getColor(c:String):Color{
//    return Color(android.graphics.Color.parseColor(c))
//}

fun verifyLogin(username: String, password: String,navController: NavController){
    // Call API get response  from API
    // do whatever you want on response
    var logedInSuccess=false
    if(username== "Vrushali" && password=="1234567")
       logedInSuccess=true
    else
        logedInSuccess=true
    if (logedInSuccess)
        navController.navigate("dashboard")

}

@Composable
fun LoginScreens(navController: NavController) {

    // Automatically get the values from the views




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

            Text(text = "Hello", color = getColor("#00BFFF"), fontSize = 32.sp)
            Text(text = "There!", color = getColor("#00BFFF"), fontSize = 28.sp)
            Spacer(modifier = Modifier.height(20.dp))
            Text(text = "Login", color = getColor("#00BFFF"))
            Spacer(modifier = Modifier.height(5.dp))
            var username by remember { mutableStateOf("")
        }
            OutlinedTextField(
                value = username,
                onValueChange = {
                   newtext -> username = newtext
                },
                label = {  // Floating label top of input box
                    Text(text = "Username",color = getColor("#00BFFF"))
                },
                placeholder = {   // hint
                    Text(text = "Enter user name",color = getColor("#00BFFF"))
                },
                modifier = Modifier.fillMaxWidth(),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = getColor("#00BFFF"),
                    unfocusedBorderColor = getColor("#00BFFF")
                )
            )
            var password by remember { mutableStateOf("")}
            OutlinedTextField(
                value = password,
                onValueChange = {
                    newpassword ->  password = newpassword
                },
                label = {
                    Text(text = "Password",color = getColor("#00BFFF"))
                },
                placeholder = {
                    Text(text = "Enter Password",color = getColor("#00BFFF"))
                },
                modifier = Modifier.fillMaxWidth(),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = getColor("#00BFFF"),
                    unfocusedBorderColor = getColor("#00BFFF")
                )
            )

            Button(
                onClick = {
                    Log.d("username",username)

                    verifyLogin(username, password ,navController)
                },
                colors = ButtonDefaults.buttonColors(getColor("#00BFFF")),
                modifier = Modifier.fillMaxWidth()) {
                Text(text = "Login",color=Color.White)

            }

        }
    }


}

@Preview(showBackground = true)
@Composable
fun DefaultPreview9() {
    LoginScreens(navController = rememberNavController())
}