#!/bin/bash

python nearDist.py western_p_up.csv western_up.csv westernEstUp.csv westernTruUp.csv 1 $1 123780 western_down.csv 
python nearDist.py central_p_up.csv central_up.csv centralEstUp.csv centralTruUp.csv 1 $1 54000 central_down.csv
python nearDist.py harbour_p_up.csv harbour_up.csv harbourEstUp.csv harbourTruUp.csv 1 $1 49000 harbour_down.csv

python nearDist.py western_p_down.csv western_down.csv westernEstDown.csv westernTruDown.csv 0 $1 123780 western_up.csv
python nearDist.py central_p_down.csv central_down.csv centralEstDown.csv centralTruDown.csv 0 $1 54000 central_up.csv
python nearDist.py harbour_p_down.csv harbour_down.csv harbourEstDown.csv harbourTruDown.csv 0 $1 49000 harbour_up.csv
