#!/bin/bash
numUsr=$1
maxNumUsr=$2
rm Avg.csv
rm allUsr.csv
echo NumUsr,AvgError,SD>allUsr.csv
while [  $numUsr -lt $maxNumUsr ]; do
         
SpotTime=7200
end_sim_time=432000
         while [  $SpotTime -lt $end_sim_time ]; do
echo -e "\n"
echo "num user="$numUsr
echo "Spot Time="$SpotTime
echo -e "\n"

             bash main.sh $end_sim_time $SpotTime $numUsr
	     python CalAvg.py $4 Avg.csv
	     let SpotTime=SpotTime+86400
         done

python avg.py Avg.csv $numUsr allUsr.csv
rm Avg.csv
   
     let numUsr=numUsr+$3
     
done
python error_bar.py $4 </dev/null>/dev/null 2>&1
