package com.technobit.pansaka.fragment

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.technobit.pansaka.R
import com.technobit.pansaka.activity.FilteredTransaction
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class BottomSheetFragment : BottomSheetDialogFragment() {

    private var formatDate = SimpleDateFormat("yyyy-MM-dd", Locale.US)
    private lateinit var btnstartdate: LinearLayout
    private lateinit var btnenddate: LinearLayout
    private lateinit var btnfilter: Button
    private lateinit var get_start_date: String
    private lateinit var get_end_date: String
    private lateinit var bulan: TextView
    private lateinit var bulan2: TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.layout_bottom_sheet, container, false)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnstartdate = view.findViewById(R.id.btn_start_date)
        btnenddate = view.findViewById(R.id.btn_end_date)
        btnfilter = view.findViewById(R.id.btn_set_filter)
        bulan = view.findViewById(R.id.tv_month_start_bottom_sheet)
        bulan2 = view.findViewById(R.id.tv_month_end_bottom_sheet)

        val current = LocalDateTime.now()
        val formartter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        val formatted = current.format(formartter)

        bulan.setText(formatted)
        bulan2.setText(formatted)
        get_start_date = ""
        get_end_date = ""
        btnstartdate.setOnClickListener {
            startdate()
        }
        btnenddate.setOnClickListener {
            enddate()
        }
        btnfilter.setOnClickListener {
            date()
        }
    }

    private fun startdate() {
        val getDate = Calendar.getInstance()
        val currentYear: Int = getDate.get(Calendar.YEAR)
        val currentMonth: Int = getDate.get(Calendar.MONTH)
        val currentDay: Int = getDate.get(Calendar.DAY_OF_MONTH)

        val datepicker = context?.let { it1 ->
            DatePickerDialog(
                it1, android.R.style.Theme_DeviceDefault_Dialog,
                DatePickerDialog.OnDateSetListener { datePicker, year, month, day ->
                    val selectdate = Calendar.getInstance()
                    selectdate.set(Calendar.YEAR, year)
                    selectdate.set(Calendar.MONTH, month)
                    selectdate.set(Calendar.DAY_OF_MONTH, day)
                    val date = formatDate.format(selectdate.time)
                    get_start_date = date
                    bulan.setText(date)
                },
                currentYear, currentMonth, currentDay
            )
        }
        datepicker?.show()
    }

    private fun enddate() {
        val getDate2 = Calendar.getInstance()
        val currentYear2: Int = getDate2.get(Calendar.YEAR)
        val currentMonth2: Int = getDate2.get(Calendar.MONTH)
        val currentDay2: Int = getDate2.get(Calendar.DAY_OF_MONTH)

        val datepicker2 = context?.let { it1 ->
            DatePickerDialog(
                it1, android.R.style.Theme_DeviceDefault_Dialog,
                DatePickerDialog.OnDateSetListener { datePicker2, year, month, day ->
                    val selectdate2 = Calendar.getInstance()
                    selectdate2.set(Calendar.YEAR, year)
                    selectdate2.set(Calendar.MONTH, month)
                    selectdate2.set(Calendar.DAY_OF_MONTH, day)
                    val date2 = formatDate.format(selectdate2.time)
                    get_end_date = date2
                    bulan2.setText(date2)
                },
                currentYear2, currentMonth2, currentDay2
            )
        }
        datepicker2?.show()
    }

    private fun date() {
        if (get_start_date.isEmpty() || get_end_date.isEmpty()) {
            Toast.makeText(context, "Mohon isi tanggal awal dan akhir", Toast.LENGTH_SHORT)
                .show()
        } else {
            val tanggal = get_start_date.split("-").toTypedArray()
            val tanggal2 = get_end_date.split("-").toTypedArray()

            val tahuncvrt = tanggal[0]
            val tahuncvrt2 = tanggal2[0]

            val bulancvrt = tanggal[1]
            val bulancvrt2 = tanggal2[1]

            val haricvrt = tanggal[2]
            val haricvrt2 = tanggal2[2]


            if (tahuncvrt <= tahuncvrt2) {
                if (bulancvrt <= bulancvrt2) {
                    if (haricvrt <= haricvrt2) {
                        val intent = Intent(context, FilteredTransaction::class.java)
                        intent.putExtra("startDate", get_start_date)
                        intent.putExtra("endDate", get_end_date)
                        startActivity(intent)
                    } else {
                        Toast.makeText(context, "Format tanggal salah", Toast.LENGTH_SHORT)
                            .show()
                    }
                } else {
                    Toast.makeText(context, "Format tanggal salah", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(context, "Mohon isi tanggal awal dan akhir", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }
}