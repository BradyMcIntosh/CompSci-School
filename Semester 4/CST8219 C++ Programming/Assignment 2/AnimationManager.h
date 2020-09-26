// AnimationManager.h
#pragma once

class AnimationManager
{
   std::string managerName;
   std::vector<Animation> animations;

public:
   AnimationManager(std::string name) :managerName(name) {}
   ~AnimationManager() {}
   void EditAnimation();
   void DeleteAnimation();
   // add an Animation
   friend std::istream& operator>>(std::istream&, AnimationManager&);
   // output the Animations
   friend std::ostream& operator<<(std::ostream&,AnimationManager&);
};


