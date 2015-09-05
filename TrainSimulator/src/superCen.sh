#!/bin/bash

#central Up line
cd ~/workspace/TrainSimulator/src/
bash finalMain.sh 10 101 10 centralEstUp.csv
mv allUsrGraph.pdf ~/Desktop/
cd ~/Desktop/
mv allUsrGraph.pdf 01_1_100cenUpGraph.pdf

cd ~/workspace/TrainSimulator/src/
bash finalMain.sh 100 3000 300 centralEstUp.csv
mv allUsrGraph.pdf ~/Desktop/
cd ~/Desktop/
mv allUsrGraph.pdf 02_100_3000cenUpGraph.pdf

cd ~/workspace/TrainSimulator/src/
bash finalMain.sh 2500 10000 500 centralEstUp.csv
mv allUsrGraph.pdf ~/Desktop/
cd ~/Desktop/
mv allUsrGraph.pdf 03_2500_10000cenUpGraph.pdf

pdftk *.pdf cat output cenUpGraph.pdf
mv cenUpGraph.pdf SimGraphs/
rm 0*.pdf

#central Down line
cd ~/workspace/TrainSimulator/src/
bash finalMain.sh 10 101 10 centralEstDown.csv
mv allUsrGraph.pdf ~/Desktop/
cd ~/Desktop/
mv allUsrGraph.pdf 01_1_100cenDownGraph.pdf

cd ~/workspace/TrainSimulator/src/
bash finalMain.sh 100 3000 300 centralEstDown.csv
mv allUsrGraph.pdf ~/Desktop/
cd ~/Desktop/
mv allUsrGraph.pdf 02_100_3000cenDownGraph.pdf

cd ~/workspace/TrainSimulator/src/
bash finalMain.sh 2500 10000 500 centralEstDown.csv
mv allUsrGraph.pdf ~/Desktop/
cd ~/Desktop/
mv allUsrGraph.pdf 03_2500_10000cenDownGraph.pdf

pdftk *.pdf cat output cenDownGraph.pdf
mv cenDownGraph.pdf SimGraphs/

rm 0*.pdf

