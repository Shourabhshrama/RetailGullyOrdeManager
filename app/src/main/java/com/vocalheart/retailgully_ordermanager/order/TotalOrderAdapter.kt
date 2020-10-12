package com.vocalheart.retailgully_ordermanager.order


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.vocalheart.retailgully_ordermanager.R
import com.vocalheart.retailgully_ordermanager.TotalOrderActivity

import com.vocalheart.retailgully_ordermanager.data.web.model.TotalOrder
import kotlinx.android.synthetic.main.row_total_order.view.*
import kotlin.collections.ArrayList

class TotalOrderAdapter : RecyclerView.Adapter<TotalOrderAdapter.TotalViewModel>() {

    private var data: ArrayList<TotalOrder> = ArrayList()
    lateinit var activity: TotalOrderActivity
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TotalViewModel {
        return TotalViewModel(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.row_total_order, parent, false)
        )
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: TotalViewModel, position: Int) =
        holder.bind(data[position], position)

    fun swapData(data: ArrayList<TotalOrder>, totalOrderActivity: TotalOrderActivity) {
        this.data = data
        this.activity = totalOrderActivity
        notifyDataSetChanged()
    }

    inner class TotalViewModel(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: TotalOrder, position: Int) = with(itemView) {
            total_order_sr.text = "${(position + 1)}"
            total_order_pname.text = "${(item.product_name)}"
            total_order_punit.text = "${(item.unit_name)}"
            total_order_pqty.text = "${(item.total_quantity)}"
            total_order_pru.text = "${(item.purchase_price)}"
            total_order_prate.text = "${(item.purchase_qty)}"

            if (item.amount.equals("0")) {
                if (item.purchase_price.isNotEmpty() or item.total_quantity.isNotEmpty()) {
                    total_order_amt.text =
                        "${((item.purchase_price.toInt()) * item.total_quantity.toInt())}"
                }
            } else {
                total_order_amt.text = "${(item.amount)}"
            }



            btn_update.setOnClickListener {
                activity.update(item, position)
            }

        }
    }

    interface UpdatePPice {
        fun update(totalOrder: TotalOrder, position: Int)
    }
}
