package com.example.bepsedc3;

import com.example.bepsedc3.api.Beeper;
import com.example.bepsedc3.api.DeviceService;
import com.example.bepsedc3.api.LED;
import com.example.bepsedc3.api.Printer;

import android.app.AlertDialog;
import android.os.Bundle;
import android.os.RemoteException;
import io.flutter.app.FlutterActivity;
import io.flutter.plugins.GeneratedPluginRegistrant;

import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;

import com.usdk.apiservice.aidl.UDeviceService;
import com.usdk.apiservice.aidl.led.Light;
import com.usdk.apiservice.aidl.printer.ASCScale;
import com.usdk.apiservice.aidl.printer.ASCSize;
import com.usdk.apiservice.aidl.printer.AlignMode;
import com.usdk.apiservice.aidl.printer.ECLevel;
import com.usdk.apiservice.aidl.printer.HZScale;
import com.usdk.apiservice.aidl.printer.HZSize;
import com.usdk.apiservice.aidl.printer.OnPrintListener;


import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;


public class MainActivity extends FlutterActivity {
    private static final String CHANNEL = "HelloWorld";
    private static final String CHANNEL2 = "Beep";
    private static final String CHANNEL3 = "BeepButton";
    private static final String CHANNEL4 = "Beep50";
    private static final String CHANNEL5 = "Beep100";
    private static final String CHANNEL6 = "Beep200";
    private static final String CHANNEL7 = "Beep500";

    private static final String LEDCHANNEL1 = "LEDRed";
    private static final String LEDCHANNEL2 = "LEDGreen";
    private static final String LEDCHANNEL3 = "LEDBlue";
    private static final String LEDCHANNEL4 = "LEDYellow";

    private static final String PRINTCHANNEL1 = "Print EDC";


    private static Context context;
    private static DeviceService deviceService;
    private static final String TAG = "MainActivity";
    private static final String USDK_ACTION_NAME = "com.usdk.apiservice";
    private static final String USDK_PACKAGE_NAME = "com.usdk.apiservice";


    private static final String TAG2 = "PrinterDemo";
    private static final int FONT_SIZE_SMALL = 0;
    private static final int FONT_SIZE_NORMAL = 1;
    private static final int FONT_SIZE_LARGE = 2;
    private Toast toast;
    private AlertDialog dialog;



//----------------------------------------------------------------------------------------------------------------------------------------//

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    context = getApplicationContext();
    bindSdkDeviceService();

    GeneratedPluginRegistrant.registerWith(this);


//----------------------------------------------------------------------------------------------------------------------------------------//


      new MethodChannel(getFlutterView(), CHANNEL).setMethodCallHandler(new MethodChannel.MethodCallHandler() {

          @Override
          public void onMethodCall(MethodCall methodCall, MethodChannel.Result result) {

              final Map<String, Object> arguments =  methodCall.arguments();

              if (methodCall.method.equals("getMessage")) {

                  String from = (String) arguments.get("from");
                  String message = "Connected to " + from;

                  result.success(message);
              }
          }
      }
      );

//----------------------------------------------------------------------------------------------------------------------------------------//


      new MethodChannel(getFlutterView(), CHANNEL2).setMethodCallHandler(new MethodChannel.MethodCallHandler() {

      @Override
      public void onMethodCall(MethodCall methodCall, MethodChannel.Result result) {

          final Map<String, Object> arguments =  methodCall.arguments();

          if (methodCall.method.equals("getBeep")) {

              String from2 = (String) arguments.get("from2");
              String message = "Beep Module " + from2;

              result.success(message);

              try {
                  Beeper.getInstance().startBeep(100);
              } catch (RemoteException e) {
                  e.printStackTrace();
              }
          }
      }
  }
  );

//----------------------------------------------------------------------------------------------------------------------------------------//

      new MethodChannel(getFlutterView(), CHANNEL3).setMethodCallHandler(new MethodChannel.MethodCallHandler() {

      @Override
      public void onMethodCall(MethodCall methodCall, MethodChannel.Result result) {

          if (methodCall.method.equals("getBeepButton")) {

              try {
                  Beeper.getInstance().startBeep(50);
              } catch (RemoteException e) {
                  e.printStackTrace();
              }
          }
      }
  }
  );

//----------------------------------------------------------------------------------------------------------------------------------------//

