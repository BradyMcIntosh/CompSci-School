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
  modified 05 Mar 2020
  by Brady McIntosh

  This example code is in the public domain.

  http://www.arduino.cc/en/Tutorial/Calibration
*/

// These constants won't change:
const int volPin = 1;    // pin that the sensor is attached to
const int tonePin = 16;    // pin that the sensor is attached to
const int ledPin = 20;        // pin that the LED is attached to
const int piezoPin = 21;        // pin that the LED is attached to

// variables:
int volValue = 0;         // the sensor value
int volMin = 1000;        // minimum sensor value
int volMax = 0;           // maximum sensor value

int toneValue = 0;         // the sensor value
int toneMin = 1000;        // minimum sensor value
int toneMax = 0;           // maximum sensor value


void setup() {
  // turn on LED to signal the start of the calibration period:
  pinMode(ledPin, OUTPUT);
  pinMode(piezoPin, OUTPUT);
  digitalWrite(ledPin, HIGH);
  digitalWrite(piezoPin, LOW);

  Serial.begin(57600);

  // calibrate during the first five seconds
  while (millis() < 6000) {
    volValue = touchRead(volPin);
    toneValue = touchRead(tonePin);

    // record the maximum sensor value
    if (volValue > volMax) {
      volMax = volValue;
    }

    // record the minimum sensor value
    if (volValue < volMin) {
      volMin = volValue;
    }

    // record the maximum sensor value
    if (toneValue > toneMax) {
      toneMax = toneValue;
    }

    // record the minimum sensor value
    if (toneValue < toneMin) {
      toneMin = toneValue;
    }
  }

  // signal the end of the calibration period
  digitalWrite(ledPin, LOW);
}

void loop() {
  // read the sensor:
  volValue = touchRead(volPin);
  toneValue = touchRead(tonePin);

  // apply the calibration to the sensor reading
  volValue = map(volValue, volMin, volMax, 0, 255);
  toneValue = map(toneValue, toneMin, toneMax, 0, 255);

  // in case the sensor value is outside the range seen during calibration
  volValue = constrain(volValue, 0, 255);
  toneValue = constrain(toneValue, 0, 255);
  
  // fade the LED using the calibrated value:
  analogWrite(ledPin, volValue);
  buzz(volValue, toneValue);
}

// Generate sound with a given volume and tone
void buzz(int volValue, int toneValue) {

  int duty_cycle = volValue / 32;
  int frequency = toneValue * 2 + 400;

  Serial.println(duty_cycle);
  
  digitalWrite(piezoPin, HIGH); 
  delayMicroseconds(1000000 / frequency * duty_cycle); 
  digitalWrite(piezoPin, LOW); 
  delayMicroseconds(1000000 / frequency * (1.0 - duty_cycle));
}
