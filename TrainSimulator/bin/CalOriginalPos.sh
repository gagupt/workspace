#!/bin/bash

python pos.py output.csv western_up.csv western_down.csv Stopped_Western $1
python pos.py output.csv central_up.csv central_down.csv Stopped_Central $1
python pos.py output.csv harbour_up.csv harbour_down.csv Stopped_Harbour $1
