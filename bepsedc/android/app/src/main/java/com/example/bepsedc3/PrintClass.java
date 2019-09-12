package com.example.bepsedc3;

import com.example.bepsedc3.MainActivity;

import android.app.AlertDialog;
import android.os.RemoteException;
import android.util.Log;
import android.widget.Toast;

import com.example.bepsedc3.api.Printer;
import com.usdk.apiservice.aidl.printer.ASCScale;
import com.usdk.apiservice.aidl.printer.ASCSize;
import com.usdk.apiservice.aidl.printer.AlignMode;
import com.usdk.apiservice.aidl.printer.ECLevel;
import com.usdk.apiservice.aidl.printer.HZScale;
import com.usdk.apiservice.aidl.printer.HZSize;
import com.usdk.apiservice.aidl.printer.OnPrintListener;


import java.io.IOException;
import java.io.InputStream;

//import static com.example.bepsedc3.MainActivity.getContext;


public class PrintClass extends MainActivity {

    private Toast toast;
    private AlertDialog dialog;

    private static final String TAG2 = "PrinterDemo";
    private static final int FONT_SIZE_SMALL = 0;
    private static final int FONT_SIZE_NORMAL = 1;
    private static final int FONT_SIZE_LARGE = 2;

//------- Print Service ---------------------------------------------------------------------------------------------------------------------------------//

    private void setFontSpec(int fontSpec) throws RemoteException {
        switch (fontSpec) {
            case FONT_SIZE_SMALL:
                Printer.getInstance().setHzSize(HZSize.DOT16x16);
                Printer.getInstance().setHzScale(HZScale.SC1x1);
                Printer.getInstance().setAscSize(ASCSize.DOT16x8);
                Printer.getInstance().setAscScale(ASCScale.SC1x1);
                break;

            case FONT_SIZE_NORMAL:
                Printer.getInstance().setHzSize(HZSize.DOT24x24);
                Printer.getInstance().setHzScale(HZScale.SC1x1);
                Printer.getInstance().setAscSize(ASCSize.DOT24x12);
                Printer.getInstance().setAscScale(ASCScale.SC1x1);
                break;

            case FONT_SIZE_LARGE:
                Printer.getInstance().setHzSize(HZSize.DOT24x24);
                Printer.getInstance().setHzScale(HZScale.SC1x2);
                Printer.getInstance().setAscSize(ASCSize.DOT24x12);
                Printer.getInstance().setAscScale(ASCScale.SC1x2);
                break;
        }
    }

    private byte[] readAssetsFile(String fileName) throws RemoteException {
        InputStream input = null;
        try {
            input = getContext().getAssets().open(fileName);
            byte[] buffer = new byte[input.available()];
            int size = input.read(buffer);
            if (size == -1) {
                throw new RemoteException(getContext().getString(R.string.read_fail));
            }
            return buffer;
        } catch (IOException e) {
            throw new RemoteException(e.getMessage());
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    showToast(e.getLocalizedMessage());
                }
            }
        }
    }



    void showToast(String message) {


        message = message != null ? message : context.getString(R.string.unknown_error);
        Log.d(TAG, "showToast: " + message);
        toast.setText(message);
        toast.show();
    }
    void hideDialog() {
        Log.d(TAG, "hideDialog: ");
        dialog.cancel();
    }


    public void print() throws RemoteException {
        // Get statue

        try {
            Printer.getInstance().getStatus();

            // Show dialog
            //noinspection deprecation
//            showDialog(R.string.waiting_for_printing, false);

            // Set gray
            Printer.getInstance().setPrnGray(6);

            // Print image
//            Printer.getInstance().addImage(AlignMode.LEFT, readAssetsFile("ic_bank_logo.bmp"));

            // Print text with normal font size
//            setFontSpec(FONT_SIZE_NORMAL);
//            Printer.getInstance().addText(AlignMode.CENTER, "BEPS");

            // Print text with small font size
            setFontSpec(FONT_SIZE_SMALL);
            Printer.getInstance().addText(AlignMode.CENTER, "Hello World <3");

            // Print text with normal font size
//            setFontSpec(FONT_SIZE_NORMAL);
//            Printer.getInstance().addText(AlignMode.CENTER, "Hello World <3");

            // Print text with small font size
//            setFontSpec(FONT_SIZE_SMALL);
//            Printer.getInstance().addText(AlignMode.LEFT, "TRANS TYPE:");

            // Print text with normal font size
//            setFontSpec(FONT_SIZE_NORMAL);
//            Printer.getInstance().addText(AlignMode.LEFT, "  FAST PAYMENT/CLSS SALE");

            // Print text with small font size
//            setFontSpec(FONT_SIZE_SMALL);
//            Printer.getInstance().addText(AlignMode.LEFT, "BATCH NO:0000001");

            // Print text with normal font size
//            setFontSpec(FONT_SIZE_NORMAL);
//            Printer.getInstance().addText(AlignMode.LEFT, "VOUCHER NO:001954");

            // Print text with small font size
//            setFontSpec(FONT_SIZE_SMALL);
//            Printer.getInstance().addText(AlignMode.LEFT, "DATE:2016/09/18");

            // Print text with normal font size
//            setFontSpec(FONT_SIZE_NORMAL);
//            Printer.getInstance().addText(AlignMode.LEFT, "AMOUNT:RMB 0.01 ");

            // Print text with small font size
//            setFontSpec(FONT_SIZE_SMALL);
//            Printer.getInstance().addText(AlignMode.LEFT, "REFERENCE:");

            // Print text with normal font size
//            setFontSpec(FONT_SIZE_NORMAL);
//            Printer.getInstance().addText(AlignMode.LEFT, "");

            // Print text with small font size
//            setFontSpec(FONT_SIZE_SMALL);
//            Printer.getInstance().addText(AlignMode.LEFT, "TC:4AA3C1579C7FD6BC  ATC:0047");

            // Print barcode
//          Printer.getInstance().addBarCode(AlignMode.CENTER, 2, -1, "12345678901234567890");

            // Print QR code
//            Printer.getInstance().addQrCode(AlignMode.CENTER, 120, ECLevel.ECLEVEL_Q, "Rizky Eka Putra");

            // Feed lines
            Printer.getInstance().feedLine(5);
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        // Start printing
        Printer.getInstance().start(new OnPrintListener.Stub() {

            @Override
            public void onFinish() {
                Log.d(TAG2, "----- onFinish -----");

                hideDialog();
//                showToast(R.string.succeed);
            }

            @Override
            public void onError(int error) {
                Log.d(TAG2, "----- onError ----");

                hideDialog();
//                showToast(Printer.getErrorId(error));
            }
        });
    }
}
