package com.example.attendanceapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.attendanceapp.ui.theme.AttendanceAppTheme
import com.example.attendanceapp.ui.theme.BrandColor

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AttendanceAppTheme {
                MarkedPage(false,false)
//                Dashboard()
            }
        }
    }
}

fun getColor(c:String):Color{
    return Color(android.graphics.Color.parseColor(c))
}

/*
* Pranav Rajeevan Start
* */
@Composable
fun Dashboard (
    name: String = "Prof. Shruti Patil",
    greet: String = "Morning",
    dayy: String = "Today",
    dateStr: String = "24/10/2020",
    timeStr: String = "12:04pm",
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

            ShowList()

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
fun ShowList(
    modifier: Modifier = Modifier
){
        val sections = listOf("First Year","Second Year", "Third Year")
        val classs = listOf("SE_IT_A", "SE_IT_B", "SE_COMPS_A")


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
                    MakeCard(section)
                }
            }
        }
}

@Composable
fun MakeCard(
    section: String,
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

    for(i in 1..5) {
        HomeCard(classname = "SE_IT_B")
    }
}

@Composable
fun HomeCard(classname: String){
    Button(
        onClick = { /*TODO*/ },
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
            Text(text=classname, color = Color.White, fontSize = 15.sp, fontWeight = FontWeight.SemiBold)
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


//annotation class composable


/*
* Pranav Rajeevan Ends
* */

/*
* Pranav Sunil Start
* */
@Composable
fun MarkedPage(isAbsenteePage: Boolean,isEnabledButton: Boolean){
    Column(horizontalAlignment = Alignment.CenterHorizontally,modifier = Modifier.fillMaxSize()){
        MarkedHeader("SE_COMP_B","02:56 AM", "Saturday","8-10-2022",isEnabledButton)
        MarkedStudentCardContainer(isAbsenteePage,isEnabledButton)
    }
}

@Composable
fun MarkedHeader(className:String, curTime:String,Day: String, Date: String, isEnabledButton: Boolean){
    Column {
        Spacer(modifier = Modifier.height(30.dp))
        Row {
            Text(text = className, color = BrandColor, fontWeight = FontWeight.W700, fontSize = 20.sp)
            Spacer(modifier = Modifier.width(140.dp))
            if(isEnabledButton) {
                Button(
                    onClick = { /*TODO*/ },
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
                onClick = { /*TODO*/ },
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
fun MarkedStudentCardContainer(isAbsenteePage: Boolean, isEnabledButton: Boolean){
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
                .background(getColor(if(isAbsenteePage) "#DE5757" else "#7fffd4"))
                    ,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Spacer(modifier = Modifier.width(20.dp))
                PresentAbsentChoiceCard("Present", isAbsenteePage)
                PresentAbsentChoiceCard("Absent", !isAbsenteePage)
            }
            val scrollState = rememberScrollState()
            Spacer(modifier = Modifier.height(10.dp))
            Column(
                modifier = Modifier.verticalScroll(state = scrollState)
            ) {
                for(i in 1..20) {
                    MarkedStudentCard(347, "Pranav Sunil", !isAbsenteePage,isEnabledButton)
                    Spacer(modifier = Modifier.height(10.dp))
                    MarkedStudentCard(348, "Pranav Sunil", !isAbsenteePage,isEnabledButton)
                    Spacer(modifier = Modifier.height(10.dp))
                }
            }
        }
    }
}

@Composable
fun PresentAbsentChoiceCard(str: String, isTransparent: Boolean){
    Button(
        onClick = { /*TODO*/ },
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
fun MarkedStudentCard(rollNo: Int, name: String, isPresenteePage: Boolean, isEnabled: Boolean){
    Card(
        backgroundColor = BrandColor,
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
fun DefaultPreview() {
    AttendanceAppTheme {
        PresentAbsentChoiceCard("Present", false)
    }
}
