#!/bin/bash

python python_filter.py PosnCOnf_western_Up.csv western_true_up.csv 
python peak.py western_true_up.csv western_p_up.csv 
rm western_true_up.csv
python python_filter.py PosnCOnf_western_Down.csv western_true_down.csv 
python peak.py western_true_down.csv western_p_down.csv 
rm western_true_down.csv

python python_filter.py PosnCOnf_central_Up.csv central_true_up.csv 
python peak.py central_true_up.csv central_p_up.csv 
rm central_true_up.csv
python python_filter.py PosnCOnf_central_Down.csv central_true_down.csv 
python peak.py central_true_down.csv central_p_down.csv 
rm central_true_down.csv


python python_filter.py PosnCOnf_harbour_Up.csv harbour_true_up.csv 
python peak.py harbour_true_up.csv harbour_p_up.csv 
rm harbour_true_up.csv
python python_filter.py PosnCOnf_harbour_Down.csv harbour_true_down.csv 
python peak.py harbour_true_down.csv harbour_p_down.csv 
rm harbour_true_down.csv
