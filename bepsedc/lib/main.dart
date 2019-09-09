import 'dart:async';

import 'package:flutter/material.dart';
import 'package:flutter/services.dart';

import 'increment.dart';
import 'methodChannel.dart';
import 'beep.dart';

void main() => runApp(MyApp());

const timeout = const Duration(seconds: 3);
const ms = const Duration(milliseconds: 1);

startTimeout([int milliseconds]) {
  var duration = milliseconds == null ? timeout : ms * milliseconds;
  return new Timer(duration, handleTimeout);
}

void handleTimeout() {  // callback function
}

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

bool get enabled => MaterialButton != null;

//---------------------------------------------------------------------------------------------//

  var varMsg = MethodCh();
  var varBeep = BeepModule();

  String msg2 = "No Messages";
  String msg3 = "No Beeps";
  String msg4 = "No Beeps Button";

  void initState() {


    varMsg.getMessage().then((String message) {
      setState(() {
        msg2 = message;
      });
    });

    varMsg.getBeep().then((String beep) {
      setState(() {
        msg3 = beep;
      });
    });

    super.initState();
  }

  void beepbutton() {
    varMsg.getBeepButton();
  }

  void print() {
    varMsg.getPrintEDC();
    setState(() {
    });
  }


  void printTime() {
  Timer(Duration(seconds: 3), () {
  });

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
              color: Colors.blueGrey[50],
              child: Align(
                alignment: Alignment.center,
                child: Text(
                  "${msg2}",
                  textAlign: TextAlign.center,
                  style: TextStyle(fontWeight: FontWeight.normal, fontSize: 15),
                ),
              )),

//---------------------------------------------------------------------------------------------// Increment

          MaterialButton(
            onPressed: addCounter,
            height: 50,
            minWidth: 500,
            color: Colors.grey[100],
            child: Align(
              alignment: Alignment.center,
              child: Text(
                "Basic Increment : ${counter.calculation}",
                textAlign: TextAlign.center,
                style: TextStyle(fontWeight: FontWeight.normal, fontSize: 20),
              ),
            ),
          ),

//---------------------------------------------------------------------------------------------// Break

          SizedBox(
            height: 10,
          ),

//---------------------------------------------------------------------------------------------// Beep EDC

          Container(
              height: 30,
              width: 400,
              color: Colors.blueGrey[50],
              child: Align(
                alignment: Alignment.center,
                child: Text(
                  "${msg3}",
                  textAlign: TextAlign.center,
                  style: TextStyle(fontWeight: FontWeight.normal, fontSize: 15),
                ),
              )),

//---------------------------------------------------------------------------------------------//

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
                    color: Colors.lightBlue[50],
                    onPressed: varMsg.getLEDBlue,
                    child: Align(
                      alignment: Alignment.center,
                      child: Text(
                        "Blue",
                        textAlign: TextAlign.center,
                        style: TextStyle(
                            fontWeight: FontWeight.normal, fontSize: 15),
                      ),
                    )),
                MaterialButton(
                    height: 50,
                    minWidth: 90,
                    color: Colors.yellow[50],
                    onPressed: varMsg.getLEDYellow,
                    child: Align(
                      alignment: Alignment.center,
                      child: Text(
                        "Yellow",
                        textAlign: TextAlign.center,
                        style: TextStyle(
                            fontWeight: FontWeight.normal, fontSize: 15),
                      ),
                    )),
                MaterialButton(
                    height: 50,
                    minWidth: 90,
                    color: Colors.green[50],
                    onPressed: varMsg.getLEDGreen,
                    child: Align(
                      alignment: Alignment.center,
                      child: Text(
                        "Green",
                        textAlign: TextAlign.center,
                        style: TextStyle(
                            fontWeight: FontWeight.normal, fontSize: 15),
                      ),
                    )),
                MaterialButton(
                    height: 50,
                    minWidth: 90,
                    color: Colors.red[50],
                    onPressed: varMsg.getLEDRed,
                    child: Align(
                      alignment: Alignment.center,
                      child: Text(
                        "Red",
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
