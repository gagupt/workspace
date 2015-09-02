#!/bin/bash
numUsr=1000
maxNumUsr=5000

while [  $numUsr -lt $maxNumUsr ]; do
         
SpotTime=1000
end_sim_time=10000

         while [  $SpotTime -lt $end_sim_time ]; do
             bash main.sh $end_sim_time $SpotTime $numUsr
	     python CalAvg.py westernEstUp.csv wesUpAvg.csv
	     let SpotTime=SpotTime+2000
         done

python avg.py wesUpAvg.csv $numUsr allUsr.csv
rm wesUpAvg.csv
   
     let numUsr=numUsr+1000
     
done
