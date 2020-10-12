package com.vocalheart.retailgully_ordermanager.helper;

import android.os.Bundle;
import android.os.CancellationSignal;
import android.os.ParcelFileDescriptor;
import android.print.PageRange;
import android.print.PrintAttributes;
import android.print.PrintDocumentAdapter;

public class PrintDocumentAdapterWrapper extends PrintDocumentAdapter{

    private final PrintDocumentAdapter delegate;
    public PrintDocumentAdapterWrapper(PrintDocumentAdapter adapter){
        super();
        this.delegate = adapter;
    }

    @Override
    public void onLayout(PrintAttributes printAttributes, PrintAttributes printAttributes1, CancellationSignal cancellationSignal, LayoutResultCallback layoutResultCallback, Bundle bundle) {

    }

    @Override
    public void onWrite(PageRange[] pageRanges, ParcelFileDescriptor parcelFileDescriptor, CancellationSignal cancellationSignal, WriteResultCallback writeResultCallback) {

    }

    public void onFinish(){
        delegate.onFinish();
        //insert hook here
    }

    //override all other methods with a trivial implementation calling to the delegate
}
