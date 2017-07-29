all: interval

interval: IntervalIdentifier.class
	echo 'java -cp ./jfugue.jar:. IntervalIdentifier' > IntervalCalc
	chmod ugo+x IntervalCalc

IntervalIdentifier.class: IntervalIdentifier.java
	javac -cp ./jfugue.jar:. IntervalIdentifier.java

clean:
	rm -f *.class IntervalCalc

new:
	make clean
	make interval
