Linux Funny Commands

1. Command: sl (Steam Locomotive)
  You might be aware of command ‘ls‘ the list command and use it frequently to view the contents of a folder but because of miss-typing sometimes you would result in ‘sl‘, how about getting a little fun in terminal and not “command not found“.

  Install sl

  akg@typhoon:~# apt-get install sl 		(In Debian like OS)
  akg@typhoon:~# yum -y install sl 		(In Red Hat like OS)

  Output
  akg@typhoon:~# sl
  sl funny command
  sl command
  This command works even when you type ‘LS‘ and not ‘ls‘.

2. Command: telnet


3. Command: fortune
  what about getting your random fortune, sometimes funny in terminal.

  Install fortune

  akg@typhoon:~# apt-get install fortune 	(for aptitude based system)
  akg@typhoon:~# yum install fortune 		(for yum based system)
  akg@typhoon:~# fortune
  You're not my type.  For that matter, you're not even my species!!!
  Future looks spotty.  You will spill soup in late evening.
  You worry too much about your job.  Stop it.  You are not paid enough to worry.
  Your love life will be... interesting.

4. Command: rev (Reverse)
    It reverse every string given to it, is not it funny.

    akg@typhoon:~# rev
    123abc
    cba321
    xuniL eb ot nrob
    born to be Linux

5. Command: factor
  Time for some Mathematics, this command output all the possible factors of a given number.

  akg@typhoon:~# factor
  5
  5: 5
  12
  12: 2 2 3
  1001
  1001: 7 11 13
  5442134
  5442134: 2 2721067

6. Command: script
  OK fine this is not a command and a script but it is nice.

  akg@typhoon:~# for i in {1..12}; do for j in $(seq 1 $i); do echo -ne $iÃ—$j=$((i*j))\\t;done; echo;done
  1Ã—1=1
  2Ã—1=2	2Ã—2=4
  3Ã—1=3	3Ã—2=6	3Ã—3=9
  4Ã—1=4	4Ã—2=8	4Ã—3=12	4Ã—4=16
  5Ã—1=5	5Ã—2=10	5Ã—3=15	5Ã—4=20	5Ã—5=25
  6Ã—1=6	6Ã—2=12	6Ã—3=18	6Ã—4=24	6Ã—5=30	6Ã—6=36
  7Ã—1=7	7Ã—2=14	7Ã—3=21	7Ã—4=28	7Ã—5=35	7Ã—6=42	7Ã—7=49
  8Ã—1=8	8Ã—2=16	8Ã—3=24	8Ã—4=32	8Ã—5=40	8Ã—6=48	8Ã—7=56	8Ã—8=64
  9Ã—1=9	9Ã—2=18	9Ã—3=27	9Ã—4=36	9Ã—5=45	9Ã—6=54	9Ã—7=63	9Ã—8=72	9Ã—9=81
  10Ã—1=10	10Ã—2=20	10Ã—3=30	10Ã—4=40	10Ã—5=50	10Ã—6=60	10Ã—7=70	10Ã—8=80	10Ã—9=90	10Ã—10=100
  11Ã—1=11	11Ã—2=22	11Ã—3=33	11Ã—4=44	11Ã—5=55	11Ã—6=66	11Ã—7=77	11Ã—8=88	11Ã—9=99	11Ã—10=110	11Ã—11=121
  12Ã—1=12	12Ã—2=24	12Ã—3=36	12Ã—4=48	12Ã—5=60	12Ã—6=72	12Ã—7=84	12Ã—8=96	12Ã—9=108	12Ã—10=120	12Ã—11=132	12Ã—12=144

