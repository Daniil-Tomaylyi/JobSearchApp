package com.example.testapp.presentation.all

import android.view.View
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.testapp.presentation.formatCountVacancies

@BindingAdapter("TitleVacancies")
fun TextView.setTitleVacancies(count: Int){
    count.let {
        if (count == 0) {
            visibility = View.INVISIBLE
        }
        else {
            visibility = View.VISIBLE
            text = formatCountVacancies(count)
        }
    }
}
