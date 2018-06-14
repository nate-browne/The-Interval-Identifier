# Interval Identifier

### Intro

Ever wondered what an augmented 7th above A flat was, but wasn't sure how to
calculate it or wanted to make sure your answer was correct? This program is the
program for you! This calculator gives users the ability to calculate musical
intervals on the fly, as well as playing the new interval created back to the
user for them to hear what it sounds like.

### Changelog

As of version 2.5, if the user
enters in a quality that isn't major, minor, diminished, or augmented, the
program treats the input as major and does nothing special to the interval.

As of version 3.0, the user can now select the starting octave instead of the
default octave of middle C being used! Yay!

### Usage

On UNIX systems, use the provided Makefile commands to compile, and run the
program with ./IntervalCalc to get your musical woes solved! Windows users,
compile how you normally would compile a java program with

javac -cp "%JFUGUE_DIR%"\jfugue.jar;. IntervalIdentifier.java

(replacing "%JFUGUE_DIR%" with the directory name where you have the .jar file
saved into)

and run with

java -cp "%JFUGUE_DIR%"\jfugue.jar;. IntervalIdentifier

I hope this project is of great use to you!

Email `natebrowne@outlook.com` with any suggestions, bugs, or ideas for the next
versions of this program!