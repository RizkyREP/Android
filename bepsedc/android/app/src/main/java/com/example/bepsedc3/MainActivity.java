package com.example.bepsedc3;

import com.example.bepsedc3.api.Beeper;
import com.example.bepsedc3.api.DeviceService;
import com.example.bepsedc3.api.LED;
import com.example.bepsedc3.PrintClass;
import com.example.bepsedc3.ServiceClass;
import com.example.bepsedc3.LEDClass;

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
    private static final String CHANNEL4 = "Beep50";
    private static final String CHANNEL5 = "Beep100";
    private static final String CHANNEL6 = "Beep200";
    private static final String CHANNEL7 = "Beep500";

    private static final String LEDCHANNEL1 = "LEDRed";
    private static final String LEDCHANNEL2 = "LEDGreen";
    private static final String LEDCHANNEL3 = "LEDBlue";
    private static final String LEDCHANNEL4 = "LEDYellow";

    private static final String PRINTCHANNEL1 = "Print EDC";

    private static DeviceService deviceService;
    public static Context context;
    public static final String TAG = "MainActivity";
    private static final String USDK_ACTION_NAME = "com.usdk.apiservice";
    private static final String USDK_PACKAGE_NAME = "com.usdk.apiservice";


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
//          int numRed;

          @Override
          public void onMethodCall(MethodCall methodCall, MethodChannel.Result result) {

              LEDClass ledClass = new LEDClass();
              ledClass.red();

//              if(numRed == 1){
//                  try{
//                      LED.getInstance().turnOn(Light.RED);
//                  } catch (RemoteException e) {
//                      e.printStackTrace();
//                  }
//              }else{
//                      try{
//                          LED.getInstance().turnOff(Light.RED);
//                      } catch (RemoteException e) {
//                      e.printStackTrace();
//                  }
//              }

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

//------- LED BLUE ---------------------------------------------------------------------------------------------------------------------------------//



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
                  PrintClass printClass = new PrintClass();
                  printClass.print();
              } catch (RemoteException e) {
                  e.printStackTrace();
              }
          }
      }
      );

//------- Super Important Brace ---------------------------------------------------------------------------------------------------------------------------------//

}

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


//------- SDK Service ---------------------------------------------------------------------------------------------------------------------------------//

}