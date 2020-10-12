package com.vocalheart.retailgully_ordermanager.data.web.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Invoice(@SerializedName("id") @Expose
                  var id: String,
              @SerializedName("order_no") @Expose
                  var order_no: String,
              @SerializedName("customer_name") @Expose
                  var customer_name: String,
              @SerializedName("mobile_no") @Expose
                  var mobile_no: String,
              @SerializedName("address") @Expose
                  var address: String,
              @SerializedName("bd_name") @Expose
                  var bd_name: String,
              @SerializedName("order_date") @Expose
                  var order_date: String,
              @SerializedName("order_time") @Expose
                  var order_time: String, @SerializedName("total_amt") @Expose
                  var total_amt: String, @SerializedName("delivery_charge") @Expose
                  var delivery_charge: String, @SerializedName("grand_total") @Expose
                  var grand_total: String,@SerializedName("total_products") @Expose
                  var total_products: String,


              @SerializedName("date_created") @Expose
                  var date_created: String)