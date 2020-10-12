package com.vocalheart.retailgully_ordermanager.ui

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Picture
import android.os.Build

import android.os.Bundle
import android.view.View

import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.mazenrashed.printooth.Printooth
import com.mazenrashed.printooth.data.converter.ArabicConverter
import com.mazenrashed.printooth.data.printable.ImagePrintable
import com.mazenrashed.printooth.data.printable.Printable
import com.mazenrashed.printooth.data.printable.RawPrintable
import com.mazenrashed.printooth.data.printable.TextPrintable
import com.mazenrashed.printooth.data.printer.DefaultPrinter
import com.mazenrashed.printooth.ui.ScanningActivity
import com.mazenrashed.printooth.utilities.Printing
import com.mazenrashed.printooth.utilities.PrintingCallback
import com.vocalheart.retailgully_ordermanager.R
import com.vocalheart.retailgully_ordermanager.viewmodel.CreateOrderViewModel
import kotlinx.android.synthetic.main.activity_print.*
import kotlinx.android.synthetic.main.appbar_layout.*
import java.lang.reflect.Constructor
import java.lang.reflect.Method

class PrintActivity : AppCompatActivity() {
    lateinit var createOrderViewModel: CreateOrderViewModel
    lateinit var webview: WebView
    lateinit var printing: Printing
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_print)
        setSupportActionBar(toolbar2)
        supportActionBar?.title = "Invoice Preview"
        webview=webView
        if (Printooth.hasPairedPrinter())
            printing = Printooth.printer()

        createOrderViewModel = ViewModelProvider(this).get(CreateOrderViewModel::class.java)
        webView.getSettings().setJavaScriptEnabled(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            WebView.enableSlowWholeDocumentDraw()
        }
        webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                return false
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                print_loading.visibility= View.GONE
                fab_print.visibility= View.VISIBLE
                //if page loaded successfully then show print button

            }
        }


        var orderid = intent.getStringExtra("order_id")

        createOrderViewModel.getInvoicePrint(orderid).observe(this, Observer {
            if (it.status.equals(1)) {
                webView.loadUrl(it.invoice_view)
            }
        })


        fab_print.setOnClickListener {
          //  print(webView)

            if (!Printooth.hasPairedPrinter()) startActivityForResult(Intent(this,
                ScanningActivity::class.java),
                ScanningActivity.SCANNING_FOR_PRINTER)
            else printSomeImages()
        }
        printing?.printingCallback = object : PrintingCallback {
            override fun connectingWithPrinter() {

                Toast.makeText(this@PrintActivity, "Connecting with printer", Toast.LENGTH_SHORT).show()
            }

            override fun printingOrderSentSuccessfully() {

                Toast.makeText(this@PrintActivity, "Order sent to printer", Toast.LENGTH_SHORT).show()
            }

            override fun connectionFailed(error: String) {

                Toast.makeText(this@PrintActivity, "Failed to connect printer", Toast.LENGTH_SHORT).show()
            }

            override fun onError(error: String) {

                Toast.makeText(this@PrintActivity, error, Toast.LENGTH_SHORT).show()
            }

            override fun onMessage(message: String) {

                Toast.makeText(this@PrintActivity, "Message: $message", Toast.LENGTH_SHORT).show()
            }

        }
    }
//    private fun print(webView: WebView) {
//        try {
//            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
//                (getSystemService(Context.PRINT_SERVICE) as PrintManager)?.let { printManager ->
//
//                    val jobName = "${getString(R.string.app_name)} Document"
//
//                    // Get a print adapter instance
//                    val printAdapter = if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
//                        webView.createPrintDocumentAdapter(jobName)
//                    } else {
//                        TODO("VERSION.SDK_INT < LOLLIPOP")
//                    }
//
//                    // Create a print job with name and adapter instance
//                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
//                        printManager.print(
//                            jobName,
//                            printAdapter,
//                            PrintAttributes.Builder().build()
//                        ).also { printJob ->
//
//                            // Save the job object for later status checking
//
//                        }
//                    } else {
//                        TODO("VERSION.SDK_INT < KITKAT")
//                    }
//                }
//            }
//
//        } catch (e: Exception) {
//        }
//    }

    private fun printSomePrintable() {
        val printables = getSomePrintables()
        printing.print(printables)
    }

    private fun printSomeImages() {

        val printable1 = ArrayList<Printable>().apply {
            add(ImagePrintable.Builder(webViewPrint(webview)!!).build())
        }
        printing?.print(printable1)

    }

    private fun getSomePrintables() = ArrayList<Printable>().apply {
        add(RawPrintable.Builder(byteArrayOf(27, 100, 4)).build()) // feed lines example in raw mode


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == ScanningActivity.SCANNING_FOR_PRINTER && resultCode == Activity.RESULT_OK)
            printSomeImages()
//        initViews()
    }



    private fun webViewPrint(webView: WebView): Bitmap? {

        var bitmapCitizen: Bitmap? = null
        val capturePicture: Picture = webView.capturePicture()
        val width: Int = capturePicture.width
        val height: Int = capturePicture.height
        try {
            bitmapCitizen = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        } catch (e: OutOfMemoryError) {
            Toast.makeText(applicationContext, "2", Toast.LENGTH_LONG).show()
        }
        capturePicture.draw(Canvas(bitmapCitizen!!))
        return bitmapCitizen
    }

}