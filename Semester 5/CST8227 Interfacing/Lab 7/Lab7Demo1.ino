/*
  Calibration

  Demonstrates one technique for calibrating sensor input. The sensor readings
  during the first five seconds of the sketch execution define the minimum and
  maximum of expected values attached to the sensor pin.

  The sensor minimum and maximum initial values may seem backwards. Initially,
  you set the minimum high and listen for anything lower, saving it as the new
  minimum. Likewise, you set the maximum low and listen for anything higher as
  the new maximum.

  The circuit:
  - analog sensor (potentiometer will do) attached to analog input 0
  - LED attached from digital pin 9 to ground

  created 29 Oct 2008
  by David A Mellis
  modified 30 Aug 2011
  by Tom Igoe

  This example code is in the public domain.

  http://www.arduino.cc/en/Tutorial/Calibration
*/

// These constants won't change:
const int sensorPin = 1;    // pin that the sensor is attached to
const int ledPin = 20;        // pin that the LED is attached to

// variables:
int sensorValue = 0;         // the sensor value
int sensorMin = 1000;        // minimum sensor value
int sensorMax = 0;           // maximum sensor value


void setup() {
  // turn on LED to signal the start of the calibration period:
  pinMode(ledPin, OUTPUT);
  digitalWrite(ledPin, HIGH);

  Serial.begin(57600);

  // calibrate during the first five seconds
  while (millis() < 6000) {
    sensorValue = touchRead(sensorPin);
    Serial.println(sensorValue);

    // record the maximum sensor value
    if (sensorValue > sensorMax) {
      sensorMax = sensorValue;
    }

    // record the minimum sensor value
    if (sensorValue < sensorMin) {
      sensorMin = sensorValue;
    }
  }

  // signal the end of the calibration period
  digitalWrite(ledPin, LOW);
}

void loop() {
  // read the sensor:
  sensorValue = touchRead(sensorPin);

  // apply the calibration to the sensor reading
  sensorValue = map(sensorValue, sensorMin, sensorMax, 0, 255);

  // in case the sensor value is outside the range seen during calibration
  sensorValue = constrain(sensorValue, 0, 255);
  Serial.println(sensorValue);
  
  // fade the LED using the calibrated value:
  analogWrite(ledPin, sensorValue);
}
