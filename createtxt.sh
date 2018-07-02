#!/bin/bash
# This file creates an executable
echo 'result=`pwd | grep -c "bin"`' > IntervalCalc
echo 'if [ $result -eq 0 ]; then cd bin; fi' >> IntervalCalc
echo 'java -cp ../src/jfugue.jar:. TextMain' >> IntervalCalc
chmod 754 IntervalCalc
mv IntervalCalc ./bin