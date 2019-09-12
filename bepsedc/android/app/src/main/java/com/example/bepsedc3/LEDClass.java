package com.example.bepsedc3;

import android.os.RemoteException;

import com.example.bepsedc3.api.LED;
import com.usdk.apiservice.aidl.led.Light;

public class LEDClass {

    int numRed;
    public void red() {

        try {
            LED.getInstance().turnOn(Light.RED);
            numRed++;
            numRed = numRed%2;
        } catch (RemoteException e) {
            e.printStackTrace();
        }




        if(numRed == 1){                                // Apabila angka = 1
            try{                                        // 'try' artinya Coba lakukan
                LED.getInstance().turnOn(Light.RED);    // Nyalain lampu merah
            }
            catch (RemoteException e) {                 // Kalau gagal,
                e.printStackTrace();                    // Munculkan error
            }
        }else{                                          // Apabila lainnya (angka bukan 1)
            try{                                        // 'try' artinya Coba lakukan
                LED.getInstance().turnOff(Light.RED);   // Matiin lampu merah
            } catch (RemoteException e) {               // Kalau gagal
                e.printStackTrace();                    // Munculkan error
            }
        }









    }
}
