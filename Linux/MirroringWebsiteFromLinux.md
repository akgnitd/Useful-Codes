Mirroring a website on Linux

You almost certainly have wget already. Try wget --help at the command line. If you get an error message, install wget with your Linux distribution's package manager. Or fetch it from the official wget page and compile your own copy from source.
Once you have wget installed correctly, the command line to mirror a website is:

wget -m -k -K -E http://url/of/web/site

See man wget or wget --help | more for a detailed explanation of each option.

If this command seems to run forever, there may be parts of the site that generate an infinite series of different URLs. You can combat this in many ways, the simplest being to use the -l option to specify how many links "away" from the home page wget should travel. For instance, -l 3 will refuse to download pages more than three clicks away from the home page. You'll have to experiment with different values for -l. Consult man wget for additional workarounds.
