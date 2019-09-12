import 'package:flutter/material.dart';
import 'package:flutter/services.dart';

import 'main.dart';

class Message extends State<MyHomePage>{

String msg = "No Message";

String value;

//---------------------------------------------------------------------------------------------// 

static const _msg = const MethodChannel('HelloWorld');   

  Future<String> getMessage() async {
    var sendMap = <String, dynamic> {
      'from' : 'Native Android',
    };
  
    try{
    value = await _msg.invokeMethod('getMessage', sendMap);
    } catch (e) {
      print (e);
    }
    return value;
  }

//---------------------------------------------------------------------------------------------//

  @override
   Widget build(BuildContext context) {
  }
}


