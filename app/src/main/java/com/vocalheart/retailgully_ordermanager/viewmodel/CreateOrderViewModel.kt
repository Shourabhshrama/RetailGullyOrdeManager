package com.vocalheart.retailgully_ordermanager.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.maverick.e_nirikshan.data.web.RetrofitClient
import com.vocalheart.retailgully_ordermanager.data.web.model.*

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import retrofit2.http.Field

class CreateOrderViewModel : ViewModel() {

    fun getProduct(): LiveData<ProductResponse> {

        var data = MutableLiveData<ProductResponse>()
        RetrofitClient
            .Factory.create().getProducts()
            .cache()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribeWith(object : DisposableSingleObserver<ProductResponse>() {
                override fun onSuccess(t: ProductResponse) {
                    data.value = t
                }

                override fun onError(e: Throwable) {
                    var response = ProductResponse("No Data Found", null, 2)
                    data.value = response
                }

            })
        return data

    }


    fun getOrder(): LiveData<TotalOrderResponse> {

        var data = MutableLiveData<TotalOrderResponse>()
        RetrofitClient
            .Factory.create().getotalOrder()
            .cache()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribeWith(object : DisposableSingleObserver<TotalOrderResponse>() {
                override fun onSuccess(t: TotalOrderResponse) {
                    data.value = t
                }

                override fun onError(e: Throwable) {
                    var response = TotalOrderResponse("No Data Found", null, 2)
                    data.value = response
                }

            })
        return data

    }


    fun getInvoice(): LiveData<InvoiceResponse> {

        var data = MutableLiveData<InvoiceResponse>()
        RetrofitClient
            .Factory.create().getInvoce()
            .cache()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribeWith(object : DisposableSingleObserver<InvoiceResponse>() {
                override fun onSuccess(t: InvoiceResponse) {
                    data.value = t
                }

                override fun onError(e: Throwable) {
                    var response = InvoiceResponse("No Data Found", null, 2)
                    data.value = response
                }

            })
        return data

    }

    fun getInvoiceDetail(orderID: String): LiveData<InvoiceResponse3> {

        var data = MutableLiveData<InvoiceResponse3>()
        RetrofitClient
            .Factory.create().InvoiceDetail(orderID)
            .cache()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribeWith(object : DisposableSingleObserver<InvoiceResponse3>() {
                override fun onSuccess(t: InvoiceResponse3) {
                    data.value = t
                }

                override fun onError(e: Throwable) {
                    var response = InvoiceResponse3("No Data Found", null, 2)
                    data.value = response
                }

            })
        return data

    }

    fun updateSellingPrice(
        orderID: String,
        product_id: ArrayList<String>,
        quantity: ArrayList<String>,
        rate: ArrayList<String>,
        total: ArrayList<String>,
        total_amount: String,
        delivery_charge: String,
        grand_total: String
    ): LiveData<CreateOrderResponse> {

        var data = MutableLiveData<CreateOrderResponse>()
        RetrofitClient
            .Factory.create().updateSellingPrice(
                orderID,
                product_id,
                quantity,
                rate,
                total,
                total_amount,
                delivery_charge,
                grand_total
            )
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribeWith(object : DisposableSingleObserver<CreateOrderResponse>() {
                override fun onSuccess(t: CreateOrderResponse) {
                    data.value = t
                }

                override fun onError(e: Throwable) {
                    var response = CreateOrderResponse("No Data Found", 2)
                    data.value = response
                }

            })
        return data

    }

    fun getInvoicePrint(orderID: String): LiveData<PrintResponse> {

        var data = MutableLiveData<PrintResponse>()
        RetrofitClient
            .Factory.create().InvoicePrint(orderID)
            .cache()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribeWith(object : DisposableSingleObserver<PrintResponse>() {
                override fun onSuccess(t: PrintResponse) {
                    data.value = t
                }

                override fun onError(e: Throwable) {
                    var response = PrintResponse("No Data Found", "", 2)
                    data.value = response
                }

            })
        return data

    }

    fun createOrder(
        customer_name: String,
        mobile_no: String,
        address: String,
        bd_name: String,
        order_date: String,
        order_time: String,
        product_id: ArrayList<String>,

        quantity: ArrayList<String>
    ): LiveData<CreateOrderResponse> {

        var data = MutableLiveData<CreateOrderResponse>()
        RetrofitClient
            .Factory.create().createOrder(
                customer_name, mobile_no, address, bd_name,
                order_date, order_time, product_id, quantity
            )

            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribeWith(object : DisposableSingleObserver<CreateOrderResponse>() {
                override fun onSuccess(t: CreateOrderResponse) {
                    data.value = t
                }

                override fun onError(e: Throwable) {
                    var response = CreateOrderResponse("No Data Found", 2)
                    data.value = response
                }

            })
        return data

    }

    fun UpdatePPriceResponse(
        productId: String,
        purchaseRate: String,
        total: String,
        purchaseQty: String
    ): LiveData<UdatePPriceResponse> {

        var data = MutableLiveData<UdatePPriceResponse>()
        RetrofitClient
            .Factory.create().updateProductPurchaseRate(productId, purchaseRate, purchaseQty,total)

            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribeWith(object : DisposableSingleObserver<UdatePPriceResponse>() {
                override fun onSuccess(t: UdatePPriceResponse) {
                    data.value = t
                }

                override fun onError(e: Throwable) {
                    var response = UdatePPriceResponse("No Data Found", 2)
                    data.value = response
                }

            })
        return data

    }
}