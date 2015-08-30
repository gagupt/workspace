#!/bin/bash
mv *.csv random/
cd random/
pdflatex report.tex </dev/null>/dev/null 2>&1
evince report.pdf 
