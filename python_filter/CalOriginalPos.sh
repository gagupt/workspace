#!/bin/bash

python pos.py output.csv western_up.csv western_down.csv Stopped_Western 10000
python pos.py output.csv central_up.csv central_down.csv Stopped_Central 10000
python pos.py output.csv harbour_up.csv harbour_down.csv Stopped_Harbour 10000
