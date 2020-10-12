package com.vocalheart.retailgully_ordermanager.ui

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.vocalheart.retailgully_ordermanager.R
import com.vocalheart.retailgully_ordermanager.data.web.model.InvoiceDetail
import com.vocalheart.retailgully_ordermanager.viewmodel.CreateOrderViewModel
import kotlinx.android.synthetic.main.activity_invoice_detail.*
import kotlinx.android.synthetic.main.appbar_layout.*
import kotlinx.android.synthetic.main.selling_price_dialog.*
import kotlinx.android.synthetic.main.selling_price_dialog.view.*


class InvoiceDetailActivity : AppCompatActivity(), InvoiceDetailAdapter.UpdateSellingPrice {
    lateinit var viewModel: CreateOrderViewModel
    var adapter = InvoiceDetailAdapter()
    var productId = ArrayList<String>()
    var qty = ArrayList<String>()
    var rate = ArrayList<String>()
    var total = ArrayList<String>()
    var totalAmount = 0
    var orderid = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_invoice_detail)
        viewModel = ViewModelProvider(this).get(CreateOrderViewModel::class.java)
        setSupportActionBar(toolbar2)
        supportActionBar?.title = intent.getStringExtra("order_user")
        recyclerview_invoice_detail.layoutManager = LinearLayoutManager(this)
        recyclerview_invoice_detail.adapter = adapter
        orderid = intent.getStringExtra("order_id")

        viewModel.getInvoiceDetail(orderid).observe(this, Observer {
            if (it.status.equals(1)) {
                invocedetaile_loading.visibility = View.GONE
                it.invoices?.let { it1 -> adapter.swapData(it1, this) }
                adapter.notifyDataSetChanged()
            } else {
                Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                invocedetaile_loading.visibility = View.GONE
            }
        })

        btn_invoice_print.setOnClickListener {
            var intent = Intent(this, PrintActivity::class.java)
            intent.putExtra("order_id", orderid)

            startActivity(intent)
            overridePendingTransition(R.anim.fragment_fade_enter, R.anim.fragment_fade_exit)
        }

        btn_update_selling_price.setOnClickListener {
            if (productId.isNotEmpty()) {
                updateRateDialog()
            } else {
                Toast.makeText(this, "Add At lease One Product", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun update(item: InvoiceDetail) {
        productId.add(item.product_id)
        qty.add(item.quantity)
        rate.add(item.sellingrate)
        total.add(item.amount)
    }


    fun updateRateDialog() {
        val dialogBuilder: AlertDialog = AlertDialog.Builder(this).create()
        val inflater = this.layoutInflater
        val dialogView: View = inflater.inflate(R.layout.selling_price_dialog, null)
        for (item in total) {
            if (item.isNotEmpty()) {
                totalAmount += item.toInt()
            }
        }
        dialogView.et_purchase_rate.setText("" + totalAmount)
        dialogView.et_deleivercharge.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (dialogView.et_deleivercharge.text.toString().isNotEmpty()) {
                    dialogView.et_purchase_total.text =
                        "${(totalAmount + dialogView.et_deleivercharge.text.toString().toInt())}"
                }
            }

            override fun afterTextChanged(p0: Editable?) {

            }

        })


        dialogView.btn_selling_update.setOnClickListener(View.OnClickListener {

            dialogView.  selling_price_loader.visibility = View.VISIBLE
            viewModel.updateSellingPrice(
                orderid,
                productId,
                qty,
                rate,
                total,
                "" + totalAmount,
                dialogView.et_deleivercharge.text.toString(),
                dialogView.et_purchase_total.text.toString()
            ).observe(this, Observer {
                if (it.status.equals(1)) {
                    dialogView.selling_price_loader.visibility = View.GONE
                    Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                    dialogBuilder.dismiss()
                    totalAmount=0
                } else {
                    Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                    dialogView. selling_price_loader.visibility = View.GONE
                    dialogBuilder.dismiss()
                    totalAmount=0
                }
            })


        })
        dialogView.tv_cancel2.setOnClickListener(View.OnClickListener { // DO SOMETHINGS
            totalAmount=0
            dialogBuilder.dismiss()
        })

        dialogBuilder.setView(dialogView)
        dialogBuilder.show()

    }
}