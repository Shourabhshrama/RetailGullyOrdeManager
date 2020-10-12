package com.vocalheart.retailgully_ordermanager.data.web.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ProductResponse(
    @SerializedName("message") @Expose
    var message: String,
    @SerializedName("products") @Expose
    var products: ArrayList<Product>?=null,
    @SerializedName("status")
    @Expose
    var status: Int
)