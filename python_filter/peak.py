#!/usr/bin/python
import csv,math
LIST=[]
peakVal=[]
count=0
i=0
n=0
k=0
outfile='Peak.csv'
outf=open(outfile,'wb')
lines = csv.reader(open('PosnCOnf_western_Up_true.csv','rb'), delimiter='\t')
outline=csv.writer(outf)
for row in lines:
    n+=1
    LIST.append(row)
for i, val in enumerate(LIST):
    if(i!=0):
        count+=1
        #print val[0],LIST[i-1][0]
        if((float)(val[0])!=(float)(LIST[i-1][0])+100):
            #print LIST[i-1][0],count
            count/=2
            k=i-count
            count=0
            peakVal.append(k)

for i, val in enumerate(LIST):
    if(i in peakVal):
        outline.writerow(val)    
            