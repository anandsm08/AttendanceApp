package com.example.attendanceapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.attendanceapp.R
import com.example.attendanceapp.ui.theme.AttendanceAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AttendanceAppTheme {
                MarkedPage()
            }
        }
    }
}
//fun formatStr(str:String){
//    if
//}

fun getColor(c:String):Color{
    return Color(android.graphics.Color.parseColor(c))
}

@Composable
fun MarkedPage(){
    Column(horizontalAlignment = Alignment.CenterHorizontally,modifier = Modifier.fillMaxSize()){
        MarkedHeader("SE_COMP_B","02:56 AM", "Saturday","8-10-2022")
        MarkedStudentCardContainer(true)
    }
}

@Composable
fun MarkedHeader(className:String, curTime:String,Day: String, Date: String){
    Column() {
        Spacer(modifier = Modifier.height(30.dp))
        Row() {
            Text(text = className, color = getColor("#00BFFF"), fontWeight = FontWeight.W700, fontSize = 20.sp)
            Spacer(modifier = Modifier.width(140.dp))
            Button(
                onClick = { /*TODO*/ },
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(getColor("#00BFFF"))
            ) {
                Text(text = "Submit", modifier = Modifier.padding(horizontal = 10.dp, vertical = 0.dp), color = Color.White)
            }
        }
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column() {
                Text(text = Day, color = getColor("#708090"), fontWeight = FontWeight.W600)
                Text(text = Date, color = getColor("#708090"))
                Text(text = curTime, color = getColor("#708090"))
            }
            Spacer(modifier = Modifier.width(177.dp))
            Button(
                onClick = { /*TODO*/ },
                contentPadding = PaddingValues(0.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(getColor("#00BFFF"))
            ) {
                val img = painterResource(R.drawable.arrow_left)
                Image(painter = img, contentDescription = "Android Logo", Modifier.size(20.dp))
            }
            Spacer(modifier = Modifier.width(10.dp))
            Button(
                onClick = { /*TODO*/ },
                contentPadding = PaddingValues(0.dp),
                colors = ButtonDefaults.buttonColors(getColor("#00BFFF")),
                shape = RoundedCornerShape(18.dp),
                modifier = Modifier.width(35.dp)
            ) {
                val img = painterResource(R.drawable.arrow_down)
                Image(painter = img, contentDescription = "Android Logo", Modifier.size(20.dp))
            }

        }
        Spacer(modifier = Modifier.height(20.dp))
    }
}

// pass this function the data of students
@Composable
fun MarkedStudentCardContainer(isAbsenteePage: Boolean){
    Card(
        backgroundColor = getColor("#7FFFD4"),
        shape = RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp),
        modifier = Modifier
            .fillMaxHeight()
            .width(350.dp)
    ) {
        Column(

            Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Spacer(modifier = Modifier.width(20.dp))
                PresentAbsentChoiceCard("Present", isAbsenteePage)
                PresentAbsentChoiceCard("Absent", !isAbsenteePage)
//                Spacer(modifier = Modifier.width(20.dp))

            }
            val scrollState = rememberScrollState()
            Spacer(modifier = Modifier.height(10.dp))
            Column(
                modifier = Modifier.verticalScroll(state = scrollState)
            ) {
                for(i in 1..20) {
                    MarkedStudentCard(347, "Pranav Sunil", !isAbsenteePage)
                    Spacer(modifier = Modifier.height(10.dp))
                    MarkedStudentCard(348, "Pranav Sunil", !isAbsenteePage)
                    Spacer(modifier = Modifier.height(10.dp))
                }
            }
        }
    }
}

@Composable
fun PresentAbsentChoiceCard(str: String, isTransparent: Boolean){
        Card(
            backgroundColor = Color.White,
            shape = RoundedCornerShape(bottomEnd = 12.dp, bottomStart = 12.dp),
            modifier = Modifier
                .alpha(if (isTransparent) 0.5F else 1F)
                .height(50.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxHeight(),
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(text = str, color = getColor("#00BFFF"), modifier = Modifier.padding(10.dp))
            }
        }
}

@Composable
fun MarkedStudentCard(rollNo: Int, name: String, isPresenteePage: Boolean){
    Card(
        backgroundColor = getColor("#00BFFF"),
        shape = RoundedCornerShape(6.dp),
        modifier = Modifier.width(320.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(horizontal = 25.dp, vertical = 20.dp)
        ) {
            Text(text = rollNo.toString(), color = Color.White, fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
            Spacer(modifier = Modifier.width(14.dp))
            Text(text=name, color = Color.White, fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                Button(
                    onClick = { /*TODO*/ },
                    contentPadding = PaddingValues(0.dp),
                    shape = RoundedCornerShape(12.dp),

                    modifier = Modifier.height(20.dp),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = if (isPresenteePage) getColor("#FF4500") else getColor(
                            "#32CD32"
                        )
                    )
                ) {
                    if (isPresenteePage)
                        Text(
                            text = "Mark Absent?",
                            fontSize = 9.sp,
                            color = Color.White,
                            modifier = Modifier.padding(horizontal = 5.dp, vertical = 0.dp)
                        )
                    else
                        Text(
                            text = "Mark Present?",
                            fontSize = 9.sp,
                            color = Color.White,
                            modifier = Modifier.padding(horizontal = 5.dp, vertical = 0.dp)
                        )
                }
            }
        }
    }
}
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    AttendanceAppTheme {
        PresentAbsentChoiceCard("Present", false)
    }
}