7. Command: Cowsay
  An ASCII cow in terminal that will say what ever you want.

  Install Cowsay

  akg@typhoon:~# apt-get install cowsay 		(for Debian based OS)
  akg@typhoon:~# yum install cowsay		(for Red Hat based OS)
  Output

  akg@typhoon:~# cowsay I Love nix
  ____________
  < I Love nix >
  ------------
  \   ^__^
  \  (oo)\_______
  (__)\       )\/\
  ||----w |
  ||     ||
  How about pipelineing ‘fortune command‘, described above with cowsay?

  akg@typhoon:~# fortune | cowsay
  _________________________________________
  / Q: How many Oregonians does it take to  \
  | screw in a light bulb? A: Three. One to |
  | screw in the light bulb and two to fend |
  | off all those                           |
  |                                         |
  | Californians trying to share the        |
  \ experience.                             /
  -----------------------------------------
  \   ^__^
  \  (oo)\_______
  (__)\       )\/\
  ||----w |
  ||     ||
  Note: ‘|‘ is called pipeline instruction and it is used where the output of one command needs to be the input of another command. In the above example the output of ‘fortune‘ command acts as an input of ‘cowsay‘ command. This pipeline instruction is frequently used in scripting and programming.

  xcowsay is a graphical program which response similar to cowsay but in a graphical manner, hence it is X of cowsay.

  apt-get install xcowsay
  yum install xcowsay
  Output

  akg@typhoon:~# xcowsay I Love nix
  install xcowsay
  xcowsay command
  cowthink is another command just run “cowthink Linux is sooo funny” and see the difference in output of cowsay and cowthink.

  apt-get install cowthink
  yum install cowthink
  Output

  akg@typhoon:~# cowthink ....Linux is sooo funny
  _________________________
  ( ....Linux is sooo funny )
  -------------------------
  o   ^__^
  o  (oo)\_______
  (__)\       )\/\
  ||----w |
  ||     ||

8. Command: yes
  It is funny but useful as well, specially in scripts and for System Administrators where an automated predefined response can be passed to terminal or generated.

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

9. Command: toilet
  what? Are u kidding, huhh no! Definitely not, but for sure this command name itself is too funny, and I don’t know from where this command gets it’s name.

  Install toilet

  akg@typhoon:~# apt-get install toilet
  akg@typhoon:~# yum install toilet
  Output

  akg@typhoon:~# toilet tecmint
  mmmmmmm                        "             m
  #     mmm    mmm   mmmmm  mmm    m mm   mm#mm          mmm    mmm   mmmmm
  #    #"  #  #"  "  # # #    #    #"  #    #           #"  "  #" "#  # # #
  #    #""""  #      # # #    #    #   #    #           #      #   #  # # #
  #    "#mm"  "#mm"  # # #  mm#mm  #   #    "mm    #    "#mm"  "#m#"  # # #
  It even offers some kind of color and fonts style.

  akg@typhoon:~# toilet -f mono12 -F metal Tecmint.com
  install toilet command
  toilet command
  Note: Figlet is another command that more or less provide such kind of effect in terminal.

10. Command: cmatrix
  You might have seen Hollywood movie ‘matrix‘ and would be fascinated with power, Neo was provided with, to see anything and everything in matrix or you might think of an animation that looks alike Hacker‘s desktop.

  Install cmatrix

  akg@typhoon:~# apt-get install cmatrix
  akg@typhoon:~# yum install cmatrix
  Output

  akg@typhoon:~# cmatrix
  cmatrix command
  cmatrix command

11. Command: oneko
  OK so you believe that mouse pointer of Linux is the same silly black/white pointer where no animation lies then I fear you could be wrong. “oneko“ is a package that will attach a “Jerry“ with you mouse pointer and moves along with you pointer.

  Install oneko

  akg@typhoon:~# apt-get install oneko
  akg@typhoon:~# yum install oneko
  Output

  akg@typhoon:~# oneko
  install oneko
  oneko command
  Note: Once you close the terminal from which oneko was run, jerry will disappear, nor will start at start-up. You can add the application to start up and continue enjoying.

12. Fork Bomb
  This is a very nasty piece of code. Run this at your own risk. This actually is a fork bomb which exponentially multiplies itself till all the system resource is utilized and the system hangs. (To check the power of above code you should try it once, but all at your own risk, close and save all other programs and file before running fork bomb).

  akg@typhoon:~# :(){ :|:& }:

13. Command: while
  The below “while” command is a script which provides you with colored date and file till you interrupt (ctrl + c). Just copy and paste the below code in terminal.

  akg@typhoon:~# while true; do echo "$(date '+%D %T' | toilet -f term -F border --gay)"; sleep 1; done
  Linux while command
  Linux while command
  Note: The above script when modified with following command, will gives similar output but with a little difference, check it in your terminal.

  akg@typhoon:~# while true; do clear; echo "$(date '+%D %T' | toilet -f term -F border --gay)"; sleep 1; done

