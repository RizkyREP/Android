import 'package:flutter/material.dart';
import 'package:flutter/services.dart';

import 'main.dart';

//---------------------------------------------------------------------------------------------//

class Print extends State<MyHomePage>{

String printEDC = "Print EDC";
String value;

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

  noSuchMethod(Invocation invocation) => super.noSuchMethod(invocation);
}


