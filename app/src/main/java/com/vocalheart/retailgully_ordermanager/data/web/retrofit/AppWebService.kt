package com.maverick.e_nirikshan.data.web


import com.vocalheart.retailgully_ordermanager.data.web.model.*

import io.reactivex.Single


import retrofit2.http.*

interface AppWebService {

    // survey type 1,2 direct,auto, 3 self

    @GET("get_products")
    fun getProducts(

    ): Single<ProductResponse>

    @GET("get_total_order")
    fun getotalOrder(

    ): Single<TotalOrderResponse>

    @GET("get_invoice_list")
    fun getInvoce(

    ): Single<InvoiceResponse>


    @POST("get_invoice_details")
    @FormUrlEncoded
    fun InvoiceDetail(
        @Field("order_id") order_id: String
    ): Single<InvoiceResponse3>

    @POST("invoice_view")
    @FormUrlEncoded
    fun InvoicePrint(
        @Field("order_id") order_id: String
    ): Single<PrintResponse>

    @POST("create_order")
    @FormUrlEncoded
    fun createOrder(
        @Field("customer_name") customer_name: String,
        @Field("mobile_no") mobile_no: String,
        @Field("address") address: String,
        @Field("bd_name") bd_name: String,
        @Field("order_date") order_date: String,
        @Field("order_time") order_time: String,
        @Field("product_id[]") product_id: ArrayList<String>,

        @Field("quantity[]") quantity: ArrayList<String>


    ): Single<CreateOrderResponse>

    @POST("update_product_selling_price")
    @FormUrlEncoded
    fun updateSellingPrice(
        @Field("order_id") order_id: String,
        @Field("product_id[]") product_id: ArrayList<String>,
        @Field("quantity[]") quantity: ArrayList<String>,
        @Field("rate[]") rate: ArrayList<String>,
        @Field("total[]") total: ArrayList<String>,
        @Field("total_amount") total_amount: String,
        @Field("delivery_charge") delivery_charge: String,
        @Field("grand_total") grand_total: String,


    ): Single<CreateOrderResponse>


    @POST("update_product_purchase_rate")
    @FormUrlEncoded
    fun updateProductPurchaseRate(
        @Field("product_id") productt_id: String,
        @Field("purchase_rate") purchase_rate: String,
        @Field("purchase_qty") purchase_qty: String,
        @Field("total") total: String


    ): Single<UdatePPriceResponse>


}