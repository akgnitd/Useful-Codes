Code:    
    shopt -s histappend
    
This allows multiple terminal sessions to write to the history at the same time.
In most environments this option is not enabled.
That means that histories are often lost if you have more than a single Bash session open (either locally or over SSH).
