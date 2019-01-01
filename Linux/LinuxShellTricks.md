1. Command: rev (Reverse) - It reverse every string given to it, is not it funny.

  akg@typhoon:~# rev
  123abc 
  cba321 
  xuniL eb ot nrob
  born to be Linux

2. Command: factor - Time for some Mathematics, this command output all the possible factors of a given number.

  akg@typhoon:~# factor
  5 
  5: 5 
  12 
  12: 2 2 3 
  1001 
  1001: 7 11 13 
  5442134 
  5442134: 2 2721067
  
3. Command: yes - It is funny but useful as well, specially in scripts and for System Administrators where an automated predefined response can be passed to terminal or generated.

  akg@typhoon:~# yes I Love Linux
  I Love Linux
  I Love Linux
  I Love Linux
  I Love Linux
  I Love Linux
  I Love Linux
  I Love Linux
  I Love Linux
  I Love Linux
  I Love Linux
  I Love Linux
  I Love Linux
  Note: (Till you interrupt i.e ctrl+c).

4. Fork Bomb - This is a very nasty piece of code. Run this at your own risk. This actually is a fork bomb which exponentially multiplies itself till all the system resource is utilized and the system hangs. (To check the power of above code you should try it once, but all at your own risk, close and save all other programs and file before running fork bomb).

  akg@typhoon:~# :(){ :|:& }:

5. Command: url - Won’t it be an awesome feeling for you if you can update you twitter status from command line in front of your friend and they seems impressed. OK just replace username, password and your status message with your’s username, password and “your status message“.

  akg@typhoon:~# url -u YourUsername:YourPassword -d status="Your status message" http://twitter.com/statuses/update.xml
