import java.io.IOException;

public class Event_handler_Western {

	static void Arrival(int TrainNo, String station, double timestamp,
			String Direction) throws IOException {
		// TODO Auto-generated method stub

		MainActivity.SimTime = timestamp;

		System.out.println(Math.round(MainActivity.SimTime * 1000 / 1000)
				+ "\t" + TrainNo + "\t" + "Arrival_Western" + "\t" + station
				+ "\t" + Direction);
		MainActivity.EventList.add(new Event(TrainNo, "Stopped_Western",
				station, MainActivity.SimTime, Direction));

	}

	static void Stopped(int TrainNo, String station, double timestamp,
			String Direction) throws IOException {
		// TODO Auto-generated method stub

		MainActivity.SimTime = timestamp;
		// Finding index of current station
		int k1 = 0;
		int countWalkIn = 0;

		for (k1 = 0; k1 < Station.StationList_Western.size(); k1++) {
			if (station.equals(Station.StationList_Western.get(k1)
					.getStationName()))
				break;

		}
		int ii = 0;
		double distFromOriginMeter = 0;
		for (ii = 0; ii < Station.StationList_Western.size(); ii++) {
			distFromOriginMeter = distFromOriginMeter
					+ Station.StationList_Western.get(ii)
							.getNextStationDistance();
			if (station.equals(Station.StationList_Western.get(ii)
					.getStationName())) {

				break;

			}

		}
		// checking all passenger which have this source station

		for (int i = 0; i < Passenger.TotalNumOfPassenger_Western; i++) {
			// Finding index of source station
			int k2 = 0;
			if (Passenger.ListOfPassenger_Western.get(i).TrainNo == TrainNo)
				Passenger.ListOfPassenger_Western.get(i)
						.setCurrStation(station);

			for (k2 = 0; k2 < Station.StationList_Western.size(); k2++) {
				if (Passenger.ListOfPassenger_Western
						.get(i)
						.getDest()
						.equals(Station.StationList_Western.get(k2)
								.getStationName()))
					break;

			}
			// SANITY CHECK Trains.TrainMovingCounter_Western[TrainNo - 1]=k1;
			// System.out.println("k1= "+k1+"k2= "+k2);
			if (Passenger.ListOfPassenger_Western.get(i).getSrc()
					.equals(station)
					&& Passenger.ListOfPassenger_Western.get(i).TrainNo == -1
					&& (k2 > k1) && Direction == "up") {
				// add that passenger to train list
				Passenger.ListOfPassenger_Western.get(i).setTrainNo(TrainNo);
				Passenger.ListOfPassenger_Western.get(i).setStatus("InTrain");
				Trains.Trainlist_Western.get(TrainNo - 1).Train
						.add(Passenger.ListOfPassenger_Western.get(i));
				// Passengers telling us that they are boarding train

				Train_Spotting.Train_Spotting_List_Western
						.add(new Train_Spotting(
								Passenger.ListOfPassenger_Western.get(i).id,
								timestamp, "up", station, distFromOriginMeter,
								0, 0));
				// ........................
				countWalkIn++;
			} else if (Passenger.ListOfPassenger_Western.get(i).getSrc()
					.equals(station)
					&& Passenger.ListOfPassenger_Western.get(i).TrainNo == -1
					&& (k2 < k1) && Direction == "down") {
				// add that passenger to train list
				Passenger.ListOfPassenger_Western.get(i).setTrainNo(TrainNo);
				Passenger.ListOfPassenger_Western.get(i).setStatus("InTrain");
				Trains.Trainlist_Western.get(TrainNo - 1).Train
						.add(Passenger.ListOfPassenger_Western.get(i));
				// Passengers telling us that they are boarding train

				Train_Spotting.Train_Spotting_List_Western
						.add(new Train_Spotting(
								Passenger.ListOfPassenger_Western.get(i).id,
								timestamp, "down", station,
								distFromOriginMeter, 0, 0));
				// ........................

				countWalkIn++;
			}

		}

		// CHECK whether this is the destination of any passenger or not
		// and put down those passenger
		/*
		 * System.out.print("train->");
		 * 
		 * for (int i = 0; i < Trains.Trainlist.get(TrainNo - 1).Train.size();
		 * i++) { System.out.print(""+Trains.Trainlist.get(TrainNo -
		 * 1).Train.get(i) .getDest()+" ");
		 * System.out.print(Trains.Trainlist.get(TrainNo - 1).Train.get(i)
		 * .getId()+" "); } System.out.println();
		 */
		int countWalkOut = 0;
		for (int i = 0; i < Trains.Trainlist_Western.get(TrainNo - 1).Train
				.size(); i++) {

			if (station.equals(Trains.Trainlist_Western.get(TrainNo - 1).Train
					.get(i).getDest())
					&& Trains.Trainlist_Western.get(TrainNo - 1).Train.get(i).TrainNo != -1) {
				// System.out.println("train num= "+Trains.Trainlist_Western.get(TrainNo
				// - 1).Train
				// .get(i).TrainNo);
				// System.out.println("Remove "+
				// Trains.Trainlist.get(TrainNo - 1).Train.get(i).id);
				Trains.Trainlist_Western.get(TrainNo - 1).Train.get(i)
						.setTrainNo(-1);
				// Passenger.ListOfPassenger_Western.get(i).setStatus("OnStation");
				// Passenger.ListOfPassenger_Western.get(i).setSrc(station);
				// Assigning next source and destination.....
				Uniform uniSrc = null, uniDest = null;
				int Src, Dest;

				uniSrc = new Uniform(0, 35);
				Src = (int) uniSrc.nextDouble();
				Trains.Trainlist_Western.get(TrainNo - 1).Train.get(i).setSrc(
						Station.StationList_Western.get(Src).StationName);

				Trains.Trainlist_Western.get(TrainNo - 1).Train
						.get(i)
						.setCurrStation(
								Station.StationList_Western.get(Src).StationName);

				Trains.Trainlist_Western.get(TrainNo - 1).Train.get(i)
						.setArrTime(timestamp);
				Trains.Trainlist_Western.get(TrainNo - 1).Train.get(i)
						.setStatus("OnStation");
				uniDest = new Uniform(0, 35);
				Dest = (int) uniDest.nextDouble();
				while (Src == Dest) {
					uniDest = new Uniform(0, 35);
					Dest = (int) uniDest.nextDouble();

				}
				Trains.Trainlist_Western.get(TrainNo - 1).Train.get(i).setDest(
						Station.StationList_Western.get(Dest).StationName);

				Trains.Trainlist_Western.get(TrainNo - 1).Train.remove(i);
				countWalkOut++;

			}
		}

		System.out
				.println(Math.round(MainActivity.SimTime * 1000 / 1000)
						+ "\t"
						+ TrainNo
						+ "\t"
						+ "Stopped_Western"
						+ "\t"
						+ station
						+ "\t"
						+ Direction
						+ "\t"
						+ countWalkIn++
						+ "\t"
						+ countWalkOut
						+ "\t"
						+ Trains.Trainlist_Western.get(TrainNo - 1).Train
								.size());

		MainActivity.EventList.add(new Event(TrainNo, "Departure_Western",
				station, MainActivity.SimTime + Trains.Halt_time_of_Train,
				Direction));

	}

