all: interval

interval: IntervalIdentifier.class
	echo 'java IntervalIdentifier' > IntervalCalc
	chmod ugo+x IntervalCalc

IntervalIdentifier.class: IntervalIdentifier.java
	javac IntervalIdentifier.java

clean:
	rm -f *.class IntervalCalc

new:
	make clean
	make interval
