package com.vocalheart.retailgully_ordermanager.data.web.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class PrintResponse(
    @SerializedName("message") @Expose
    var message: String,
    @SerializedName("invoice_view") @Expose
    var invoice_view: String,
    @SerializedName("status")
    @Expose
    var status: Int
)