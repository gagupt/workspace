#!/bin/bash

python nearDist.py western_p_up.csv western_up.csv westernEstUp.csv westernTruUp.csv
python nearDist.py central_p_up.csv central_up.csv centralEstUp.csv centralTruUp.csv
python nearDist.py harbour_p_up.csv harbour_up.csv harbourEstUp.csv harbourTruUp.csv

python nearDist.py western_p_down.csv western_down.csv westernEstDown.csv westernTruDown.csv
python nearDist.py central_p_down.csv central_down.csv centralEstDown.csv centralTruDown.csv
python nearDist.py harbour_p_down.csv harbour_down.csv harbourEstDown.csv harbourTruDown.csv
