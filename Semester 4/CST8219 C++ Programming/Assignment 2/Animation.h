//Animation.h
#pragma once

class Animation
{
	std::string animationName;
	std::forward_list<Frame> frames;	

public:
   Animation(std::string name):animationName(name) {}
   ~Animation() {}
   void EditFrame();
   void DeleteFrame();
   // Add a Frame as in cin >> A;
   friend std::istream& operator>>(std::istream&,Animation&);
   // output the Frames as in cout << A;
   friend std::ostream& operator<<(std::ostream&,Animation&);
};


