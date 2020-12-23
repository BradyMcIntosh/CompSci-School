/*  SevenSegmentLEDdisplay102a.ino
 *   2017-02-20
 *   Mel Lester Jr.
 *  Simple example of using Shift Register with a
 *  Single Digit Seven Segment LED Display
*/
// Globals
const int dataPin = 14;
const int latchPin = 16;
const int clockPin = 15;

volatile int count = 0;

int myDelay = 100;

/* uncomment one of the following lines that describes your display
 *  and comment out the line that does not describe your display */
const char common = 'a';    // common anode
//const char common = 'c';    // common cathode

bool decPt = true;  // decimal point display flag
 
void setup() {
  // initialize I/O pins
  pinMode(dataPin, OUTPUT);
  pinMode(latchPin, OUTPUT);
  pinMode(clockPin, OUTPUT);
}

void loop() {
  decPt = !decPt; // display decimal point every other pass through loop

  // generate characters to display for hexidecimal numbers 0 to F
  for (count = 0; count <= 99; count++) {
    byte leftBits = myfnNumToBits(count/10);
    byte rightBits = myfnNumToBits(count%10);
    if (decPt) {
      leftBits = leftBits | B00000001;  // add decimal point if needed
      rightBits = rightBits | B00000001;  // add decimal point if needed
    }
    myfnUpdateDisplay(leftBits, rightBits);    // display alphanumeric digit
    delay(myDelay);                 // pause for 1/2 second
  }
}

void myfnUpdateDisplay(byte leftBits, byte rightBits) {
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

byte myfnNumToBits(int someNumber) {
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
void resetCounter() {
  noInterrupts();
  count = 0;
  interrupts();
}
