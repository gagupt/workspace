ArrayList<Train_Spotting> Train_Spotting_List_Western_t = new ArrayList<Train_Spotting>(
				10000);
	
		 
		for(Train_Spotting obj:Train_Spotting.Train_Spotting_List_Western)
		{
			Train_Spotting_List_Western_t.add(obj);
		}
	
		for (int i = 0; i < Train_Spotting.Train_Spotting_List_Western.size(); i++) {

			double t = Train_Spotting.Train_Spotting_List_Western.get(i).Timestamp;

			Train_Spotting.Train_Spotting_List_Western.get(i).setConfidence(
					getConfidenceFromPast(t, nowtime));

			// calculating number of stations between nowTime and t
			String station = Train_Spotting.Train_Spotting_List_Western.get(i).Station;
			int k1 = 0;
			for (k1 = 0; k1 < Station.StationList_Western.size(); k1++) {
				if (station.equals(Station.StationList_Western.get(k1)
						.getStationName()))
					break;

			}
			String dir = Train_Spotting.Train_Spotting_List_Western.get(i).Direction;
			double timeBetweenStations;
			double distOffset = 0;
			double timetoTravel = nowtime - t;
			// System.out.println("timetoTravel="+timetoTravel);
			int m = 0;
		//	int dir_flag = 0;
		
			while (true) {
				if (dir == "up") {
					for (m = k1 + 1; m <= 35; m++) {
						double dist = Station.StationList_Western.get(m).NextStationDistance;
						timeBetweenStations = dist / Trains.Speed_of_The_Train
								+ Trains.Halt_time_of_Train;
						if (timetoTravel <= timeBetweenStations) {
							distOffset = timetoTravel
									* Trains.Speed_of_The_Train;
							break;
						} else {
							timetoTravel -= timeBetweenStations;
						}
					}
					if (m > 35) {
					//	dir_flag++;
						dir = "down";
						k1 = m - 1;
					} else {
						break;
					}
				}
				 System.out.println("m="+m);
				//System.out.println("k="+k1);
				if (dir == "down") {
					for (m = k1; m >= 0; m--) {
						double dist = Station.StationList_Western.get(m).NextStationDistance;
						timeBetweenStations = dist / Trains.Speed_of_The_Train
								+ Trains.Halt_time_of_Train;
						if (timetoTravel <= timeBetweenStations) {
							distOffset = -1 * timetoTravel
									* Trains.Speed_of_The_Train;
							break;
						} else {
							timetoTravel -= timeBetweenStations;
						}
					}
					if (m < 0) {
						dir = "up";
						//dir_flag++;
						k1 = m;
					} else {
						break;
					}
				}

			}
			if (dir == "up")
				m--;
			// System.out.println("k=" + k1 + "m=" + m + "station=" + station
			// + "distOffset=" + distOffset);
			// double total_run_dist=timetoTravel*Trains.Speed_of_The_Train
			// +station_count*Trains.Halt_time_of_Train;
			// int dir_flag=(int)total_run_dist/123780;
			
			int ii = 0;
			double distNow = 0;
			for (ii = 0; ii < Station.StationList_Western.size(); ii++) {
				distNow = distNow
						+ Station.StationList_Western.get(ii)
								.getNextStationDistance();
				if (m == ii)
					break;
			}
			//
			distNow += distOffset;
			
			/*
			for(int kk=0;kk<Station.StationList_Western.size();kk++){
				System.out.print(Station.StationList_Western.get(kk).getNextStationDistance());
			}
			System.out.println();
			*/
			//System.out.println("dist="+distNow+" "+"dir="+dir);
			Train_Spotting.Train_Spotting_List_Western.get(i).setDistNow(
					distNow);
			// if(dir_flag%2==1){
			// if(Train_Spotting.Train_Spotting_List_Western.get(i).Direction=="up"){

			Train_Spotting.Train_Spotting_List_Western.get(i).setDirection(dir);

			// }
			// else{
			// Train_Spotting.Train_Spotting_List_Western.get(i).setDirection("up");

			// }
			// }
		}
		Train_Spotting.Train_Spotting_List_Western.clear();
		for(Train_Spotting obj:Train_Spotting_List_Western_t)
		{
			Train_Spotting.Train_Spotting_List_Western.add(obj);
		}
