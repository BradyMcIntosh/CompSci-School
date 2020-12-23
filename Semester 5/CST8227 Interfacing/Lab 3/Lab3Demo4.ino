//these teensy pins are PWM capable pins
int greenLED = 3;//3.0V, ~33ohm or bigger - LEDs turn ON with very little current so the 220 ohm that came with the tri-color LED are fine
int blueLED = 4;//2.3V, ~111ohm
int redLED = 5;//1.6V, ~189ohm

int pushButton = 20;
volatile int counter;

long dutyCycleRandNumber;

void setup() {
  // put your setup code here, to run once:
  Serial.begin(9600);
  pinMode(greenLED, OUTPUT);
  pinMode(blueLED, OUTPUT);
  pinMode(redLED, OUTPUT);
  pinMode(pushButton, INPUT);
  pinMode(A0, INPUT);//use this open circuited pin for a seed to the random number generator

  attachInterrupt(pushButton, changeLights, CHANGE);//for task 6, demo #4   
}

//DEMO 4 INTERRUPT WITH DIGITALWRITE COLOUR CYCLING FOR CONVENIENCE USE THREE SEQUENTIAL PINS TO EXPLOIT RANDOM NUMBER GENERATION
//REFER TO THE ATTACHINTERRUPT LINE OF CODE IN THE SETUP() FUNCTION - IT IS COMMENTED OUT
//THIS WAS EASIER TO IMPLEMENT USING FUNCTIONS CHANGELIGHTS AND RANDOMLIGHTS
void loop(){
 
   while(counter < 1)
   {
    randomLights();
   }
 
}//end main

void changeLights()
{
  int onPin, offPin_1, offPin_2;

  counter++;

  int beacon = counter % 3;
     
   if(beacon == 0)
   {
    onPin = greenLED;
    offPin_1 = redLED;
    offPin_2 = blueLED;
   }
   else if(beacon == 1)
   {
    onPin = redLED;
    offPin_1 = greenLED;
    offPin_2 = blueLED;
   }
   else
   {
    onPin = blueLED;
    offPin_1 = greenLED;
    offPin_2 = redLED;
   }

  digitalWrite(onPin, HIGH);
  digitalWrite(offPin_1, LOW);
  digitalWrite(offPin_2, LOW);
  delay(200);
}

void randomLights()
{
   int onPin = random(3, 6);
   
   int offPin_1;
   int offPin_2;
   
   if(onPin == greenLED)
   {
    offPin_1 = redLED;
    offPin_2 = blueLED;
   }
   else if(onPin == redLED)
   {
    offPin_1 = greenLED;
    offPin_2 = blueLED;
   }
   else
   {
    offPin_1 = greenLED;
    offPin_2 = redLED;
   }
  
    digitalWrite(onPin, HIGH);
    digitalWrite(offPin_1, LOW);
    digitalWrite(offPin_2, LOW);
    delay(100);

}
