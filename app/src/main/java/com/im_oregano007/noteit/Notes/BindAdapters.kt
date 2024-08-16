package com.im_oregano007.noteit.Notes

import android.widget.TextView
import androidx.databinding.BindingAdapter
import java.text.SimpleDateFormat
import java.util.Date

@BindingAdapter("app:AddDate")
fun TextView.AddDate(date: Date){
    val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
    val formattedDate = dateFormat.format(date)
    this.text = formattedDate
}