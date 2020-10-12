package com.vocalheart.retailgully_ordermanager.ui


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.vocalheart.retailgully_ordermanager.CreateInvoiceActivity

import com.vocalheart.retailgully_ordermanager.R
import com.vocalheart.retailgully_ordermanager.data.web.model.Invoice
import kotlinx.android.synthetic.main.row_invoice.view.*
import kotlin.collections.ArrayList

class InVoiceListAdapter : RecyclerView.Adapter<InVoiceListAdapter.InVoiceViewHolder>() {

    private var data: ArrayList<Invoice> = ArrayList()
   lateinit var createInvoiceActivity: CreateInvoiceActivity
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InVoiceViewHolder {
        return InVoiceViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.row_invoice, parent, false)
        )
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: InVoiceViewHolder, position: Int) =
        holder.bind(data[position],position)

    fun swapData(data: ArrayList<Invoice>, createInvoiceActivity: CreateInvoiceActivity) {
        this.data = data
        this.createInvoiceActivity=createInvoiceActivity
        notifyDataSetChanged()
    }

  inner  class InVoiceViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: Invoice, position: Int) = with(itemView) {
            textView3.text = "${(position+1)}   ${item.customer_name}       "
            textView4.text = "${item.total_products}"
            setOnClickListener {
                createInvoiceActivity.change(item)
            }
        }
    }
    interface InvoiceDetail{
          fun change(invoice: Invoice)
    }
}
