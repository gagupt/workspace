#!/usr/bin/python
import re, sys ,csv,math
LIST=[]
count=0
i=0
preDistPoint=0
preNumUser=0
outfile='PosnCOnf_western_Up_true.csv'
outf=open(outfile,'wb')
lines = csv.reader(open('PosnCOnf_western_Up.csv','rb'), delimiter='\t')
outline=csv.writer(outf, delimiter='\t')
for row in lines:
    if row[3] == "true":
            outline.writerow(row)