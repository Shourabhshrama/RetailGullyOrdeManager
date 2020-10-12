package com.vocalheart.retailgully_ordermanager.data.web.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class InvoiceDetail(
    @SerializedName("id") @Expose
    var id: String,
    @SerializedName("order_id") @Expose
    var order_id: String,
    @SerializedName("product_id") @Expose
    var product_id: String,
    @SerializedName("quantity") @Expose
    var quantity: String,
    @SerializedName("purchase_qty") @Expose
    var purchase_qty: String,

    @SerializedName("amount") @Expose
    var amount: String,
    @SerializedName("purchase_price") @Expose
    var purchase_price: String,
    @SerializedName("product_name") @Expose
    var product_name: String, @SerializedName("unit_name") @Expose
    var unit_name: String,
    var sellingrate: String,

    @SerializedName("date_created") @Expose
    var date_created: String
)