package com.vocalheart.retailgully_ordermanager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.vocalheart.retailgully_ordermanager.data.web.model.TotalOrder
import com.vocalheart.retailgully_ordermanager.order.TotalOrderAdapter
import com.vocalheart.retailgully_ordermanager.viewmodel.CreateOrderViewModel
import kotlinx.android.synthetic.main.activity_total_order.*

import kotlinx.android.synthetic.main.appbar_layout.*
import kotlinx.android.synthetic.main.bottom_sheet_logout.view.*

class TotalOrderActivity : AppCompatActivity(), TotalOrderAdapter.UpdatePPice {
    lateinit var viewModel: CreateOrderViewModel
    var totalOrderAdapter = TotalOrderAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_total_order)
        setSupportActionBar(toolbar2)
        supportActionBar?.title = "Total Order"
        viewModel = ViewModelProvider(this).get(CreateOrderViewModel::class.java)

        recyclerview_total_order.layoutManager = LinearLayoutManager(this)
        recyclerview_total_order.adapter = totalOrderAdapter
        viewModel.getOrder().observe(this, Observer {
            if (it.status.equals(1)) {
                total_loading.visibility = View.GONE
                it.order_details?.let { it1 -> totalOrderAdapter.swapData(it1, this) }
                totalOrderAdapter.notifyDataSetChanged()
            } else {
                total_loading.visibility = View.GONE
                Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
            }
        })


//        totalOrderAdapter.swapData(list)
//        totalOrderAdapter.notifyDataSetChanged()
    }

    override fun update(totalOrder: TotalOrder, position: Int) {

        val mBottomSheetDialog = BottomSheetDialog(this)
        val sheetView = layoutInflater.inflate(R.layout.bottom_sheet_logout, null)
        mBottomSheetDialog.setContentView(sheetView)
        mBottomSheetDialog.show()
        val btn_logout = sheetView.btn_update
        val tv_cancel = sheetView.tv_cancel
        sheetView.et_purchase_rate.setText(totalOrder.purchase_price)
        sheetView.et_purchase_qty.setText(totalOrder.purchase_qty)
        btn_logout.setOnClickListener {
            sheetView.updatepurchase_loading.visibility = View.VISIBLE
            viewModel.UpdatePPriceResponse(
                totalOrder.product_id,
                sheetView.et_purchase_rate.text.toString(),
                "${sheetView.et_purchase_rate.text.toString().toInt() * sheetView.et_purchase_qty.text.toString().toInt()}",
                sheetView.et_purchase_qty.text.toString()
            ).observe(this, Observer {
                if (it.status.equals(1)) {
                    sheetView.updatepurchase_loading.visibility = View.GONE
                    totalOrder.purchase_price = sheetView.et_purchase_rate.text.toString()
                    totalOrder.purchase_qty = sheetView.et_purchase_qty.text.toString()
                    totalOrder.amount =
                        "${(totalOrder.purchase_price.toInt() * totalOrder.purchase_qty.toInt())}"
                    Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                    totalOrderAdapter.notifyItemChanged(position)
                    mBottomSheetDialog.dismiss()
                } else {
                    sheetView.updatepurchase_loading.visibility = View.GONE
                    Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()

                }
            })
        }


        tv_cancel.setOnClickListener {
            mBottomSheetDialog.dismiss()
        }


    }
}