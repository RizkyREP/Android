import 'dart:async';

import 'package:bepsedc3/led.dart';
import 'package:flutter/material.dart';
import 'package:flutter/services.dart';

import 'increment.dart';
import 'msg.dart';
import 'beep.dart';
import 'led.dart';
import 'print.dart';

void main() => runApp(MyApp());

//---------------------------------------------------------------------------------------------//

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Bepsnet3',
      theme: ThemeData(
        primarySwatch: Colors.blue,
      ),
      home: MyHomePage(title: 'Beps3'),
    );
  }
}

class MyHomePage extends StatefulWidget {
  MyHomePage({Key key, this.title}) : super(key: key);

  final String title;

  @override
  _MyHomePageState createState() => _MyHomePageState();
}

//---------------------------------------------------------------------------------------------//

class _MyHomePageState extends State<MyHomePage> {
  final counter = Add();

  void addCounter() {
    setState(() {
      counter.plusCounter();
    });
  }

//---------------------------------------------------------------------------------------------//

  var varMsg = Message();
  var varBeep = BeepModule();
  var varLED = LED();
  var varPrint = Print();
  
  String _message = "Not Connected to Native Android";
  bool pressBlue = false;
  bool pressYellow = false;
  bool pressGreen = false;
  bool pressRed = false;

  void initState() {
    varMsg.getMessage().then((String message) {
      setState(() {
        _message = message;
      });
    });

    super.initState();
  }

