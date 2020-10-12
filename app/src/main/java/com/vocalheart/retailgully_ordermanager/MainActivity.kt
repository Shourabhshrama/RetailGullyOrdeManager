package com.vocalheart.retailgully_ordermanager


import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.View

import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.gordonwong.materialsheetfab.MaterialSheetFab
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity()  {
    lateinit var materialSheetFab: MaterialSheetFab<Fab>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))


        val sheetView: View = findViewById(R.id.fab_sheet)
        val overlay: View = findViewById(R.id.overlay)
        val sheetColor = resources.getColor(R.color.wheat)
        val fabColor = resources.getColor(R.color.colorPrimary)
        val selectedcolor = resources.getColor(R.color.green)


        // Initialize material sheet FAB

        // Initialize material sheet FAB
        materialSheetFab = MaterialSheetFab<Fab>(
            fab, sheetView, overlay,
            sheetColor, fabColor
        )
        btn_create_order.setOnClickListener {
            btn_create_order.setBackgroundColor(selectedcolor)
            btn_total_order.setBackgroundColor(sheetColor)
            btn_create_invoice.setBackgroundColor(sheetColor)

            startActivity(Intent(this, CreateOrderActivity::class.java))
            overridePendingTransition(R.anim.fragment_fade_enter,R.anim.fragment_fade_exit)
            materialSheetFab.hideSheet()
        }
        btn_total_order.setOnClickListener {
            btn_total_order.setBackgroundColor(selectedcolor)
            btn_create_order.setBackgroundColor(sheetColor)
            btn_create_invoice.setBackgroundColor(sheetColor)

            startActivity(Intent(this, TotalOrderActivity::class.java))
            overridePendingTransition(R.anim.fragment_fade_enter,R.anim.fragment_fade_exit)
            materialSheetFab.hideSheet()
        }
        btn_create_invoice.setOnClickListener {
            btn_create_invoice.setBackgroundColor(selectedcolor)
            btn_total_order.setBackgroundColor(sheetColor)
            btn_create_order.setBackgroundColor(sheetColor)

            startActivity(Intent(this, CreateInvoiceActivity::class.java))
            overridePendingTransition(R.anim.fragment_fade_enter,R.anim.fragment_fade_exit)
            materialSheetFab.hideSheet()
        }

        materialSheetFab.showFab()
    }

//    override fun onCreateOptionsMenu(menu: Menu): Boolean {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        menuInflater.inflate(R.menu.menu_main, menu)
//        return true
//    }
//
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        return when (item.itemId) {
//            R.id.action_settings -> true
//            else -> super.onOptionsItemSelected(item)
//        }
//    }

    override fun onBackPressed() {
        super.onBackPressed()
        AlertDialog.Builder(this)
            .setIcon(android.R.drawable.ic_dialog_alert)
            .setTitle("Closing Application")
            .setMessage("Are you sure you want to close this Application?")
            .setPositiveButton("Yes",
                DialogInterface.OnClickListener { dialog, which -> finish() })
            .setNegativeButton("No", null)
            .show()
    }
}