#!/bin/bash
mv *.csv random/
cd random/
pdflatex "\def\NumUser{$1} \input{report.tex}" </dev/null>/dev/null 2>&1
mv report.pdf ../
cd ..
mv report.pdf $(echo $1_user_report).pdf
#evince report.pdf 

