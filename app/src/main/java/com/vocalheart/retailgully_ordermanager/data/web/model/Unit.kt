package com.vocalheart.retailgully_ordermanager.data.web.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Unit(@SerializedName("id") @Expose
           var id: String,
           @SerializedName("unit_name") @Expose
           var unit_name: String,
           @SerializedName("unit_type") @Expose
           var unit_type: String,
           @SerializedName("delete_status") @Expose
           var delete_status: String,
           @SerializedName("added_by") @Expose
           var added_by: String)

