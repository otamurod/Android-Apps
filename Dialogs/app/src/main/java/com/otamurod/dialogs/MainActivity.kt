package com.otamurod.dialogs

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.TimePicker
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.snackbar.Snackbar
import com.otamurod.dialogs.adapters.SnackbarAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.custom_dialog_view.view.*
import java.sql.Time

class MainActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.N)

    lateinit var snackbarAdapter: SnackbarAdapter

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val myArray = arrayOf("Java", "Kotlin", "Python", "Javascript")
        val dialogBuilder = AlertDialog.Builder(this)
        val dialog = dialogBuilder.create()

        btn1.setOnClickListener {
            dialogBuilder.setTitle("Title")
            dialogBuilder.setMessage("Message")

            dialogBuilder.setPositiveButton("Positive") { dialog, which ->
                Toast.makeText(this@MainActivity, "Positive", Toast.LENGTH_SHORT).show()
            }
            dialogBuilder.setNegativeButton("Negative") { dialog, which ->
                Toast.makeText(this@MainActivity, "Negative", Toast.LENGTH_SHORT).show()
            }
            dialogBuilder.setNeutralButton("Neutral") { dialog, which ->
                Toast.makeText(this@MainActivity, "Neutral", Toast.LENGTH_SHORT).show()
            }
            dialogBuilder.setMultiChoiceItems(
                myArray,
                BooleanArray(myArray.size)
            ) { dialog, which, p2 ->
                Toast.makeText(this@MainActivity, "${myArray[which]}", Toast.LENGTH_SHORT).show()
            }

            dialogBuilder.setSingleChoiceItems(myArray, -1) { dialog, which ->
                Toast.makeText(this@MainActivity, "${myArray[which]}", Toast.LENGTH_SHORT).show()
                dialog.dismiss()
            }
            dialogBuilder.show()
        }

        btn2.setOnClickListener {

            val dialogView = layoutInflater.inflate(R.layout.custom_dialog_view, null, false)
            dialog.setView(dialogView)
            dialogView.retry.setOnClickListener {
                dialog.dismiss()
            }

            dialog.show()
        }

        btn3.setOnClickListener {

            val beginTransaction = supportFragmentManager.beginTransaction()

            val fragmentDialog = FragmentDialog.newInstance("Dialog1", "Dialog2")
            fragmentDialog.show(beginTransaction, "dialog1")

        }

        btn4.setOnClickListener {
            val datePickerDialog = DatePickerDialog(this)

            datePickerDialog.setOnDateSetListener { view, year, month, dayOfMonth ->
                Toast.makeText(this, "$dayOfMonth.${month + 1}.$year", Toast.LENGTH_SHORT).show()
            }
            datePickerDialog.show()
        }

        btn5.setOnClickListener {
            val timePickerDialog = TimePickerDialog(this, object :TimePickerDialog.OnTimeSetListener{
                override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
                    Toast.makeText(this@MainActivity, "$hourOfDay:$minute", Toast.LENGTH_SHORT).show()
                }

            }, 24, 60, true)

            timePickerDialog.updateTime(12, 30)

            timePickerDialog.show()
        }

        btn6.setOnClickListener {
            val bottomSheetDialog = BottomSheetDialog(this)
            val bottomView = layoutInflater.inflate(R.layout.bottomsheet_dialog_view, null, false)

            bottomSheetDialog.setContentView(bottomView)
            bottomSheetDialog.show()
        }

        val list = mutableListOf("Java", "Kotlin", "Python", "Javascript")

        btn7.setOnClickListener {

            snackbarAdapter = SnackbarAdapter(list, object :SnackbarAdapter.OnItemClickListener{
                override fun onItemClick(string: String, position: Int) {
                    list.removeAt(position)
                    snackbarAdapter.notifyItemRemoved(position)
                    snackbarAdapter.notifyItemRangeChanged(position, list.size)

                    val snackbar = Snackbar.make(it, "Deleted", Snackbar.LENGTH_LONG)
                    snackbar.setAction("Undo", object : View.OnClickListener{
                        override fun onClick(p0: View?) {
                            Toast.makeText(this@MainActivity, "Undone", Toast.LENGTH_SHORT).show()
                            list.add(position, string)

                            snackbarAdapter.notifyItemInserted(position)
                            snackbarAdapter.notifyItemRangeChanged(position, list.size)
                        }
                    })
                    snackbar.show()
                }
            })

            rv.adapter = snackbarAdapter

        }

    }

}