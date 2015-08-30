#!/usr/bin/python
import csv,math,sys
LISTEst=[]
LISTtru=[]

min=0
total = len(sys.argv)
if (total!=5):
	print "Please Enter four arguments....!"
	exit()
inpfileEst=sys.argv[1]
inpfileTru=sys.argv[2]
outfileEst=sys.argv[3]
outfileTru=sys.argv[4]

outfEst=open(outfileEst,'wb')
outfTru=open(outfileTru,'wb')
outlineEst=csv.writer(outfEst)
outlineTru=csv.writer(outfTru)

#calculating for each estimated value

linesEst = csv.reader(open(inpfileEst,'rb'), delimiter=',')

for row1 in linesEst:
    min=1000000
    listTemp=[]
    linesTru = csv.reader(open(inpfileTru,'rb'), delimiter=',')
    for row2 in linesTru:
        #print abs(float(row1[0])-float(row2[4]))
        if(min>abs(float(row1[0])-float(row2[4]))):
            min=abs(float(row1[0])-float(row2[4]))
    listTemp.append(row1[0])
    listTemp.append(min)
    LISTEst.append(listTemp)
for i, val in enumerate(LISTEst):
    outlineEst.writerow(val)

#calculating avg and max Positional Confidence

linesEst = csv.reader(open(inpfileEst,'rb'), delimiter=',')
maxPosConf=0
avgPosConf=0
s=0
i=0
for row in linesEst:
    s=s+float(row[1])
    i=i+1
    if(maxPosConf<row[1]):
        maxPosConf=row[1]
avgPosConf=s/i
#print avgPosConf,maxPosConf

s1="AveragePosConfidence="+str(avgPosConf)
s2="MaxPosConfidence="+str(maxPosConf)
listTemp=[]
listTemp.append(s1)
outlineEst.writerow(listTemp)
listTemp=[]
listTemp.append(s2)
outlineEst.writerow(listTemp)

#calculating for each ground truth value
linesTru = csv.reader(open(inpfileTru,'rb'), delimiter=',')

for row1 in linesTru:
    min=1000000
    listTemp=[]
    linesEst = csv.reader(open(inpfileEst,'rb'), delimiter=',')
    for row2 in linesEst:
        #print abs(float(row1[0])-float(row2[4]))
        if(min>abs(float(row1[4])-float(row2[0]))):
            min=abs(float(row1[4])-float(row2[0]))
    listTemp.append(row1[4])
    listTemp.append(min)
    LISTtru.append(listTemp)
for i, val in enumerate(LISTtru):
    outlineTru.writerow(val)


   
