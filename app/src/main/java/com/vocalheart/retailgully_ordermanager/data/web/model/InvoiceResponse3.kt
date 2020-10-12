package com.vocalheart.retailgully_ordermanager.data.web.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class InvoiceResponse3(
    @SerializedName("message") @Expose
    var message: String,
    @SerializedName("invoice_details") @Expose
    var invoices: ArrayList<InvoiceDetail>?=null,
    @SerializedName("status")
    @Expose
    var status: Int
)