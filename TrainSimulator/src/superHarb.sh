#!/bin/bash

#harbour Up line
cd ~/workspace/TrainSimulator/src/
bash finalMain.sh 10 101 10 harbourEstUp.csv
mv allUsrGraph.pdf ~/Desktop/
cd ~/Desktop/
mv allUsrGraph.pdf 01_1_100harUpGraph.pdf

cd ~/workspace/TrainSimulator/src/
bash finalMain.sh 100 3000 300 harbourEstUp.csv
mv allUsrGraph.pdf ~/Desktop/
cd ~/Desktop/
mv allUsrGraph.pdf 02_100_3000harUpGraph.pdf

cd ~/workspace/TrainSimulator/src/
bash finalMain.sh 2500 10000 500 harbourEstUp.csv
mv allUsrGraph.pdf ~/Desktop/
cd ~/Desktop/
mv allUsrGraph.pdf 03_2500_10000harUpGraph.pdf

pdftk *.pdf cat output harUpGraph.pdf
mv harUpGraph.pdf SimGraphs/

rm 0*.pdf

#harbour Down line
cd ~/workspace/TrainSimulator/src/
bash finalMain.sh 10 101 10 harbourEstDown.csv
mv allUsrGraph.pdf ~/Desktop/
cd ~/Desktop/
mv allUsrGraph.pdf 01_1_100harDownGraph.pdf

cd ~/workspace/TrainSimulator/src/
bash finalMain.sh 100 3000 300 harbourEstDown.csv
mv allUsrGraph.pdf ~/Desktop/
cd ~/Desktop/
mv allUsrGraph.pdf 02_100_3000harDownGraph.pdf

cd ~/workspace/TrainSimulator/src/
bash finalMain.sh 2500 10000 500 harbourEstDown.csv
mv allUsrGraph.pdf ~/Desktop/
cd ~/Desktop/
mv allUsrGraph.pdf 03_2500_10000harDownGraph.pdf

pdftk *.pdf cat output harDownGraph.pdf
mv harDownGraph.pdf SimGraphs/

rm 0*.pdf