	static void Departure(int TrainNo, String station, double timestamp,
			String Direction) throws IOException {
		// TODO Auto-generated method stub

		MainActivity.SimTime = timestamp;

		System.out.println(Math.round(MainActivity.SimTime * 1000 / 1000)
				+ "\t" + TrainNo + "\t" + "Departure_Western" + "\t" + station
				+ "\t" + Direction);

		// System.out.println(Trains.TrainMovingCounter[TrainNo - 1]);
		if (Direction == "up") {
			if (Trains.TrainMovingCounter_Western[TrainNo - 1] == 35) {
				// System.out.println("Hello");
				Direction = "down";
				double nxtStationDis = Station.StationList_Western
						.get(Trains.TrainMovingCounter_Western[TrainNo - 1]).NextStationDistance;

				double nxtStationTime = nxtStationDis
						/ Trains.Speed_of_The_Train;

				MainActivity.EventList
						.add(new Event(
								TrainNo,
								"Arrival_Western",
								Station.StationList_Western
										.get(Trains.TrainMovingCounter_Western[TrainNo - 1] - 1).StationName,
								MainActivity.SimTime + nxtStationTime, "down"));

			} else {
				Trains.TrainMovingCounter_Western[TrainNo - 1]++;
				double nxtStationDis = Station.StationList_Western
						.get(Trains.TrainMovingCounter_Western[TrainNo - 1]).NextStationDistance;

				double nxtStationTime = nxtStationDis
						/ Trains.Speed_of_The_Train;

				MainActivity.EventList
						.add(new Event(
								TrainNo,
								"Arrival_Western",
								Station.StationList_Western
										.get(Trains.TrainMovingCounter_Western[TrainNo - 1]).StationName,
								MainActivity.SimTime + nxtStationTime, "up"));

			}

		}

		else if (Direction == "down") {
			if (Trains.TrainMovingCounter_Western[TrainNo - 1] == 1) {
				Direction = "up";
				// System.out.println(Station.StationList.get(Trains.
				// TrainMovingCounter[TrainNo - 1]).StationName);
				double nxtStationDis = Station.StationList_Western
						.get(Trains.TrainMovingCounter_Western[TrainNo - 1]).NextStationDistance;

				double nxtStationTime = nxtStationDis
						/ Trains.Speed_of_The_Train;

				MainActivity.EventList
						.add(new Event(
								TrainNo,
								"Arrival_Western",
								Station.StationList_Western
										.get(Trains.TrainMovingCounter_Western[TrainNo - 1]).StationName,
								MainActivity.SimTime + nxtStationTime, "up"));

			} else {
				Trains.TrainMovingCounter_Western[TrainNo - 1]--;
				double nxtStationDis = Station.StationList_Western
						.get(Trains.TrainMovingCounter_Western[TrainNo - 1]).NextStationDistance;

				double nxtStationTime = nxtStationDis
						/ Trains.Speed_of_The_Train;

				MainActivity.EventList
						.add(new Event(
								TrainNo,
								"Arrival_Western",
								Station.StationList_Western
										.get(Trains.TrainMovingCounter_Western[TrainNo - 1] - 1).StationName,
								MainActivity.SimTime + nxtStationTime, "down"));

			}
		}

	}

