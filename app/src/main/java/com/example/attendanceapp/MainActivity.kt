package com.example.attendanceapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.attendanceapp.data.datasource
import com.example.attendanceapp.model.Record
import com.example.attendanceapp.ui.theme.AttendanceAppTheme
import com.example.attendanceapp.ui.theme.BrandColor

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AttendanceAppTheme {

            }
        }
    }
}


@Composable
fun Attendance_App(){

}

fun getColor(c:String):Color{
    return Color(android.graphics.Color.parseColor(c))
}

/*
* Pranav Rajeevan Start
* */


//annotation class composable


/*
* Pranav Rajeevan Ends
* */

/*
* Pranav Sunil Start
* */
@Composable
fun MarkedPage(isAbsenteePage: Boolean,isEnabledButton: Boolean, navController: NavController,RecordList: List<Record>){
    Column(horizontalAlignment = Alignment.CenterHorizontally,modifier = Modifier.fillMaxSize()){
        MarkedHeader("SE_COMP_B","02:56 AM", "Saturday","8-10-2022",isEnabledButton,navController)
        MarkedStudentCardContainer(isAbsenteePage,isEnabledButton,navController, RecordList)
    }
    Log.e("CHECKSET-Main",checkset.toString())
}

@Composable
fun MarkedHeader(className:String, curTime:String,Day: String, Date: String, isEnabledButton: Boolean, navController: NavController,){
    Column {
        Spacer(modifier = Modifier.height(30.dp))
        Row {
            Text(text = className, color = BrandColor, fontWeight = FontWeight.W700, fontSize = 20.sp)
            Spacer(modifier = Modifier.width(140.dp))
            if(isEnabledButton) {
                Button(
                    onClick = { navController.navigate("dashboard") },
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(BrandColor)
                ) {
                    Text(
                        text = "Submit",
                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 0.dp),
                        color = Color.White
                    )
                }
            }
        }
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                DayDate(dayy=Day, dateStr = Date, timeStr = curTime)
//                Text(text = Day, color = getColor("#708090"), fontWeight = FontWeight.W600)
//                Text(text = Date, color = getColor("#708090"))
//                Text(text = curTime, color = getColor("#708090"))
            }
            Spacer(modifier = Modifier.width(177.dp))
            Button(
                onClick = { navController.navigate("attendance") },
                contentPadding = PaddingValues(0.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(BrandColor)
            ) {
                val img = painterResource(R.drawable.arrow_left)
                Image(painter = img, contentDescription = "Android Logo", Modifier.size(20.dp))
            }
            Spacer(modifier = Modifier.width(10.dp))
            if (isEnabledButton) {
                Button(
                    onClick = { /*TODO*/ },
                    contentPadding = PaddingValues(0.dp),
                    colors = ButtonDefaults.buttonColors(BrandColor),
                    shape = RoundedCornerShape(18.dp),
                    modifier = Modifier.width(35.dp)
                ) {
                    val img = painterResource(R.drawable.arrow_down)
                    Image(painter = img, contentDescription = "Android Logo", Modifier.size(20.dp))
                }
            }

        }
        Spacer(modifier = Modifier.height(20.dp))
    }
}

// pass this function the data of students
@Composable
fun MarkedStudentCardContainer(isAbsenteePage: Boolean, isEnabledButton: Boolean,navController: NavController, RecordList: List<Record>){
    Card(
//        backgroundColor = getColor("#7FFFD4"),
        backgroundColor = getColor("#DE5757"),
        shape = RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp),
        modifier = Modifier
            .fillMaxHeight()
            .width(350.dp)
    ) {
        Column(
            Modifier
                .fillMaxWidth()
//                .background(getColor("#ffffff"))
                .background(getColor(if (isAbsenteePage) "#DE5757" else "#7fffd4"))
                    ,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Spacer(modifier = Modifier.width(20.dp))
                PresentAbsentChoiceCard("Present", isAbsenteePage, navController)
                PresentAbsentChoiceCard("Absent", !isAbsenteePage, navController)
            }
            val scrollState = rememberScrollState()
            Spacer(modifier = Modifier.height(10.dp))
            Column(
                modifier = Modifier.verticalScroll(state = scrollState)
            ) {
                for(record in RecordList) {
                    MarkedStudentCard(record, !isAbsenteePage,isEnabledButton,navController)
                    Spacer(modifier = Modifier.height(10.dp))
                    MarkedStudentCard(record, !isAbsenteePage,isEnabledButton,navController)
                    Spacer(modifier = Modifier.height(10.dp))
                }
            }
        }
    }
}

@Composable
fun PresentAbsentChoiceCard(str: String, isTransparent: Boolean,navController: NavController){
    Button(
        onClick = { navController.navigate(str.lowercase()) },
        colors = ButtonDefaults.buttonColors(Color.White),
        shape = RoundedCornerShape(bottomEnd = 12.dp, bottomStart = 12.dp),
        modifier = Modifier
            .alpha(if (isTransparent) 0.5F else 1F)
            .height(50.dp)
    ){
        Column(
            modifier = Modifier.fillMaxHeight(),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(text = str, color = BrandColor, modifier = Modifier.padding(5.dp))
        }
    }
}

@Composable
fun MarkedStudentCard(record: Record, isPresenteePage: Boolean, isEnabled: Boolean,navController: NavController){
    Card(
        backgroundColor = BrandColor,
        shape = RoundedCornerShape(6.dp),
        modifier = Modifier.width(320.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(horizontal = 25.dp, vertical = 20.dp)
        ) {
            Text(text = stringResource(id = record.stringid), color = Color.White, fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
            Spacer(modifier = Modifier.width(14.dp))
            Text(text=stringResource(id = record.stringResourceId), color = Color.White, fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                Button(
                    onClick = { /*TODO*/ },
                    contentPadding = PaddingValues(0.dp),
                    shape = RoundedCornerShape(12.dp),
                    enabled=isEnabled,
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
/*
* Pranav Sunil End
* */







@Preview(showBackground = true)
@Composable
fun DefaultPreview6() {
    AttendanceAppTheme {
        MarkedPage(isAbsenteePage = true, isEnabledButton =true , navController = rememberNavController(

        ), RecordList = datasource().loadAttendance() )
//        PresentAbsentChoiceCard("Present", false)
    }
}
