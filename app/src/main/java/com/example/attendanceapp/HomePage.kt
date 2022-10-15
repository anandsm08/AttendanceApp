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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.attendanceapp.ui.theme.AttendanceAppTheme
import androidx.compose.ui.unit.dp
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.attendanceapp.data.datasource
import com.example.attendanceapp.model.Classes
import com.example.attendanceapp.ui.theme.BrandColor

class HomePage() : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AttendanceAppTheme {
//                Home(navController = rememberNavController())
                Setup()
                }
        }
    }
}
//fun getColor(c:String):Color{
//    return Color(android.graphics.Color.parseColor(c))
//}

@Composable
fun Home(navController: NavController) {
    Dashboard (
        "Prof. Shruti Patil",
   "Morning",
     "Today",
    "24/10/2020",
     "12:04pm",
     navController = navController,
        classesList = datasource().loadClasses()
    )





}
@Composable
fun Dashboard (
    name: String = "Prof. Shruti Patil",
    greet: String = "Morning",
    dayy: String = "Today",
    dateStr: String = "24/10/2020",
    timeStr: String = "12:04pm",
    navController: NavController,
    classesList: List<Classes>
) {
    Column(

        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(getColor("#F0FFFF"))
            .padding(top = 30.dp)
            .padding(horizontal = 30.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.End,
            modifier = Modifier.fillMaxWidth()
        ) {
            RoundImage(
                image = painterResource(id = R.drawable.blankprofilepicture),
                modifier = Modifier
                    .size(60.dp)
            )
        }
        Spacer(modifier = Modifier.width(60.dp))
        Text(text = "Good $greet",
            color = BrandColor,
            fontWeight = FontWeight.W700,
            fontSize = 30.sp,
            overflow = TextOverflow.Ellipsis
        )
        Text(text = name,
            color = Color.Black,
            fontWeight = FontWeight.W700,
            fontSize = 40.sp,
            overflow = TextOverflow.Ellipsis
        )

        Spacer(modifier = Modifier.height(15.dp))

        DayDate(
            dayy, dateStr, timeStr
        )

        Spacer(modifier = Modifier.height(15.dp))

        Text(text = "Your Classes",
            color = BrandColor,
            fontWeight = FontWeight.W700,
            fontSize = 25.sp,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .fillMaxWidth()
        )

        ShowList( navController = navController)

    }
}

@Composable
fun RoundImage(
    image: Painter,
    modifier: Modifier = Modifier
) {
    Image(
        painter = image,
        contentDescription = null,
        modifier = modifier
            .aspectRatio(1f, matchHeightConstraintsFirst = true)
            .size(5.dp)
            .border(
                width = 1.dp,
                color = BrandColor,
                shape = CircleShape
            )
            .padding(3.dp)
            .clip(CircleShape)
    )
}
@Composable
fun DayDate(
//    modifier: Modifier= Modifier,
    dayy: String = "Today",
    dateStr: String = "24/10/2020",
    timeStr: String = "12:04pm",
){
    Text(text = dayy,
        color = getColor("#708090"),
        fontWeight = FontWeight.W700,
        fontSize = 15.sp,
    )
    Text(text = dateStr,
        color = getColor("#708090"),
        fontWeight = FontWeight.W500,
        fontSize = 15.sp
    )
    Text(text = timeStr,
        color = getColor("#708090"),
        fontWeight = FontWeight.W500,
        fontSize = 15.sp
    )
}
@Composable
fun MakeCard(
    record: List<Classes>,
    section: String,
    navController: NavController,
){
    Text(
        text = section,
        color = BrandColor,
        fontWeight = FontWeight.W700,
        fontSize = 20.sp,
        overflow = TextOverflow.Ellipsis,
        modifier = Modifier
            .fillMaxWidth()
    )
   AListClasses(classesList = datasource().loadClasses(), navController = navController)

}
@Composable
fun AListClasses(classesList: List<Classes>, modifier: Modifier = Modifier,navController: NavController) {
    for (classes in classesList){
        HomeCard(record = classes, navController = navController)
        Spacer(modifier = Modifier.height(1.dp))
    }
}
@Composable
fun HomeCard(record: Classes, navController: NavController){
    Button(
        onClick = {  navController.navigate("attendance") },
        colors = ButtonDefaults.buttonColors(
            backgroundColor = BrandColor
        ),
        shape = RoundedCornerShape(6.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(horizontal = 6.dp, vertical = 6.dp)
        ) {
            Text(text= stringResource(id = record.classname), color = Color.White, fontSize = 15.sp, fontWeight = FontWeight.SemiBold)
            Log.d("nameclass",stringResource(id = record.classname))
            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                Row(
                    modifier = Modifier.height(30.dp),
                ) {
                    RoundImage(
                        image = painterResource(R.drawable.arrowcircle),
                        modifier = Modifier
                            .size(60.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun ShowList(

    modifier: Modifier = Modifier,
    navController: NavController,

    ){
    val sections = listOf("First Year","Second Year", "Third Year")



    val scrollstate = rememberScrollState()
    Card(
        shape = RoundedCornerShape(topEnd = 12.dp, topStart = 12.dp),
        modifier = Modifier
            .fillMaxHeight()
    ) {
        Column(
            modifier = Modifier
                .verticalScroll(state = scrollstate)
                .background(Color.White)
                .padding(15.dp)
            ,
            verticalArrangement = Arrangement.spacedBy(15.dp)
        ) {
            for (section in sections){
            MakeCard(record = datasource().loadClasses(), section =section , navController = navController)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    AttendanceAppTheme {
        Home(navController = rememberNavController())
    }
}