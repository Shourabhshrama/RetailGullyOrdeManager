package com.vocalheart.retailgully_ordermanager

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.vocalheart.retailgully_ordermanager.data.web.model.Invoice
import com.vocalheart.retailgully_ordermanager.ui.InVoiceListAdapter
import com.vocalheart.retailgully_ordermanager.ui.InvoiceDetailActivity
import com.vocalheart.retailgully_ordermanager.viewmodel.CreateOrderViewModel
import kotlinx.android.synthetic.main.activity_create_invoice.*
import kotlinx.android.synthetic.main.appbar_layout.*

class CreateInvoiceActivity : AppCompatActivity(),InVoiceListAdapter.InvoiceDetail {
    lateinit var viewModel: CreateOrderViewModel
    var adapter = InVoiceListAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_invoice)
        setSupportActionBar(toolbar2)
        supportActionBar?.title = "Create Invoice"
        viewModel = ViewModelProvider(this).get(CreateOrderViewModel::class.java)
        recyckerview_invoice.layoutManager = LinearLayoutManager(this)
        recyckerview_invoice.adapter = adapter
        viewModel.getInvoice().observe(this, Observer {
            if (it.status.equals(1)) {
                invoice_loading.visibility = View.GONE
                recyckerview_invoice.visibility = View.VISIBLE
                it.invoices?.let { it1 -> adapter.swapData(it1,this) }
            } else {
                invoice_loading.visibility = View.GONE
                Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
            }
        })
    }

    override fun change(invoice: Invoice) {
        var intent=Intent(this, InvoiceDetailActivity::class.java)
        intent.putExtra("order_id",invoice.id)
        intent.putExtra("order_user",invoice.customer_name)
        startActivity(intent )
        overridePendingTransition(R.anim.fragment_fade_enter,R.anim.fragment_fade_exit)
    }
}