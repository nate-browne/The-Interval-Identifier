#!/bin/bash
# This file creates an executable
echo 'cd bin' > IntervalCalc
echo 'java -cp ../src/jfugue.jar:. TextMain' >> IntervalCalc
chmod 754 IntervalCalc
mv IntervalCalc ./bin