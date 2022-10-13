package com.example.attendanceapp


import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.attendanceapp.data.datasource
import com.example.attendanceapp.model.Record
import com.example.attendanceapp.ui.theme.AttendanceAppTheme
import com.example.attendanceapp.ui.theme.BrandColor
//import com.example.attendanceapp.MainActivity.Header
import javax.security.auth.login.LoginException


class attendance : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AttendanceAppTheme {
//               AttendancePage()
            }
        }
    }
}
//fun getColors(c:String):Color{
//    return Color(android.graphics.Color.parseColor(c))
//}

@Composable
fun  AttendancePage(navController: NavController,RecordList: List<Record>){
    Column(horizontalAlignment = Alignment.CenterHorizontally,modifier = Modifier.fillMaxSize()){
        Header("SE_COMP_B","02:56 AM", "Saturday","8-10-2022", navController)
        StudentCardContainer(RecordList)
        Log.d("LISTAAA",datasource().loadAttendance().toString())
    }
}

@Composable
fun Header(className:String, curTime:String,Day: String, Date: String,navController: NavController){
    Column() {
        Spacer(modifier = Modifier
            .height(30.dp))
        Row() {
            Text(text = className, color = getColor("#00BFFF"), fontWeight = FontWeight.W700, fontSize = 20.sp)
            Spacer(modifier = Modifier.width(140.dp))
            Button(
                onClick = { navController.navigate("present") },
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
                onClick = { navController.navigate("dashboard") },
                contentPadding = PaddingValues(0.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(BrandColor)
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
@Composable
fun SimpleCheckboxComponent() {
    // in below line we are setting
    // the state of our checkbox.
    val checkedState = remember { mutableStateOf(false)
    }
    // in below line we are displaying a row
    // and we are creating a checkbox in a row.
    Row {
        Checkbox(
            // below line we are setting
            // the state of checkbox.
            checked = checkedState.value,
            // below line is use to add padding
            // to our checkbox.
            modifier = Modifier.padding(5.dp),
            // below line is use to add on check
            // change to our checkbox.
            onCheckedChange = { checkedState.value = it },

            )
        // below line is use to add text to our check box and we are
        // adding padding to our text of checkbox
        //  Text(text = "Checkbox Example", modifier = Modifier.padding(16.dp))
    }
}

// pass this function the data of students
@Composable
fun StudentCardContainer(RecordList: List<Record>){
    Card(
        backgroundColor = getColor("#7FFFD4"),
        shape = RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp),
        modifier = Modifier
            .fillMaxHeight()
            .width(350.dp)
    ) {
        Column(
            Modifier
                .fillMaxWidth()
//                .background(getColor("#ffffff"))
            ,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Spacer(modifier = Modifier.width(20.dp))
                Card(
                    backgroundColor = Color.White,
                    shape = RoundedCornerShape(bottomEnd = 12.dp, bottomStart = 12.dp),
                    modifier = Modifier
                        .height(50.dp)
                ){
                    Column(
                        modifier = Modifier.fillMaxHeight(),
                        verticalArrangement = Arrangement.Bottom,
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Text(text = "Attendance", color = BrandColor, modifier = Modifier.padding(5.dp))
                    }
                }
            }
            val scrollState = rememberScrollState()
            Spacer(modifier = Modifier.height(10.dp))
            Column(
                modifier = Modifier.verticalScroll(state = scrollState)
            ) {
                AList(RecordList)
            }
        }
    }
}


@Composable
fun AList(RecordList: List<Record>, modifier: Modifier = Modifier) {
       for (record in RecordList){
           StudentCard(record = record)
           Spacer(modifier = Modifier.height(10.dp))
        }
}

@Composable
fun StudentCard(record: Record){
    Card(
        backgroundColor = getColor("#00BFFF"),
        shape = RoundedCornerShape(6.dp),
        modifier = Modifier.width(320.dp)
    ) {
         Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(horizontal = 25.dp, vertical = 20.dp)

        ) {
            Text(
                text = stringResource(id = record.stringid),
                color = Color.White,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold
            )
            Spacer(modifier = Modifier.width(14.dp))
            Text(
                text = stringResource(id = record.stringResourceId),
                color = Color.White,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold
            )
            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                SimpleCheckboxComponent()
            }
        }

    }
    }



@Preview(showBackground = true)
@Composable
fun DefaultPreview1() {
//   AttendancePage()
}