import 'package:flutter/material.dart';
import 'package:flutter/services.dart';

import 'main.dart';

class LED extends State<MyHomePage> {
  
//---------------------------------------------------------------------------------------------//

  static const _ledRed = const MethodChannel('LEDRed');
  static const _ledGreen = const MethodChannel('LEDGreen');
  static const _ledBlue = const MethodChannel('LEDBlue');
  static const _ledYellow = const MethodChannel('LEDYellow');

  String value;

  Future getLEDRed() async {
    try {
      value = await _ledRed.invokeMethod('LEDRed');
    } catch (e) {
      print(e);
    }
  }

  Future getLEDGreen() async {
    try {
      value = await _ledGreen.invokeMethod('LEDGreen');
    } catch (e) {
      print(e);
    }
  }

  Future getLEDBlue() async {
    try {
      value = await _ledBlue.invokeMethod('LEDBlue');
    } catch (e) {
      print(e);
    }
  }

  Future getLEDYellow() async {
    try {
      value = await _ledYellow.invokeMethod('LEDYellow');
    } catch (e) {
      print(e);
    }
  }

//---------------------------------------------------------------------------------------------//

  @override
  Widget build(BuildContext context) {}
}
