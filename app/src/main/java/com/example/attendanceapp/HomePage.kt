package com.example.attendanceapp

import android.os.Bundle
import android.text.Layout
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.attendanceapp.ui.theme.AttendanceAppTheme
import androidx.compose.ui.Alignment.Companion
import androidx.compose.ui.unit.dp
import androidx.compose.material.Icon
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Face
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

class HomePage : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AttendanceAppTheme {
                Home()
                }
        }
    }
}
fun getColor(c:String):Color{
    return Color(android.graphics.Color.parseColor(c))
}

@Composable
fun Home() {
    TopAppBar() {

    }
    Box(modifier = Modifier.background(getColor("#adfff4"))) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp)
        ) {
            Spacer(modifier = Modifier.height(25.dp))
            Row(){
//                Text(text = "Home",color=getColor("#00BFFF"), fontSize = 18.sp)
                Spacer(modifier = Modifier.width(300.dp))
                OutlinedButton(
                    onClick = { /*TODO*/ },
                    modifier =Modifier.size(50.dp) ,
                    shape = CircleShape,
                    border = BorderStroke(1.dp,getColor("#00BFFF"))
                ) {
                    Icon(Icons.Default.Face, contentDescription ="description",Modifier.size(32.dp) )
                }
            }
            
            Spacer(modifier = Modifier.height(45.dp))
            Column(){
                Text(text = "Good Morning",color= getColor("#00BFFF"), fontSize = 20.sp)

                Text(text = "Prof. Patil",color= Color.Black, fontSize = 24.sp)
            }
            Spacer(modifier = Modifier.height(45.dp))
            Column() {
                Text(text = "Today", fontWeight = FontWeight.Bold)
                Text(text = "Tuesday")
                Text(text = "11:00 AM")
                Text(text = "11/10/2022")
            }
            Spacer(modifier = Modifier.height(10.dp))
            Card(
                backgroundColor = Color.White,
                shape = RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp, bottomEnd = 5.dp, bottomStart = 5.dp)
            ){

                Column(Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(), horizontalAlignment = Alignment.CenterHorizontally
                ) {
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(text = "CLASSES", color=getColor("#00BFFF"), fontWeight = FontWeight.Bold, fontSize = 24.sp)
                    for( a in 1..9) {
                        Spacer(modifier = Modifier.height(10.dp))
                        Button(
                            onClick = {

                            },

                            colors = ButtonDefaults.buttonColors(getColor("#00BFFF")),
                            modifier = Modifier.width(320.dp)) {
                            Row() {
                                Text(text = "SE CS A", color = Color.White)
                                Spacer(modifier = Modifier.width(175.dp))
                                Icon(Icons.Default.ArrowForward, contentDescription = "")
                            }
                        }
                    }

                }
            }


        }

        
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    AttendanceAppTheme {
        Home()
    }
}