package com.technobit.pansaka.fragment

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.technobit.pansaka.R
import com.technobit.pansaka.activity.FilteredTransaction
import kotlinx.android.synthetic.main.fragment_transaksi.*
import kotlinx.android.synthetic.main.layout_bottom_sheet.*
import java.text.SimpleDateFormat
import java.util.*

class BottomSheetFragment : BottomSheetDialogFragment() {

    private var formatDate = SimpleDateFormat("YYYY MM DD", Locale.US)
    private lateinit var btndate: LinearLayout
    private lateinit var btnfilter: Button
    private lateinit var getdate: String

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
                        getdate = date
                    },
                    currentYear, currentMonth, currentDay)
            }
            datepicker?.show()
        }

        btnfilter.setOnClickListener {
            val intent = Intent(context,FilteredTransaction::class.java)
            startActivity(intent)
        }
    }

}