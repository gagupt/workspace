#!/bin/bash
echo "running MainActivity"
javac MainActivity.java 
java MainActivity $1 $2 $3 >output.csv
echo $1 $2 $3
echo "running CalPeak.sh" 
bash CalPeak.sh

echo "running CalOriginalPos.sh"
bash CalOriginalPos.sh $2  #this is getSpottingsNowTime 

echo "running CalNearDist.sh"
bash CalNearDist.sh $2   #this is getSpottingsNowTime 

echo "copying files to analysis_report2"
cp centralEstDown.csv ../../analysis_report2/
cp centralEstUp.csv ../../analysis_report2/
cp centralTruDown.csv ../../analysis_report2/
cp centralTruUp.csv ../../analysis_report2/

cp westernEstDown.csv ../../analysis_report2/
cp westernEstUp.csv ../../analysis_report2/
cp westernTruDown.csv ../../analysis_report2/
cp westernTruUp.csv ../../analysis_report2/

cp harbourEstDown.csv ../../analysis_report2/
cp harbourEstUp.csv ../../analysis_report2/
cp harbourTruDown.csv ../../analysis_report2/
cp harbourTruUp.csv ../../analysis_report2/
