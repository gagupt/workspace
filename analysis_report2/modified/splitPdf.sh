#!/bin/bash
w="western"
c="central"
h="harbour"
mkdir "western"
mkdir "central"
mkdir "harbour"

for i in *.pdf;do
    y=${i##*.}
    fname=${i%%.pdf}
    #echo $y
    #echo $fname
    #echo $i
    pdftk $i cat 1 output $fname$w.pdf
    mv $fname$w.pdf western/
 
    pdftk $i cat 2 output $fname$c.pdf
    mv $fname$c.pdf central/

    pdftk $i cat 3 output $fname$h.pdf
    mv $fname$h.pdf harbour/
done
