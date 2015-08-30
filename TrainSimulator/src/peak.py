#!/usr/bin/python
import csv,math,sys
LIST=[]
peakVal=[]
count=0
i=0
n=0
k=0
last_peak=0
total = len(sys.argv)
if (total!=3):
	print "Please Enter two arguments....!"
	exit()
inpfile=sys.argv[1]
outfile=sys.argv[2]

#outfile='Peak.csv'
outf=open(outfile,'wb')
lines = csv.reader(open(inpfile,'rb'), delimiter='\t')
outline=csv.writer(outf)
for row in lines:
    n+=1
    LIST.append(row)
for i, val in enumerate(LIST):
    if(i!=0):
        count+=1
        #print val[0],LIST[i-1][0]
        if((float)(val[0])!=(float)(LIST[i-1][0])+100 and val[2]!=LIST[i-1][2]):
                last_peak=i
                count/=2
                k=i-count
                count=0
                peakVal.append(k)
peakVal.append(i-(i-last_peak)/2)
#print last_peak,i,i-(i-last_peak)/2
for i, val in enumerate(LIST):
    if(i in peakVal):
        outline.writerow(val)    
            
