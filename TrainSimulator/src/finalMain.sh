#!/bin/bash
numUsr=2500
maxNumUsr=10000
rm wesUpAvg.csv
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
	     python CalAvg.py westernEstUp.csv wesUpAvg.csv
	     let SpotTime=SpotTime+5000
         done

python avg.py wesUpAvg.csv $numUsr allUsr.csv
rm wesUpAvg.csv
   
     let numUsr=numUsr+500
     
done
python error_bar.py </dev/null>/dev/null 2>&1
