import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'main.dart';

class BeepModule extends State<MyHomePage>{

//---------------------------------------------------------------------------------------------//

String beep50 = "Beep 50ms";
String beep100 = "Beep 100ms";
String beep200 = "Beep 200ms";
String beep500 = "Beep 500ms";
String value;
 
//---------------------------------------------------------------------------------------------//

static const _beep50 = const MethodChannel('Beep50');
static const _beep100 = const MethodChannel('Beep100');   
static const _beep200 = const MethodChannel('Beep200');   
static const _beep500 = const MethodChannel('Beep500'); 

  Future getBeep50() async {
    try{
    value = await _beep50.invokeMethod('Beep50');
    } catch (e) {
      print (e);
    }
  }

  Future getBeep100() async {
    try{
    value = await _beep100.invokeMethod('Beep100');
    } catch (e) {
      print (e);
    }
  }

    Future getBeep200() async {
    try{
    value = await _beep200.invokeMethod('Beep200');
    } catch (e) {
      print (e);
    }
  }

    Future getBeep500() async {
    try{
    value = await _beep500.invokeMethod('Beep500');
    } catch (e) {
      print (e);
    }
  }

//---------------------------------------------------------------------------------------------//

  @override
   Widget build(BuildContext context) {
  }
}


