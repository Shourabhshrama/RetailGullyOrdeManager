package com.vocalheart.retailgully_ordermanager.ui


import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.vocalheart.retailgully_ordermanager.R
import com.vocalheart.retailgully_ordermanager.data.web.model.InvoiceDetail
import kotlinx.android.synthetic.main.row_invoice_detail.view.*
import java.util.*

class InvoiceDetailAdapter : RecyclerView.Adapter<InvoiceDetailAdapter.InvoiceDetailViewModel>() {

    private var data: List<InvoiceDetail> = ArrayList()
    lateinit var invoiceDetailActivity: InvoiceDetailActivity
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InvoiceDetailViewModel {
        return InvoiceDetailViewModel(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.row_invoice_detail, parent, false)
        )
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: InvoiceDetailViewModel, position: Int) =
        holder.bind(data[position], position)

    fun swapData(data: List<InvoiceDetail>, invoiceDetailActivity: InvoiceDetailActivity) {
        this.data = data
        this.invoiceDetailActivity = invoiceDetailActivity
        notifyDataSetChanged()
    }

    inner class InvoiceDetailViewModel(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: InvoiceDetail, position: Int) = with(itemView) {
            invoice_sr.text = "${(position + 1)}"
            invoice_pname.text = "${item.product_name}"
            invoice_punit.text = "${item.unit_name}"
            invoice_pqty.setText(item.quantity)

            invoice_pru.text = "${item.purchase_price}"
            invoice_prate.setText(item.sellingrate)
            invoice_amt.text = "${item.amount}"
            item.sellingrate=item.purchase_price
            invoice_prate.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    item.sellingrate = invoice_prate.text.toString()
                    if (invoice_prate.text.toString()
                            .isNotEmpty() and invoice_pqty.text.toString().isNotEmpty()
                    ) {
                        invoice_amt.setText("${(item.sellingrate.toInt() * item.quantity.toInt())}")
                        item.amount = invoice_amt.text.toString()
                    } else {
                    }
                }

                override fun afterTextChanged(p0: Editable?) {

                }

            })
            invoice_pqty.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    item.quantity = invoice_pqty.text.toString()
                    if (item.sellingrate.isNotEmpty() and item.quantity.isNotEmpty()) {
                        invoice_amt.setText("${(item.sellingrate.toInt() * item.quantity.toInt())}")

                        item.amount = invoice_amt.text.toString()
                    } else {
                    }

                }

                override fun afterTextChanged(p0: Editable?) {

                }

            })
            btn_invoice_update.setOnClickListener {
                btn_invoice_update.setBackgroundColor(R.color.green)
                invoiceDetailActivity.update(item)
            }
        }
    }

    interface UpdateSellingPrice {
        fun update(item: InvoiceDetail)
    }
}
