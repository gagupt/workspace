#!/bin/bash
numUsr=100
maxNumUsr=3000
rm cenUpAvg.csv
rm allUsr.csv
echo NumUsr,AvgError,SD>allUsr.csv
while [  $numUsr -lt $maxNumUsr ]; do
         
SpotTime=1000
end_sim_time=30000
         while [  $SpotTime -lt $end_sim_time ]; do
echo -e "\n"
echo "num user="$numUsr
echo "Spot Time="$SpotTime
echo -e "\n"

             bash main.sh $end_sim_time $SpotTime $numUsr
	     python CalAvg.py centralEstUp.csv cenUpAvg.csv
	     let SpotTime=SpotTime+5000
         done

python avg.py cenUpAvg.csv $numUsr allUsr.csv
rm cenUpAvg.csv
   
     let numUsr=numUsr+300
     
done
python error_bar.py </dev/null>/dev/null 2>&1
