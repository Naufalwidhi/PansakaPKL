package com.technobit.pansaka.fragment

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.technobit.pansaka.R
import com.technobit.pansaka.activity.FilteredTransaction
import kotlinx.android.synthetic.main.fragment_transaksi.*
import kotlinx.android.synthetic.main.layout_bottom_sheet.*
import java.text.SimpleDateFormat
import java.util.*

class BottomSheetFragment : BottomSheetDialogFragment() {

    private var formatDate = SimpleDateFormat("YYYYMM", Locale.US)
    private var formatMonth = SimpleDateFormat("MM", Locale.US)
    private lateinit var btndate: LinearLayout
    private lateinit var btnfilter: Button
    private lateinit var getdate: String
    private lateinit var bulan: TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.layout_bottom_sheet, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btndate = view.findViewById(R.id.btn_date)
        btnfilter = view.findViewById(R.id.btn_set_filter)
        bulan = view.findViewById(R.id.tv_month_bottom_sheet)

        btndate.setOnClickListener {
            val getDate = Calendar.getInstance()
            val currentYear: Int = getDate.get(Calendar.YEAR)
            val currentMonth: Int = getDate.get(Calendar.MONTH)
            val currentDay: Int = getDate.get(Calendar.DAY_OF_MONTH)

            val datepicker = context?.let { it1 ->
                DatePickerDialog(it1, android.R.style.Theme_Holo_Light_Dialog,
                    DatePickerDialog.OnDateSetListener { datePicker, year, month, dayOfMonth ->
                        val selectdate = Calendar.getInstance()
                        selectdate.set(Calendar.YEAR, year)
                        selectdate.set(Calendar.MONTH, month)
                        val date = formatDate.format(selectdate.time)
                        val getbulan = formatMonth.format(selectdate.time)
                        getdate = date
                        val getbulan1 = getbulan.toString()
                        if (getbulan1.equals("01")){
                            bulan.setText("Januari")
                        }
                        if (getbulan1.equals("02")){
                            bulan.setText("Februari")
                        }
                        if (getbulan1.equals("03")){
                            bulan.setText("Maret")
                        }
                        if (getbulan1.equals("04")){
                            bulan.setText("April")
                        }
                        if (getbulan1.equals("05")){
                            bulan.setText("Mei")
                        }
                        if (getbulan1.equals("06")){
                            bulan.setText("Juni")
                        }
                        if (getbulan1.equals("07")){
                            bulan.setText("Juli")
                        }
                        if (getbulan1.equals("08")){
                            bulan.setText("Agustus")
                        }
                        if (getbulan1.equals("09")){
                            bulan.setText("September")
                        }
                        if (getbulan1.equals("10")){
                            bulan.setText("Oktober")
                        }
                        if (getbulan1.equals("11")){
                            bulan.setText("November")
                        }
                        if(getbulan1.equals("12")){
                            bulan.setText("Desember")
                        }
                    },
                    currentYear, currentMonth, currentDay)
            }
            datepicker?.show()
        }

        btnfilter.setOnClickListener {
            val intent = Intent(context,FilteredTransaction::class.java)
            intent.putExtra("Date", getdate)
            startActivity(intent)
        }
    }

}