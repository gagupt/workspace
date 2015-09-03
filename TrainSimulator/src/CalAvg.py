#!/usr/bin/python
import csv,math,sys
total = len(sys.argv)
if (total!=3):
	print "Please Enter two arguments....!"
	exit()
inpfileEst=sys.argv[1]
Sum=0
Avg=0
count=0
outfileAvg=sys.argv[2]

outfAvg=open(outfileAvg,'a')
outlineAvg=csv.writer(outfAvg)
List=[]

linesEst = csv.reader(open(inpfileEst,'rb'), delimiter=',')

for row in linesEst:
        
        Sum=Sum+float(row[1])
        List.append(row[1])
        count+=1
squaresum=0
Avg=Sum/count
#print Avg
for i, val in enumerate(List):
        squaresum+=math.pow(abs(float(val)-Avg),2)
stand_dev=math.sqrt(squaresum/count)
#print stand_dev 
listTemp=[]
listTemp.append(Avg)
listTemp.append(stand_dev)
outlineAvg.writerow(listTemp)