  void print() {
    varPrint.getPrintEDC();
    setState(() {});
  }

//----------------------------------------------------------------------------------------------//

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text(widget.title),
      ),
      body: Column(
        children: <Widget>[
//---------------------------------------------------------------------------------------------// Native Android

          Container(
              height: 30,
              width: 400,
              color: Colors.blue[50],
              child: Align(
                alignment: Alignment.center,
                child: Text(
                  "$_message",
                  textAlign: TextAlign.center,
                  style: TextStyle(fontWeight: FontWeight.normal, fontSize: 15),
                ),
              )),

//---------------------------------------------------------------------------------------------// Increment

          // MaterialButton(
          //   onPressed: addCounter,
          //   height: 50,
          //   minWidth: 500,
          //   color: Colors.grey[100],
          //   child: Align(
          //     alignment: Alignment.center,
          //     child: Text(
          //       "Basic Increment : ${counter.calculation}",
          //       textAlign: TextAlign.center,
          //       style: TextStyle(fontWeight: FontWeight.normal, fontSize: 20),
          //     ),
          //   ),
          // ),

//---------------------------------------------------------------------------------------------// Break

          SizedBox(
            height: 10,
          ),

//---------------------------------------------------------------------------------------------// Beeper Module

          Container(
              height: 30,
              width: 400,
              color: Colors.blueGrey[50],
              child: Align(
                alignment: Alignment.center,
                child: Text(
                  "Beeper Module",
                  textAlign: TextAlign.center,
                  style: TextStyle(fontWeight: FontWeight.normal, fontSize: 15),
                ),
              )),

//---------------------------------------------------------------------------------------------// Beeper

          SingleChildScrollView(
            scrollDirection: Axis.horizontal,
            child: Row(
              children: <Widget>[
                MaterialButton(
                    height: 50,
                    minWidth: 100,
                    color: Colors.grey[100],
                    onPressed: varBeep.getBeep50,
                    child: Align(
                      alignment: Alignment.center,
                      child: Text(
                        "50ms",
                        textAlign: TextAlign.center,
                        style: TextStyle(
                            fontWeight: FontWeight.normal, fontSize: 15),
                      ),
                    )),
                MaterialButton(
                    height: 50,
                    minWidth: 100,
                    color: Colors.grey[100],
                    onPressed: varBeep.getBeep100,
                    child: Align(
                      alignment: Alignment.center,
                      child: Text(
                        "100ms",
                        textAlign: TextAlign.center,
                        style: TextStyle(
                            fontWeight: FontWeight.normal, fontSize: 15),
                      ),
                    )),
                MaterialButton(
                    height: 50,
                    minWidth: 100,
                    color: Colors.grey[100],
                    onPressed: varBeep.getBeep200,
                    child: Align(
                      alignment: Alignment.center,
                      child: Text(
                        "200ms",
                        textAlign: TextAlign.center,
                        style: TextStyle(
                            fontWeight: FontWeight.normal, fontSize: 15),
                      ),
                    )),
                MaterialButton(
                    height: 50,
                    minWidth: 100,
                    color: Colors.grey[100],
                    onPressed: varBeep.getBeep500,
                    child: Align(
                      alignment: Alignment.center,
                      child: Text(
                        "500ms",
                        textAlign: TextAlign.center,
                        style: TextStyle(
                            fontWeight: FontWeight.normal, fontSize: 15),
                      ),
                    )),
              ],
            ),
          ),

//---------------------------------------------------------------------------------------------// Break

          SizedBox(
            height: 10,
          ),

//---------------------------------------------------------------------------------------------// LED Module

          Container(
              height: 30,
              width: 400,
              color: Colors.blueGrey[50],
              child: Align(
                alignment: Alignment.center,
                child: Text(
                  "LED Module",
                  textAlign: TextAlign.center,
                  style: TextStyle(fontWeight: FontWeight.normal, fontSize: 15),
                ),
              )),

//---------------------------------------------------------------------------------------------// LED

          SingleChildScrollView(
            scrollDirection: Axis.horizontal,
            child: Row(
              children: <Widget>[
                MaterialButton(
                    height: 50,
                    minWidth: 90,
                    color: pressBlue ? Colors.blue[500] : Colors.blue[50],
                    onPressed: () => [
                          varLED.getLEDBlue(),
                          setState(() => pressBlue = !pressBlue)
                        ],
                    child: new Container(
                      alignment: Alignment.center,
                      child: Text(
                        "Blue",
                        textAlign: TextAlign.center,
                        style: TextStyle(
                            fontWeight: FontWeight.normal,
                            fontSize: 15,
                            color: pressBlue ? Colors.white : Colors.black),
                      ),
                    )),
                MaterialButton(
                    height: 50,
                    minWidth: 90,
                    color: pressYellow ? Colors.orange[500] : Colors.yellow[50],
                    onPressed: () => [
                          varLED.getLEDYellow(),
                          setState(() => pressYellow = !pressYellow)
                        ],
                    child: Align(
                      alignment: Alignment.center,
                      child: Text(
                        "Yellow",
                        textAlign: TextAlign.center,
                        style: TextStyle(
                            fontWeight: FontWeight.normal,
                            fontSize: 15,
                            color: pressYellow ? Colors.white : Colors.black),
                      ),
                    )),
                MaterialButton(
                    height: 50,
                    minWidth: 90,
                    color: pressGreen ? Colors.green[500] : Colors.green[50],
                    onPressed: () => [
                          varLED.getLEDGreen(),
                          setState(() => pressGreen = !pressGreen)
                        ],
                    child: Align(
                      alignment: Alignment.center,
                      child: Text(
                        "Green",
                        textAlign: TextAlign.center,
                        style: TextStyle(
                            fontWeight: FontWeight.normal,
                            fontSize: 15,
                            color: pressGreen ? Colors.white : Colors.black),
                      ),
                    )),
                MaterialButton(
                    height: 50,
                    minWidth: 90,
                    color: pressRed ? Colors.red[500] : Colors.red[50],
                    onPressed: () => [
                          varLED.getLEDRed(),
                          setState(() => pressRed = !pressRed)
                        ],
                    child: Align(
                      alignment: Alignment.center,
                      child: Text(
                        "Red",
                        textAlign: TextAlign.center,
                        style: TextStyle(
                            fontWeight: FontWeight.normal,
                            fontSize: 15,
                            color: pressRed ? Colors.white : Colors.black),
                      ),
                    )),
              ],
            ),
          ),

//---------------------------------------------------------------------------------------------// Break

          SizedBox(
            height: 10,
          ),

//---------------------------------------------------------------------------------------------// Print

          Container(
              height: 30,
              width: 400,
              color: Colors.blueGrey[50],
              child: Align(
                alignment: Alignment.center,
                child: Text(
                  "Print Module",
                  textAlign: TextAlign.center,
                  style: TextStyle(fontWeight: FontWeight.normal, fontSize: 15),
                ),
              )),

//---------------------------------------------------------------------------------------------//

          MaterialButton(
              height: 50,
              minWidth: 400,
              color: Colors.grey[100],
              onPressed: print,
              child: Align(
                alignment: Alignment.center,
                child: Text(
                  "Print Test",
                  textAlign: TextAlign.center,
                  style: TextStyle(fontWeight: FontWeight.normal, fontSize: 20),
                ),
              )),

//---------------------------------------------------------------------------------------------//
        ],
      ),
    );
  }
}
