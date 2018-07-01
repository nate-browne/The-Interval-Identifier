#!/bin/bash
# This file creates an executable
echo 'cd bin' > IntervalCalcGUI
echo 'java -cp ../src/jfugue.jar:. GUIMain' >> IntervalCalcGUI
chmod 754 IntervalCalcGUI
mv IntervalCalcGUI ./bin