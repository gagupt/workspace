#!/bin/bash

#Western Up line
cd ~/workspace/TrainSimulator/src/
bash finalMain.sh 10 101 10 westernEstUp.csv
mv allUsrGraph.pdf ~/Desktop/
cd ~/Desktop/
mv allUsrGraph.pdf 01_1_100wesUpGraph.pdf

#cd ~/workspace/TrainSimulator/src/
#bash finalMain.sh 100 3000 300 westernEstUp.csv
#mv allUsrGraph.pdf ~/Desktop/
#cd ~/Desktop/
#mv allUsrGraph.pdf 02_100_3000wesUpGraph.pdf

#cd ~/workspace/TrainSimulator/src/
#bash finalMain.sh 2500 10000 500 westernEstUp.csv
#mv allUsrGraph.pdf ~/Desktop/
#cd ~/Desktop/
#mv allUsrGraph.pdf 03_2500_10000wesUpGraph.pdf

pdftk *.pdf cat output wesUpGraph.pdf
mv wesUpGraph.pdf SimGraphs/
rm 0*.pdf

#Western Down line
cd ~/workspace/TrainSimulator/src/
bash finalMain.sh 10 101 10 westernEstDown.csv
mv allUsrGraph.pdf ~/Desktop/
cd ~/Desktop/
mv allUsrGraph.pdf 01_1_100wesDownGraph.pdf

#cd ~/workspace/TrainSimulator/src/
#bash finalMain.sh 100 3000 300 westernEstDown.csv
#mv allUsrGraph.pdf ~/Desktop/
#cd ~/Desktop/
#mv allUsrGraph.pdf 02_100_3000wesDownGraph.pdf

#cd ~/workspace/TrainSimulator/src/
#bash finalMain.sh 2500 10000 500 westernEstDown.csv
#mv allUsrGraph.pdf ~/Desktop/
#cd ~/Desktop/
#mv allUsrGraph.pdf 03_2500_10000wesDownGraph.pdf

pdftk *.pdf cat output wesDownGraph.pdf
mv wesDownGraph.pdf SimGraphs/
rm 0*.pdf

