#!/bin/bash
# This file creates an executable
echo 'result=`pwd | grep -c "bin"`' > IntervalCalcGUI
echo 'if [ $result -eq 0 ]; then cd bin; fi' >> IntervalCalcGUI
echo 'java -cp ../src/jfugue.jar:. GUIMain' >> IntervalCalcGUI
chmod 754 IntervalCalcGUI
mv IntervalCalcGUI ./bin