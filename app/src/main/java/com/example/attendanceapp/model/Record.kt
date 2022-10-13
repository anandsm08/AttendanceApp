package com.example.attendanceapp.model

import androidx.annotation.StringRes

data class Record(@StringRes val stringid: Int,@StringRes val stringResourceId: Int,val isSelected: Boolean)


//@StringRes val rollno: Int,@StringRes val stringResourceId: Int