	public static void Initialization() {
		// scheduling first arrival

		for (int i = 0, j = 0; i < Trains.NumOfTrains_Western; j = j + 50) {
			MainActivity.EventList
					.add(new Event(
							i + 1,
							"Arrival_Western",
							Station.StationList_Western
									.get(Trains.TrainMovingCounter_Western[i]).StationName,
							0 + j, "up"));
			i++;
			if (Trains.NumOfTrains_Western == 1)
				break;
			Trains.TrainMovingCounter_Western[i] = 35;
			MainActivity.EventList
					.add(new Event(
							i + 1,
							"Arrival_Western",
							Station.StationList_Western
									.get(Trains.TrainMovingCounter_Western[i]).StationName,
							0 + j, "down"));
			i++;

		}
	}

	public static void AssignStationToPassenger() {
		// adding stations to passenger randomly
		// ...........
		for (int i = 0; i < Passenger.TotalNumOfPassenger_Western; i++) {

			Uniform uniSrc = null, uniDest = null;
			int Src, Dest;

			uniSrc = new Uniform(0, 35);
			Src = (int) uniSrc.nextDouble();

			Passenger.ListOfPassenger_Western.get(i).setSrc(
					Station.StationList_Western.get(Src).StationName);
			Passenger.ListOfPassenger_Western.get(i).setCurrStation(
					Station.StationList_Western.get(Src).StationName);

			Passenger.ListOfPassenger_Western.get(i).setArrTime(0);
			Passenger.ListOfPassenger_Western.get(i).setStatus("OnStation");
			uniDest = new Uniform(0, 35);
			Dest = (int) uniDest.nextDouble();
			while (Src == Dest) {
				uniDest = new Uniform(0, 35);
				Dest = (int) uniDest.nextDouble();

			}
			Passenger.ListOfPassenger_Western.get(i).setDest(
					Station.StationList_Western.get(Dest).StationName);

		}

	}

