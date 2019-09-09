import 'package:flutter/material.dart';
import 'package:flutter/services.dart';

import 'main.dart';

class MethodCh extends State<MyHomePage>{

String msg = "No Message";
String beepmsg = "No Beep";
String beepbutton = "No Beep Button";

String ledRed = "Led Red";
String printEDC = "Print EDC";

//---------------------------------------------------------------------------------------------//


 
//---------------------------------------------------------------------------------------------//

static const _beepmsg = const MethodChannel('HelloWorld');   

  Future<String> getMessage() async {
    var sendMap = <String, dynamic> {
      'from' : 'Native Android',
    };
    
    String value;

    try{
    value = await _beepmsg.invokeMethod('getMessage', sendMap);
    } catch (e) {
      print (e);
    }

    return value;
  }

//---------------------------------------------------------------------------------------------//

static const beep = const MethodChannel('Beep');   

  Future<String> getBeep() async {
    var sendMap = <String, dynamic> {
      'from2' : '',
    };
    
    String value;

    try{
    value = await beep.invokeMethod('getBeep', sendMap);
    } catch (e) {
      print (e);
    }

    return value;
  }

//---------------------------------------------------------------------------------------------//

static const _beepbutton = const MethodChannel('BeepButton');   

  Future<String> getBeepButton() async {
    var sendMap = <String, dynamic> {
      'from3' : 'Connected',
    };
    
    String value;

    try{
    value = await _beepbutton.invokeMethod('getBeepButton', sendMap);
    } catch (e) {
      print (e);
    }

    return value;
  }

//---------------------------------------------------------------------------------------------//


static const _ledRed = const MethodChannel('LEDRed'); 
static const _ledGreen = const MethodChannel('LEDGreen');
static const _ledBlue = const MethodChannel('LEDBlue'); 
static const _ledYellow = const MethodChannel('LEDYellow'); 

String value;

    Future getLEDRed() async {
    try{
    value = await _ledRed.invokeMethod('LEDRed');
    } catch (e) {
      print (e);
    }
  }

    Future getLEDGreen() async {
    try{
    value = await _ledGreen.invokeMethod('LEDGreen');
    } catch (e) {
      print (e);
    }
  }

    Future getLEDBlue() async {
    try{
    value = await _ledBlue.invokeMethod('LEDBlue');
    } catch (e) {
      print (e);
    }
  }

    Future getLEDYellow() async {
    try{
    value = await _ledYellow.invokeMethod('LEDYellow');
    } catch (e) {
      print (e);
    }
  }

//---------------------------------------------------------------------------------------------//

static const _printEDC = const MethodChannel('Print EDC'); 

  Future getPrintEDC() async {
    try{
    value = await _printEDC.invokeMethod('Print EDC');
    } catch (e) {
      print (e);
    }
  }

//---------------------------------------------------------------------------------------------//

  @override
   Widget build(BuildContext context) {
  }
}


