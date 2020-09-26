//Frame.h
#pragma once

class Frame {
   char* frameName;
   double duration;
public:
   Frame(char*, double);	
   Frame(const Frame&);
   ~Frame();
   Frame& operator=(const Frame&);
   friend std::ostream& operator<<(std::ostream&, Frame&);
};



