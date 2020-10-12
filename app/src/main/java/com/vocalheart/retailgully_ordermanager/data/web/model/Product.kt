package com.vocalheart.retailgully_ordermanager.data.web.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Product(
    @SerializedName("id") @Expose
    var id: String,
    @SerializedName("product_name") @Expose
    var product_name: String,
    @SerializedName("unit_id") @Expose
    var unit_id: String,
    @SerializedName("unit_name") @Expose
    var unit_name: String,
    @SerializedName("date_created") @Expose
    var date_created: String,
    var qty: String,

    @SerializedName("added_by") @Expose
    var added_by: String
)