      new MethodChannel(getFlutterView(), CHANNEL4).setMethodCallHandler(new MethodChannel.MethodCallHandler() {

        @Override
        public void onMethodCall(MethodCall methodCall, MethodChannel.Result result) {

            if (methodCall.method.equals("Beep50")) {
                try {
                    Beeper.getInstance().startBeep(50);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        }
    }
  );

      new MethodChannel(getFlutterView(), CHANNEL5).setMethodCallHandler(new MethodChannel.MethodCallHandler() {

          @Override
          public void onMethodCall(MethodCall methodCall, MethodChannel.Result result) {

              if (methodCall.method.equals("Beep100")) {
                  try {
                      Beeper.getInstance().startBeep(100);
                  } catch (RemoteException e) {
                      e.printStackTrace();
                  }
              }
          }
      }
   );

      new MethodChannel(getFlutterView(), CHANNEL6).setMethodCallHandler(new MethodChannel.MethodCallHandler() {

          @Override
          public void onMethodCall(MethodCall methodCall, MethodChannel.Result result) {

              if (methodCall.method.equals("Beep200")) {
                  try {
                      Beeper.getInstance().startBeep(200);
                  } catch (RemoteException e) {
                      e.printStackTrace();
                  }
              }
          }
      }
   );

      new MethodChannel(getFlutterView(), CHANNEL7).setMethodCallHandler(new MethodChannel.MethodCallHandler() {

          @Override
          public void onMethodCall(MethodCall methodCall, MethodChannel.Result result) {

              if (methodCall.method.equals("Beep500")) {
                  try {
                      Beeper.getInstance().startBeep(500);
                  } catch (RemoteException e) {
                      e.printStackTrace();
                  }
              }
          }
      }
   );


//------- LED Service ---------------------------------------------------------------------------------------------------------------------------------//
//------- LED RED ---------------------------------------------------------------------------------------------------------------------------------//

      new MethodChannel(getFlutterView(), LEDCHANNEL1).setMethodCallHandler(new MethodChannel.MethodCallHandler() {
          int numRed;

          @Override
          public void onMethodCall(MethodCall methodCall, MethodChannel.Result result) {

              try {
                  LED.getInstance().turnOn(Light.RED);
                  numRed++;
                  numRed = numRed%2;
              } catch (RemoteException e) {
                  e.printStackTrace();
              }
              if(numRed == 1){
                  try{
                      LED.getInstance().turnOn(Light.RED);
                  } catch (RemoteException e) {
                      e.printStackTrace();
                  }
              }else{
                      try{
                          LED.getInstance().turnOff(Light.RED);
                      } catch (RemoteException e) {
                      e.printStackTrace();
                  }
              }


          }
      }
   );

//------- LED GREEN ---------------------------------------------------------------------------------------------------------------------------------//

      new MethodChannel(getFlutterView(), LEDCHANNEL2).setMethodCallHandler(new MethodChannel.MethodCallHandler() {
          int numGreen;

          @Override
          public void onMethodCall(MethodCall methodCall, MethodChannel.Result result) {

              try {
                  LED.getInstance().turnOn(Light.GREEN);
                  numGreen++;
                  numGreen = numGreen % 2;
              } catch (RemoteException e) {
                  e.printStackTrace();
              }
              if (numGreen == 1) {
                  try {
                      LED.getInstance().turnOn(Light.GREEN);
                  } catch (RemoteException e) {
                      e.printStackTrace();
                  }
              } else {
                  try {
                      LED.getInstance().turnOff(Light.GREEN);
                  } catch (RemoteException e) {
                      e.printStackTrace();
                  }
              }
          }
      }
   );

//------- LED GREEN ---------------------------------------------------------------------------------------------------------------------------------//

      new MethodChannel(getFlutterView(), LEDCHANNEL3).setMethodCallHandler(new MethodChannel.MethodCallHandler() {
          int numBlue;

          @Override
          public void onMethodCall(MethodCall methodCall, MethodChannel.Result result) {

              try {
                  LED.getInstance().turnOn(Light.BLUE);
                  numBlue++;
                  numBlue = numBlue % 2;
              } catch (RemoteException e) {
                  e.printStackTrace();
              }
              if (numBlue == 1) {
                  try {
                      LED.getInstance().turnOn(Light.BLUE);
                  } catch (RemoteException e) {
                      e.printStackTrace();
                  }
              } else {
                  try {
                      LED.getInstance().turnOff(Light.BLUE);
                  } catch (RemoteException e) {
                      e.printStackTrace();
                  }
              }
          }
        }
      );

//------- LED YELLOW ---------------------------------------------------------------------------------------------------------------------------------//

      new MethodChannel(getFlutterView(), LEDCHANNEL4).setMethodCallHandler(new MethodChannel.MethodCallHandler() {
          int numYellow;

          @Override
          public void onMethodCall(MethodCall methodCall, MethodChannel.Result result) {

              try {
                  LED.getInstance().turnOn(Light.YELLOW);
                  numYellow++;
                  numYellow = numYellow % 2;
              } catch (RemoteException e) {
                  e.printStackTrace();
              }
              if (numYellow == 1) {
                  try {
                      LED.getInstance().turnOn(Light.YELLOW);
                  } catch (RemoteException e) {
                      e.printStackTrace();
                  }
              } else {
                  try {
                      LED.getInstance().turnOff(Light.YELLOW);
                  } catch (RemoteException e) {
                      e.printStackTrace();
                  }
              }
          }
      }
      );

//------- LED YELLOW ---------------------------------------------------------------------------------------------------------------------------------//

      new MethodChannel(getFlutterView(), PRINTCHANNEL1).setMethodCallHandler(new MethodChannel.MethodCallHandler() {

          @Override
          public void onMethodCall(MethodCall methodCall, MethodChannel.Result result) {
              try {
                  print();
              } catch (RemoteException e) {
                  e.printStackTrace();
              }
          }
      }
      );

//------- Super Important Brace ---------------------------------------------------------------------------------------------------------------------------------//

}

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

    private void print() throws RemoteException {
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
            setFontSpec(FONT_SIZE_NORMAL);
            Printer.getInstance().addText(AlignMode.CENTER, "BEPS");
//            Printer.getInstance().addText(AlignMode.LEFT, "POS SALES SLIP");
//            Printer.getInstance().addText(AlignMode.LEFT, "MERCHANT NAME");
//          Printer.getInstance().addText(AlignMode.LEFT, "  " + TerminalInfo.merchantName);

            // Print text with small font size
            setFontSpec(FONT_SIZE_SMALL);
//          Printer.getInstance().addText(AlignMode.LEFT, "MERCHANT NO:");
//          Printer.getInstance().addText(AlignMode.LEFT, "  " + TerminalInfo.merchantNo);
//          Printer.getInstance().addText(AlignMode.LEFT, "TERMINAL NO:");
//          Printer.getInstance().addText(AlignMode.LEFT, "  " + TerminalInfo.terminalNo);
//          Printer.getInstance().addText(AlignMode.LEFT, "OPERATOR NO:" + TerminalInfo.operatorNo);
//          Printer.getInstance().addText(AlignMode.LEFT, "ISSUER:" + TerminalInfo.issuer);
//          Printer.getInstance().addText(AlignMode.LEFT, "ACQUIRER:" + TerminalInfo.acquirer);
//          Printer.getInstance().addText(AlignMode.CENTER, "Jl Guru Mughni");

            // Print text with normal font size
//            setFontSpec(FONT_SIZE_NORMAL);
            Printer.getInstance().addText(AlignMode.CENTER, "Hello World <3");

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
//            Printer.getInstance().addText(AlignMode.LEFT, "REF NO:160918094357");
//            Printer.getInstance().addText(AlignMode.LEFT, "AUTH NO:ECC001");

            // Print text with small font size
//            setFontSpec(FONT_SIZE_SMALL);
//            Printer.getInstance().addText(AlignMode.LEFT, "DATE:2016/09/18");
//            Printer.getInstance().addText(AlignMode.LEFT, "TIME:09:43:00");
//            Printer.getInstance().addText(AlignMode.LEFT, "EXP DATE:2023/12");

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
//            Printer.getInstance().addText(AlignMode.LEFT, "TUR:0000000000 SN:000 TSI:0000");
//            Printer.getInstance().addText(AlignMode.LEFT, "AID:A000000333010101");
//            Printer.getInstance().addText(AlignMode.LEFT, "APP LABEL:EMV DEBIT");
//            Printer.getInstance().addText(AlignMode.LEFT, "UNPR NUM:283CBA05");
//            Printer.getInstance().addText(AlignMode.LEFT, "AIP:7C00  Term Capa:000000");
//            Printer.getInstance().addText(AlignMode.LEFT, "9F10:07020103900000010A01000000198933660FAD");
//            Printer.getInstance().addText(AlignMode.LEFT, "Ver:00000606160101");
//            Printer.getInstance().addText(AlignMode.LEFT, "S/N:50629416");

            // Print barcode
//          Printer.getInstance().addBarCode(AlignMode.CENTER, 2, -1, "12345678901234567890");

            // Print QR code
             Printer.getInstance().addQrCode(AlignMode.CENTER, 120, ECLevel.ECLEVEL_Q, "Rizky Eka Putra");

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



//------- SDK Service ---------------------------------------------------------------------------------------------------------------------------------//



    private void bindSdkDeviceService() {
        Intent intent = new Intent();
        intent.setAction(USDK_ACTION_NAME);
        intent.setPackage(USDK_PACKAGE_NAME);

        Log.d(TAG, "binding sdk device service...");
        boolean flag = bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
        if (!flag) {
            Log.d(TAG, "SDK service binding failed.");
            return;
        }

        Log.d(TAG, "SDK service binding successfully.");
    }

    private ServiceConnection serviceConnection = new ServiceConnection() {

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.d(TAG, "SDK service disconnected.");
            deviceService = null;
        }

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.d(TAG, "SDK service connected.");

            try {
                deviceService = new DeviceService(UDeviceService.Stub.asInterface(service));
                deviceService.register();
                deviceService.debugLog(true, true);
                Log.d(TAG, "SDK deviceService initiated version:" + deviceService.getVersion() + ".");
            } catch (RemoteException e) {
                throw new RuntimeException("SDK deviceService initiating failed.", e);
            }

            try {
                linkToDeath(service);
            } catch (RemoteException e) {
                throw new RuntimeException("SDK service link to death error.", e);
            }
        }

        private void linkToDeath(IBinder service) throws RemoteException {
            service.linkToDeath(() -> {
                Log.d(TAG, "SDK service is dead. Reconnecting...");
                bindSdkDeviceService();
            }, 0);
        }


    };

    public static Context getContext() {
        if (context == null) {
            throw new RuntimeException("Initiate context failed");
        }

        return context;
    }

    public static DeviceService getDeviceService() {
        if (deviceService == null) {
            throw new RuntimeException("SDK service is still not connected Lol.");
        }

        return deviceService;
    }



}
