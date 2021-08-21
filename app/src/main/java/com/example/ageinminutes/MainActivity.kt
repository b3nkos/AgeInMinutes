package com.example.ageinminutes

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        datePickerButton.setOnClickListener { view ->
            clickDatePicker(view)
        }
    }

    private fun clickDatePicker(view: View) {
        val calendar = Calendar.getInstance()

        val onDataSetListener = { view: View, selectedYear: Int, selectedMonth: Int,
                                  selectedDayOfMonth: Int ->

            val selectedDate = "$selectedDayOfMonth/${selectedMonth + 1}/$selectedYear"
            textViewSelectedDate.text = selectedDate

            val simpleDateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
            val selectedDateParsed = simpleDateFormat.parse(selectedDate)

            val selectedDateInMinutes = selectedDateParsed!!.time / 60000
            val currentDate =
                simpleDateFormat.parse(simpleDateFormat.format(System.currentTimeMillis()))
            val currentDateToMinutes = currentDate!!.time / 60000
            val differenceInMinutes = currentDateToMinutes - selectedDateInMinutes
            val differenceInDays = differenceInMinutes / 1440

            textViewSelectedDateInMinutes.text = differenceInMinutes.toString()
            textViewSelectedDateInDays.text = differenceInDays.toString()
        }

        val datePickerDialog = DatePickerDialog(
            this,
            DatePickerDialog.OnDateSetListener(onDataSetListener),
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )

        datePickerDialog.datePicker.maxDate = Date().time - 86400000
        datePickerDialog.show()
    }
}