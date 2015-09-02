#!/usr/bin/python
import csv,math,sys
total = len(sys.argv)
if (total!=4):
	print "Please Enter three arguments....!"
	exit()
inpfileEst=sys.argv[1]
Sum1=0
Sum2=0
count=0
outfileAvg=sys.argv[3]

outfAvg=open(outfileAvg,'a')
outlineAvg=csv.writer(outfAvg)
List=[]

linesEst = csv.reader(open(inpfileEst,'rb'), delimiter=',')

for row in linesEst:
        Sum1=Sum1+float(row[0])
        Sum2=Sum2+float(row[1])
        count+=1
listTemp=[]
listTemp.append(sys.argv[2])
listTemp.append(Sum1/count)
listTemp.append(Sum2/count)
outlineAvg.writerow(listTemp)

