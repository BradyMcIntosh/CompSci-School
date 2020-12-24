/*************************************
 * Practical Question: Winter 2020
 * 
 * Level Achieved: A
 * 
 * Status: Functional 
 * 
 * @author Brady McIntosh
 */


/*  mcin0260_finalexam.ino
 *   2020-04-16
 *   Brady McIntosh
 *   mcin0260@algonquinlive.com
 *   040706980
 *  Two shift registers - Two 7-seg LEDs
 *  Displaying temperature in c/f scale
 *  (kelvin does not work o_o)
*/

// Globals
const int dataPin = 14;
const int latchPin = 16;
const int clockPin = 15;

const int tempPin = 9; //A9 = D23 

volatile int count;

const int updateDelay = 250; // 250 milliseconds or .25 seconds
const int scaleDelay = 1250000; // 1.25M microseconds or 1.25 seconds

IntervalTimer scaleTimer;

// 'a' for common anode, 'c' for common cathode
const char common = 'a';

// 'c' for celsius, 'f' for fahrenheit, 'k' for kelvin
volatile char tempScale;

bool decPt = false;  // decimal point display flag
 
void setup() {
  // initialize I/O pins
  pinMode(dataPin, OUTPUT);
  pinMode(latchPin, OUTPUT);
  pinMode(clockPin, OUTPUT);

  count = 0;
  tempScale = 'c';

  Serial.begin(384000);
  scaleTimer.begin(scaleChange, 1250000);
}

void loop() {

  byte leftBits = numToBits(0);
  byte rightBits = numToBits(0);

  for (int count = 0; count < 5; count++) {

    int temp = readTemp();
    byte leftBits = numToBits(temp/10);
    byte rightBits = numToBits(temp%10);

//      if (decPt) {
//        leftBits = leftBits | B00000001;  // add decimal point if needed
//        rightBits = rightBits | B00000001;  // add decimal point if needed
//      }

    if (count == 0) {
      if(tempScale == 'c') 
      { leftBits = B10011100; } // 'C' shape 
      if(tempScale == 'f') 
      { leftBits = B10001110; } // 'F' shape 
      if(tempScale == 'k') 
      { leftBits = B01101110; } // 'H' shape 
      
      rightBits = B11000110; // upper box shape
    }
    
    updateDisplays(leftBits, rightBits);    // display alphanumeric digit
    delay(updateDelay);
  }
}

void updateDisplays(byte leftBits, byte rightBits) {
  if (common == 'a') {                  // using a common anode display?
    leftBits = leftBits ^ B11111111;    // then flip all bits using XOR 
    rightBits = rightBits ^ B11111111;  // then flip all bits using XOR 
  }
  digitalWrite(latchPin, LOW);  // prepare shift register for data
  shiftOut(dataPin, clockPin, LSBFIRST, rightBits); // send data
  shiftOut(dataPin, clockPin, LSBFIRST, leftBits); // send data
  digitalWrite(latchPin, HIGH); // update display
}

// B10000000 = segment A
// B01000000 = segment B
// B00100000 = segment C
// B00010000 = segment D
// B00001000 = segment E
// B00000100 = segment F
// B00000010 = segment G
// B00000001 = point

byte numToBits(int someNumber) {
  switch (someNumber) {
    case 0:
      return B11111100;
      break;
    case 1:
      return B00001100;
      break;
    case 2:
      return B11011010;
      break;
    case 3:
      return B11110010;
      break;
    case 4:
      return B01100110;
      break;
    case 5:
      return B10110110;
      break;
    case 6:
      return B10111110;
      break;
    case 7:
      return B11100000;
      break;
    case 8:
      return B11111110;
      break;
    case 9:
      return B11110110;
      break;
    case 10:
      return B11101110; // Hexidecimal A
      break;
    case 11:
      return B00111110; // Hexidecimal B
      break;
    case 12:
      return B10011100; // Hexidecimal C or use for Centigrade
      break;
    case 13:
      return B01111010; // Hexidecimal D
      break;
    case 14:
      return B10011110; // Hexidecimal E
      break;
    case 15:
      return B10001110; // Hexidecimal F or use for Fahrenheit
      break;  
    default:
      return B10010010; // Error condition, displays three vertical bars
      break;   
  }
}

float readTemp()                     
{
  float temp;
  float tempOut;
  int code = analogRead(tempPin);
  
  if (code <= 289) {
    temp = 5 + (code - 289) / 9.82;
  }
  if (code > 289 && code <= 342) {
    temp = 10 + (code - 342) / 10.60;
  }
  if (code > 342 && code <= 398) {
    temp = 15 + (code - 398) / 11.12;
  }
  if (code > 398 && code <= 455) {
    temp = 20 + (code - 455) / 11.36;
  }
  if (code > 455 && code <= 512) {
    temp = 25 + (code - 512) / 11.32;
  }
  if (code > 512 && code <= 566) {
    temp = 30 + (code - 566) / 11.00;
  }
  if (code > 566 && code <= 619) {
    temp = 35 + (code - 619) / 10.44;
  }
  if (code > 619 && code <= 667) {
    temp = 40 + (code - 667) / 9.73;
  }
  if (code > 667) {
    temp = 45 + (code - 712) / 8.90;
  }

  temp = temp - 30;

  Serial.print("Temperature: ");
  Serial.print(code);
  Serial.print("(raw) ");

  Serial.print(temp);
  Serial.print(" Celsius ");

  if(tempScale == 'c') 
  { 
    tempOut = temp;
    Serial.print("(X)  ");
  }
  else {
    Serial.print("( )  ");
  }

  float ftemp = temp * 1.8 + 32; 
  Serial.print(ftemp);
  Serial.print(" Fahrenheit ");

  if(tempScale == 'f') 
  { 
    tempOut = ftemp;
    Serial.print("(X)  ");
  }
  else {
    Serial.print("( )  ");
  }

  float ktemp = temp + 273.15;
  Serial.print(ktemp);
  Serial.print(" Kelvin ");

  if(tempScale == 'k') 
  { 
    tempOut = ktemp;
    Serial.print("(X)  ");
  }
  else {
    Serial.print("( )  ");
  }

  Serial.println();
  
  return tempOut;
}

void scaleChange() 
{
  count = 0;
  
  if(tempScale == 'c') 
  { 
    tempScale = 'f';
  }
  else if(tempScale == 'f') 
  { 
    tempScale = 'c';
  }
}
