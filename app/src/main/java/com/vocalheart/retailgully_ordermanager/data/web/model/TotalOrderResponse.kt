package com.vocalheart.retailgully_ordermanager.data.web.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class TotalOrderResponse(
    @SerializedName("message") @Expose
    var message: String,
    @SerializedName("order_details") @Expose
    var order_details: ArrayList<TotalOrder>?=null,
    @SerializedName("status")
    @Expose
    var status: Int
)