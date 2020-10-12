package com.vocalheart.retailgully_ordermanager.data.web.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class UdatePPriceResponse(
    @SerializedName("message") @Expose
    var message: String,

    @SerializedName("status")
    @Expose
    var status: Int
)