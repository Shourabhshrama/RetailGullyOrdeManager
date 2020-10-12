package com.vocalheart.retailgully_ordermanager.order


import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.vocalheart.retailgully_ordermanager.CreateOrderActivity
import com.vocalheart.retailgully_ordermanager.R


import com.vocalheart.retailgully_ordermanager.data.web.model.Product
import kotlinx.android.synthetic.main.row_create_order.view.*


class OrderListAdapter : RecyclerView.Adapter<OrderListAdapter.OrderListViewHolder>() {

    private var data: ArrayList<Product> = ArrayList()
    lateinit var createOrderActivity: CreateOrderActivity
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderListViewHolder {
        return OrderListViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.row_create_order, parent, false)
        )
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: OrderListViewHolder, position: Int) =
        holder.bind(data[position], position)

    fun swapData(data: ArrayList<Product>, createOrderActivity: CreateOrderActivity) {
        this.data = data
        this.createOrderActivity = createOrderActivity
        notifyDataSetChanged()
    }

    inner class OrderListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: Product, position: Int) = with(itemView) {
            var unitId = ""
            var qty = ""
            row_createprder_sr.text = "${(position + 1)} "
            row_createprder_name.text = "${(item.product_name)} "
            row_createprder_sp_unit.text = item.unit_name

            row_createprder_qty.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(s: Editable?) {

                }

                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {

                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    if (row_createprder_qty.text.toString().isNotEmpty()) {
                        qty = row_createprder_qty.text.toString()
                    }
                }
            })
            row_createprder_add.setOnClickListener {
                if ( qty.isNotEmpty()) {


                    item.qty = qty
                    createOrderActivity.add(item)
                    row_createprder_add.setBackgroundColor(resources.getColor(R.color.green))
                    row_createprder_add.text = "Added"
                } else {

                    if (qty.isEmpty()) {
                        Toast.makeText(itemView.context, "Please Enter Qty", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            }
        }
    }

    interface AddProdcut {
        fun add(product: Product)
    }
}
