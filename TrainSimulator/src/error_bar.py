#!/usr/bin/python
import matplotlib.pyplot as plt
import numpy as np
import csv, sys, re, random, os, time
font = {'family' : 'normal',
        
        'size'   : 10}

plt.rc('font', **font)
fname=sys.argv[1]
#datfile="allUsr.csv"
datfile=sys.argv[2]
gName=sys.argv[3]
Mcsv = csv.reader(open(datfile, 'rb'), delimiter=',', quotechar='"')

# Mapping from column name to column index
I = {}

# Matrix of all the rows
M = []

rowNum = 0
for row in Mcsv:
    rowNum += 1
    if (rowNum == 1):
        for i in range(len(row)):
            I[row[i]] = i
        continue
    else:
        M.append(row)

Usr = map(lambda r: r[I['NumUsr']], M)
Avg = map(lambda r: float(r[I['AvgError']]), M)
SD = map(lambda r:.5*float( r[I['SD']]), M)


ind = np.arange(len(Usr))
width = 0.20

#r1= plt.bar(ind,Avg ,width, color='b')
x = np.array([1, 2, 3, 4])
y = np.power(x, 2) # Effectively y = x**2

plt.errorbar(ind+1.5*width, Avg, SD, linestyle='None',color='b',marker='*')

plt.axis(xmin=-0.5)
plt.xlabel('Number of passengers')
plt.ylabel('Avg Error (m)')
plt.grid(b='on')
plt.xticks(ind+1.5*width, Usr)
plt.legend(loc=0)
plt.suptitle('Graph for Average Error with repect to Ground Trhuth value (' +fname+')')
plt.savefig(gName, bbox_inches='tight')
#plt.show()