	static double getConfidenceFromPast(double t, double nowtime) {
		double diffSec = nowtime - t;
		//System.out.println("difftime=" + diffSec);
		if (diffSec < 10)
			return 1;
		if (diffSec < 50)
			return 0.9;
		if (diffSec < 150)
			return 0.8;
		if (diffSec < 300)
			return 0.6;
		if (diffSec < 600)
			return 0.3;
		if (diffSec >= 2400)
			return 0;
		return (0.3 - 0.3 * diffSec / (2400));
	}

	static double getConfidenceFromFarSpotting(double diffKm) {
		if (diffKm < 100)
			return 1;
		if (diffKm < 200)
			return 0.9;
		if (diffKm < 400)
			return 0.8;
		if (diffKm < 800)
			return 0.6;
		if (diffKm < 1200)
			return 0.3;
		if (diffKm >= 2000)
			return 0;
		return (0.3 - 0.3 * diffKm / (2000));
	}

	public static void getSpottingsNow(double nowtime) {
		// TODO Auto-generated method stub
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
						dir = "down";
						k1 = m-1;
					} else {
						break;
					}
				}
				// System.out.println("m="+m);

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
			Train_Spotting.Train_Spotting_List_Western.get(i).setDistNow(
					distNow);

		}
		// computePosnConf..................
		double dist = 0;
		double inrc = 100;
		double Posnconf;
		int NumUserInputs;
		while (dist < 123780) {

			Posnconf = 0;
			NumUserInputs = 0;
			for (int j = 0; j < Train_Spotting.Train_Spotting_List_Western
					.size(); j++) {
				double distEach = Train_Spotting.Train_Spotting_List_Western
						.get(j).DistNow;

				if (Math.abs(dist - distEach) < 2000) {
					double confDist = getConfidenceFromFarSpotting(Math
							.abs(dist - distEach));
					double overallConf = Train_Spotting.Train_Spotting_List_Western
							.get(j).Confidence * confDist;
					Posnconf += (1 - Posnconf) * overallConf;
					NumUserInputs++;
				}

			}
			if (Posnconf != 0)
				PosnConf.PosnConfidnce_List_Western.add(new PosnConf(dist,
						Posnconf, NumUserInputs, true));
			dist += inrc;
		}

		for (int i = 0; i < PosnConf.PosnConfidnce_List_Western.size(); i++) {

			double confAdj4NumUsers = getConfidence4NumUsers(PosnConf.PosnConfidnce_List_Western
					.get(i).NumUserInputs);
			double posnconf = PosnConf.PosnConfidnce_List_Western.get(i).PosnConfidence;
			posnconf *= confAdj4NumUsers;
			PosnConf.PosnConfidnce_List_Western.get(i).setPosnConfidence(
					posnconf);

		}
		// computeConfidencePeaks.................
		int jStart,jEnd;
		for (int i = 0; i < PosnConf.PosnConfidnce_List_Western
				.size(); i++) {
			if(i < PosnConf.peakThres)
				jStart=0;
			else
				jStart=i-PosnConf.peakThres;
			if(i + PosnConf.peakThres>=PosnConf.PosnConfidnce_List_Western
					.size())
				jEnd=PosnConf.PosnConfidnce_List_Western
						.size()-1;
			else
				jEnd=i+PosnConf.peakThres;
			
			for (int j = jStart; j <= jEnd; j++) {
				if (PosnConf.PosnConfidnce_List_Western.get(i).PosnConfidence < PosnConf.PosnConfidnce_List_Western
						.get(j).PosnConfidence) {
					PosnConf.PosnConfidnce_List_Western.get(i).setPeak(false);
					break;
				}
			}
		}
		
	}

	static double getConfidence4NumUsers(int N) {
		return (1 - 1.0 / Math.pow(2, N - 1)); // this gives 0 confidence for 1
												// user, tending toward 1 for
												// large N
	} // End getConfidence4NumUsers()

}