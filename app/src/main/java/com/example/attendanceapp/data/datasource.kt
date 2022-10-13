package com.example.attendanceapp.data

import com.example.attendanceapp.R
import com.example.attendanceapp.model.Record

class datasource {
    fun loadAttendance(): List<Record> {
        return listOf<Record>(
            Record(R.string.rollno,R.string.student_name,false) ,
            Record(R.string.rollno1,R.string.student_name1,false),
            Record(R.string.rollno2,R.string.student_name2,false),
            Record(R.string.rollno3,R.string.student_name3,false),
            Record(R.string.rollno4,R.string.student_name4,false),
            Record(R.string.rollno5,R.string.student_name5,false),


//R.string.rollno,R.string.student_name
//            Affirmation(R.string.affirmation2, R.drawable.image2),
//            Affirmation(R.string.affirmation3, R.drawable.image3),
//            Affirmation(R.string.affirmation4, R.drawable.image4),
//            Affirmation(R.string.affirmation5, R.drawable.image5),
//            Affirmation(R.string.affirmation6, R.drawable.image6),
//            Affirmation(R.string.affirmation7, R.drawable.image7),
//            Affirmation(R.string.affirmation8, R.drawable.image8),
//            Affirmation(R.string.affirmation9, R.drawable.image9),
//            Affirmation(R.string.affirmation10, R.drawable.image10)
             )
    }

}