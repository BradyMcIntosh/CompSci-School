int tempPin = 21;

void setup()
{
  Serial.begin(38400);
}

int code;

void loop()                     
{
  code = analogRead(tempPin);
  Serial.println(code);
  delay(1000);
}