14. Command: espeak
  Just Turn the Knob of your multimedia speaker to full before pasting this command in your terminal and let us know how you felt listening the god’s voice.

  Install espeak

  akg@typhoon:~# apt-get install espeak
  akg@typhoon:~# yum install espeak
  Output

  akg@typhoon:~# espeak "Tecmint is a very good website dedicated to Foss Community"

15. Command: aafire
  How about fire in your terminal. Just type “aafire” in the terminal, without quotes and see the magic. Press any key to interrupt the program.

  Install aafire

  akg@typhoon:~# sudo apt-get install libaa-bin
  Output

  akg@typhoon:~# aafire
  install aafire
  aafire command

16. Command: bb
  First install “apt-get insatll bb” and then, type “bb” in terminal and see what happens.

  akg@typhoon:~# bb
  bb command
  bb command

17. Command: url
  Won’t it be an awesome feeling for you if you can update you twitter status from command line in front of your friend and they seems impressed. OK just replace username, password and your status message with your’s username, password and “your status message“.

  akg@typhoon:~# url -u YourUsername:YourPassword -d status="Your status message" http://twitter.com/statuses/update.xml

18. ASCIIquarium
  How it will be to get an aquarium in terminal.

  akg@typhoon:~# apt-get install libcurses-perl
  akg@typhoon:~# cd /tmp
  akg@typhoon:~# wget http://search.cpan.org/CPAN/authors/id/K/KB/KBAUCOM/Term-Animation-2.4.tar.gz
  akg@typhoon:~# tar -zxvf Term-Animation-2.4.tar.gz
  akg@typhoon:~# cd Term-Animation-2.4/
  akg@typhoon:~# perl Makefile.PL &&  make &&   make test
  akg@typhoon:~# make install
  Install ASCIIquarium

  Now Download and Install ASCIIquarium.

  akg@typhoon:~# cd /tmp
  akg@typhoon:~# wget http://www.robobunny.com/projects/asciiquarium/asciiquarium.tar.gz
  akg@typhoon:~# tar -zxvf asciiquarium.tar.gz
  akg@typhoon:~# cd asciiquarium_1.1/
  akg@typhoon:~# cp asciiquarium /usr/local/bin
  akg@typhoon:~# chmod 0755 /usr/local/bin/asciiquarium
  And finally run “asciiquarium” or “/usr/local/bin/asciiquarium“ in terminal without quotes and be a part of magic that will be taking place in front of your eyes.

  akg@typhoon:~# asciiquarium
  install aquarium
  aquarium command

19. Command: funny manpages
  First install “apt-get install funny-manpages” and then run man pages for the commands below. Some of them may be 18+, run at your own risk, they all are too funny.

  baby
  celibacy
  condom
  date
  echo
  flame
  flog
  gong
  grope, egrope, fgrope
  party
  rescrog
  rm
  rtfm
  tm
  uubp
  woman (undocumented)
  xkill
  xlart
  sex
  strfry
  akg@typhoon:~# man baby

20. Linux Tweaks
  It is time for you to have some one liner tweaks.

  akg@typhoon:~# world
  bash: world: not found
  akg@typhoon:~# touch girls\ boo**
  touch: cannot touch `girls boo**': Permission denied
  akg@typhoon:~# nice man woman
  No manual entry for woman
  akg@typhoon:~# ^How did the sex change operation go?^
  bash: :s^How did the sex change operation go?^ : substitution failed
  akg@typhoon:~# %blow
  bash: fg: %blow: no such job
  akg@typhoon:~# make love
  make: *** No rule to make target `love'.  Stop.
  $ [ whereis my brain?
  sh: 2: [: missing ]
  % man: why did you get a divorce?
  man:: Too many arguments.
  % !:say, what is saccharine?
  Bad substitute.
  server@localhost:/srv$ \(-
  bash: (-: command not found
  Linux is sexy: who | grep -i blonde | date; cd ~; unzip; touch; strip; finger; mount; gasp; yes; uptime; umount; sleep (If you know what i mean)
