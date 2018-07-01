#!/bin/bash
# This file creates an executable
echo 'cd bin' > IntervalCalc
echo 'java -cp ../src/jfugue.jar:. Main' >> IntervalCalc
chmod 754 IntervalCalc
mv IntervalCalc ./bin