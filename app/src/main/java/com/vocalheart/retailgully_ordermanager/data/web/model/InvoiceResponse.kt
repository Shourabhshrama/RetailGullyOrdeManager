package com.vocalheart.retailgully_ordermanager.data.web.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class InvoiceResponse(
    @SerializedName("message") @Expose
    var message: String,
    @SerializedName("invoices") @Expose
    var invoices: ArrayList<Invoice>?=null,
    @SerializedName("status")
    @Expose
    var status: Int
)