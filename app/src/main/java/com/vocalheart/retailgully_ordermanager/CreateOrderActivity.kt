package com.vocalheart.retailgully_ordermanager

import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.vocalheart.retailgully_ordermanager.data.web.model.Customer
import com.vocalheart.retailgully_ordermanager.data.web.model.Product
import com.vocalheart.retailgully_ordermanager.order.OrderListAdapter
import com.vocalheart.retailgully_ordermanager.viewmodel.CreateOrderViewModel
import kotlinx.android.synthetic.main.activity_create_order.*
import kotlinx.android.synthetic.main.appbar_layout.*
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class CreateOrderActivity : AppCompatActivity(), OrderListAdapter.AddProdcut {
    lateinit var createOrderViewModel: CreateOrderViewModel
    var customer = Customer()
    var product_id = ArrayList<String>()

    var quantity = ArrayList<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_order)
        setSupportActionBar(toolbar2)
        createOrderViewModel = ViewModelProvider(this).get(CreateOrderViewModel::class.java)
        supportActionBar?.title = "Create Order"

        var orderListAdapter = OrderListAdapter()

        recyclerview_create_order.layoutManager = LinearLayoutManager(this)
        recyclerview_create_order.adapter = orderListAdapter
        createOrderViewModel.getProduct().observe(this, Observer {
            if (it.status.equals(1)) {
                shimmer_create_order.visibility = View.GONE
                recyclerview_create_order.visibility = View.VISIBLE
                recyclerview_create_order.setItemViewCacheSize(it.products?.size ?: 0)
                it.products?.let { it1 -> orderListAdapter.swapData(it1, this) }
                orderListAdapter.notifyDataSetChanged()
            } else {
                shimmer_create_order.visibility = View.GONE
                Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
            }
        })


        btn_submit_costumer.setOnClickListener {

        }
        button_create_order.setOnClickListener {
            if (validCustomer()) {
                customer.cName = et_create_order_costumer_name.text.toString()
                customer.cMobile = et_create_order_costumer_mobile.text.toString()
                customer.address = et_create_order_costumer_address.text.toString()
                customer.bdName = et_create_order_bd_name.text.toString()
                create_loading.visibility = View.VISIBLE

                var date = getDateTime()?.substringBefore(" ")
                var localTime = getDateTime()?.substringAfter(" ")


                date?.let { it1 ->
                    localTime?.let { it2 ->
                        createOrderViewModel.createOrder(
                            customer.cName,
                            customer.cMobile,
                            customer.address,
                            customer.bdName,
                            it1,
                            it2,
                            product_id,

                            quantity
                        ).observe(this, Observer {
                            if (it.status.equals(1)) {
                                create_loading.visibility = View.GONE
                                Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                                product_id.clear()
                                 quantity.clear()
                                et_create_order_costumer_name.setText("")
                                et_create_order_costumer_mobile.setText("")
                                et_create_order_costumer_address.setText("")
                                et_create_order_bd_name.setText("")
                                orderListAdapter.notifyDataSetChanged()
                            } else {
                                create_loading.visibility = View.GONE
                                showFailDialog(it.message)
                            }
                        })
                    }
                }
            }

        }


    }

    private fun getDateTime(): String? {
        val dateFormat: DateFormat = SimpleDateFormat("yyyy/MM/dd HH:mm:ss")
        val date = Date()
        return dateFormat.format(date)
    }

    fun validCustomer(): Boolean {
        if (et_create_order_costumer_name.text.toString().isEmpty()) {


            showFailDialog("Please Enter Customer Name")
            return false
        }
        if (et_create_order_costumer_mobile.text.toString().isEmpty()) {


            showFailDialog("Please Enter Mobile Number")
            return false
        }
        if (et_create_order_costumer_mobile.text.toString().length > 11) {


            showFailDialog("Please Enter Correct Mobile Number")
            return false
        }
        if (et_create_order_costumer_mobile.text.toString().length < 9) {


            showFailDialog("Please Enter Correct Mobile Number")
            return false
        }
        if (et_create_order_costumer_address.text.toString().isEmpty()) {
            showFailDialog("Please Enter Address")
            return false
        }
        if (et_create_order_bd_name.text.toString().isEmpty()) {

            showFailDialog("Please Enter Bd Name")
            return false
        }
        if (quantity.isEmpty() or quantity.size.equals(0)) {

            showFailDialog("Please Select At lest One Product")
            return false
        }

        return true
    }

    fun showFailDialog(message: String) {
        tv_auth_fail.visibility = View.VISIBLE
        tv_auth_fail.text = message
        Handler().postDelayed(object : Runnable {
            override fun run() {
                tv_auth_fail.visibility = View.GONE
            }

        }, 2000)
    }

    override fun add(product: Product) {
        product_id.add(product.id)

        quantity.add(product.qty)


    }
}