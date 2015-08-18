import java.io.IOException;

public class Event_handler_Harbour {

	static void Arrival(int TrainNo, String station, double timestamp,
			String Direction) throws IOException {
		// TODO Auto-generated method stub

		MainActivity.SimTime = timestamp;

		System.out.println(Math.round(MainActivity.SimTime * 1000 / 1000)
				+ "\t" + TrainNo + "\t" + "Arrival_Harbour" + "\t" + station
				+ "\t" + Direction);
		MainActivity.EventList.add(new Event(TrainNo, "Stopped_Harbour",
				station, MainActivity.SimTime, Direction));

	}

	static void Stopped(int TrainNo, String station, double timestamp,
			String Direction) throws IOException {
		// TODO Auto-generated method stub

		MainActivity.SimTime = timestamp;
		// Finding index of current station
		int k1 = 0;
		int countWalkIn = 0;

		for (k1 = 0; k1 < Station.StationList_Harbour.size(); k1++) {
			if (station.equals(Station.StationList_Harbour.get(k1)
					.getStationName()))
				break;

		}
		int ii = 0;
		double distFromOriginMeter = 0;
		for (ii = 0; ii < Station.StationList_Harbour.size(); ii++) {
			distFromOriginMeter = distFromOriginMeter
					+ Station.StationList_Harbour.get(ii)
							.getNextStationDistance();
			if (station.equals(Station.StationList_Harbour.get(ii)
					.getStationName())) {

				break;

			}

		}
		// checking all passenger which have this source station

		for (int i = 0; i < Passenger.TotalNumOfPassenger_Harbour; i++) {
			// Finding index of source station
			int k2 = 0;
			if (Passenger.ListOfPassenger_Harbour.get(i).TrainNo == TrainNo)
				Passenger.ListOfPassenger_Harbour.get(i)
						.setCurrStation(station);

			for (k2 = 0; k2 < Station.StationList_Harbour.size(); k2++) {
				if (Passenger.ListOfPassenger_Harbour
						.get(i)
						.getDest()
						.equals(Station.StationList_Harbour.get(k2)
								.getStationName()))
					break;

			}
			// SANITY CHECK Trains.TrainMovingCounter_Harbour[TrainNo - 1]=k1;
			// System.out.println("k1= "+k1+"k2= "+k2);
			if (Passenger.ListOfPassenger_Harbour.get(i).getSrc()
					.equals(station)
					&& Passenger.ListOfPassenger_Harbour.get(i).TrainNo == -1
					&& (k2 > k1) && Direction == "up") {
				// add that passenger to train list
				Passenger.ListOfPassenger_Harbour.get(i).setTrainNo(TrainNo);
				Passenger.ListOfPassenger_Harbour.get(i).setStatus("InTrain");
				Trains.Trainlist_Harbour.get(TrainNo - 1).Train
						.add(Passenger.ListOfPassenger_Harbour.get(i));
				// Passengers telling us that they are boarding train

				Train_Spotting.Train_Spotting_List_Harbour
						.add(new Train_Spotting(
								Passenger.ListOfPassenger_Harbour.get(i).id,
								timestamp, "up",station, distFromOriginMeter, 0, 0, 0,
								0, true));
				// ........................
				countWalkIn++;
			} else if (Passenger.ListOfPassenger_Harbour.get(i).getSrc()
					.equals(station)
					&& Passenger.ListOfPassenger_Harbour.get(i).TrainNo == -1
					&& (k2 < k1) && Direction == "down") {
				// add that passenger to train list
				Passenger.ListOfPassenger_Harbour.get(i).setTrainNo(TrainNo);
				Passenger.ListOfPassenger_Harbour.get(i).setStatus("InTrain");
				Trains.Trainlist_Harbour.get(TrainNo - 1).Train
						.add(Passenger.ListOfPassenger_Harbour.get(i));
				// Passengers telling us that they are boarding train

				Train_Spotting.Train_Spotting_List_Harbour
						.add(new Train_Spotting(
								Passenger.ListOfPassenger_Harbour.get(i).id,
								timestamp, "up",station, distFromOriginMeter, 0, 0, 0,
								0, true));
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
		for (int i = 0; i < Trains.Trainlist_Harbour.get(TrainNo - 1).Train
				.size(); i++) {

			if (station.equals(Trains.Trainlist_Harbour.get(TrainNo - 1).Train
					.get(i).getDest())
					&& Trains.Trainlist_Harbour.get(TrainNo - 1).Train.get(i).TrainNo != -1) {
				// System.out.println("train num= "+Trains.Trainlist_Harbour.get(TrainNo
				// - 1).Train
				// .get(i).TrainNo);
				// System.out.println("Remove "+
				// Trains.Trainlist.get(TrainNo - 1).Train.get(i).id);
				Trains.Trainlist_Harbour.get(TrainNo - 1).Train.get(i)
						.setTrainNo(-1);
				// Passenger.ListOfPassenger_Harbour.get(i).setStatus("OnStation");
				// Passenger.ListOfPassenger_Harbour.get(i).setSrc(station);
				// Assigning next source and destination.....
				Uniform uniSrc = null, uniDest = null;
				int Src, Dest;

				uniSrc = new Uniform(0, 24);
				Src = (int) uniSrc.nextDouble();
				Trains.Trainlist_Harbour.get(TrainNo - 1).Train.get(i).setSrc(
						Station.StationList_Harbour.get(Src).StationName);

				Trains.Trainlist_Harbour.get(TrainNo - 1).Train
						.get(i)
						.setCurrStation(
								Station.StationList_Harbour.get(Src).StationName);

				Trains.Trainlist_Harbour.get(TrainNo - 1).Train.get(i)
						.setArrTime(timestamp);
				Trains.Trainlist_Harbour.get(TrainNo - 1).Train.get(i)
						.setStatus("OnStation");
				uniDest = new Uniform(0, 24);
				Dest = (int) uniDest.nextDouble();
				while (Src == Dest) {
					uniDest = new Uniform(0, 24);
					Dest = (int) uniDest.nextDouble();

				}
				Trains.Trainlist_Harbour.get(TrainNo - 1).Train.get(i).setDest(
						Station.StationList_Harbour.get(Dest).StationName);

				Trains.Trainlist_Harbour.get(TrainNo - 1).Train.remove(i);
				countWalkOut++;

			}
		}

		System.out
				.println(Math.round(MainActivity.SimTime * 1000 / 1000)
						+ "\t"
						+ TrainNo
						+ "\t"
						+ "Stopped_Harbour"
						+ "\t"
						+ station
						+ "\t"
						+ Direction
						+ "\t"
						+ countWalkIn++
						+ "\t"
						+ countWalkOut
						+ "\t"
						+ Trains.Trainlist_Harbour.get(TrainNo - 1).Train
								.size());

		MainActivity.EventList.add(new Event(TrainNo, "Departure_Harbour",
				station, MainActivity.SimTime + Trains.Halt_time_of_Train,
				Direction));

	}

	static void Departure(int TrainNo, String station, double timestamp,
			String Direction) throws IOException {
		// TODO Auto-generated method stub

		MainActivity.SimTime = timestamp;

		System.out.println(Math.round(MainActivity.SimTime * 1000 / 1000)
				+ "\t" + TrainNo + "\t" + "Departure_Harbour" + "\t" + station
				+ "\t" + Direction);

		// System.out.println(Trains.TrainMovingCounter[TrainNo - 1]);
		if (Direction == "up") {
			if (Trains.TrainMovingCounter_Harbour[TrainNo - 1] == 24) {
				// System.out.println("Hello");
				Direction = "down";
				double nxtStationDis = Station.StationList_Harbour
						.get(Trains.TrainMovingCounter_Harbour[TrainNo - 1]).NextStationDistance;

				double nxtStationTime = nxtStationDis
						/ Trains.Speed_of_The_Train;

				MainActivity.EventList
						.add(new Event(
								TrainNo,
								"Arrival_Harbour",
								Station.StationList_Harbour
										.get(Trains.TrainMovingCounter_Harbour[TrainNo - 1] - 1).StationName,
								MainActivity.SimTime + nxtStationTime, "down"));

			} else {
				Trains.TrainMovingCounter_Harbour[TrainNo - 1]++;
				double nxtStationDis = Station.StationList_Harbour
						.get(Trains.TrainMovingCounter_Harbour[TrainNo - 1]).NextStationDistance;

				double nxtStationTime = nxtStationDis
						/ Trains.Speed_of_The_Train;

				MainActivity.EventList
						.add(new Event(
								TrainNo,
								"Arrival_Harbour",
								Station.StationList_Harbour
										.get(Trains.TrainMovingCounter_Harbour[TrainNo - 1]).StationName,
								MainActivity.SimTime + nxtStationTime, "up"));

			}

		}

		else if (Direction == "down") {
			if (Trains.TrainMovingCounter_Harbour[TrainNo - 1] == 1) {
				Direction = "up";
				// System.out.println(Station.StationList.get(Trains.
				// TrainMovingCounter[TrainNo - 1]).StationName);
				double nxtStationDis = Station.StationList_Harbour
						.get(Trains.TrainMovingCounter_Harbour[TrainNo - 1]).NextStationDistance;

				double nxtStationTime = nxtStationDis
						/ Trains.Speed_of_The_Train;

				MainActivity.EventList
						.add(new Event(
								TrainNo,
								"Arrival_Harbour",
								Station.StationList_Harbour
										.get(Trains.TrainMovingCounter_Harbour[TrainNo - 1]).StationName,
								MainActivity.SimTime + nxtStationTime, "up"));

			} else {
				Trains.TrainMovingCounter_Harbour[TrainNo - 1]--;
				double nxtStationDis = Station.StationList_Harbour
						.get(Trains.TrainMovingCounter_Harbour[TrainNo - 1]).NextStationDistance;

				double nxtStationTime = nxtStationDis
						/ Trains.Speed_of_The_Train;

				MainActivity.EventList
						.add(new Event(
								TrainNo,
								"Arrival_Harbour",
								Station.StationList_Harbour
										.get(Trains.TrainMovingCounter_Harbour[TrainNo - 1] - 1).StationName,
								MainActivity.SimTime + nxtStationTime, "down"));

			}
		}

	}

	public static void Initialization() {
		// scheduling first arrival

		for (int i = 0, j = 0; i < Trains.NumOfTrains_Harbour; j = j + 50) {
			MainActivity.EventList
					.add(new Event(
							i + 1,
							"Arrival_Harbour",
							Station.StationList_Harbour
									.get(Trains.TrainMovingCounter_Harbour[i]).StationName,
							0 + j, "up"));
			i++;
			if (Trains.NumOfTrains_Harbour == 1)
				break;
			Trains.TrainMovingCounter_Harbour[i] = 24;
			MainActivity.EventList
					.add(new Event(
							i + 1,
							"Arrival_Harbour",
							Station.StationList_Harbour
									.get(Trains.TrainMovingCounter_Harbour[i]).StationName,
							0 + j, "down"));
			i++;

		}
	}

	public static void AssignStationToPassenger() {
		// adding stations to passenger randomly
		// ...........
		for (int i = 0; i < Passenger.TotalNumOfPassenger_Harbour; i++) {

			Uniform uniSrc = null, uniDest = null;
			int Src, Dest;

			uniSrc = new Uniform(0, 24);
			Src = (int) uniSrc.nextDouble();

			Passenger.ListOfPassenger_Harbour.get(i).setSrc(
					Station.StationList_Harbour.get(Src).StationName);
			Passenger.ListOfPassenger_Harbour.get(i).setCurrStation(
					Station.StationList_Harbour.get(Src).StationName);

			Passenger.ListOfPassenger_Harbour.get(i).setArrTime(0);
			Passenger.ListOfPassenger_Harbour.get(i).setStatus("OnStation");
			uniDest = new Uniform(0, 24);
			Dest = (int) uniDest.nextDouble();
			while (Src == Dest) {
				uniDest = new Uniform(0, 24);
				Dest = (int) uniDest.nextDouble();

			}
			Passenger.ListOfPassenger_Harbour.get(i).setDest(
					Station.StationList_Harbour.get(Dest).StationName);

		}

	}

	static double getConfidenceFromPast(double t, double nowtime) {
		double diffMin = nowtime - t;
		if (diffMin < 10)
			return 1;
		if (diffMin < 50)
			return 0.9;
		if (diffMin < 150)
			return 0.8;
		if (diffMin < 300)
			return 0.6;
		if (diffMin < 600)
			return 0.3;
		if (diffMin > 2400)
			return 0;
		return (0.3 - 0.3 * diffMin / (2400 - 600));
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
		if (diffKm > 2000)
			return 0;
		return (0.3 - 0.3 * diffKm / (2000 - 1200));
	}

	public static void getSpottingsNow(double nowtime) {
		// TODO Auto-generated method stub
		for (int i = 0; i < Train_Spotting.Train_Spotting_List_Harbour.size(); i++) {

			double t = Train_Spotting.Train_Spotting_List_Harbour.get(i).Timestamp;

			Train_Spotting.Train_Spotting_List_Harbour.get(i).setConfidence(
					getConfidenceFromPast(t, nowtime));
			double distAtSpotting = Train_Spotting.Train_Spotting_List_Harbour
					.get(i).DistFromOriginMeter;

			double distSinceSpotting = Trains.Speed_of_The_Train
					* (nowtime - t);
			double distNow = 0;
			if (Train_Spotting.Train_Spotting_List_Harbour.get(i).Direction == "up") {
				distNow = distAtSpotting + distSinceSpotting;
				if (distNow > 49000) {
					distNow = 49000 - (distNow - 49000) % 49000;
				}

			}
			if (Train_Spotting.Train_Spotting_List_Harbour.get(i).Direction == "down") {
				distNow = distAtSpotting - distSinceSpotting;
				if (distNow < 0) {
					distNow = (-1 * distNow) % 49000;
				}

			}
			Train_Spotting.Train_Spotting_List_Harbour.get(i).setDistNow(
					distNow);

		}
		// computePosnConf..................

		for (int i = 0; i < Train_Spotting.Train_Spotting_List_Harbour.size(); i++) {
			double dist = Train_Spotting.Train_Spotting_List_Harbour.get(i).DistNow;
			for (int j = 0; j < Train_Spotting.Train_Spotting_List_Harbour
					.size(); j++) {
				double distEach = Train_Spotting.Train_Spotting_List_Harbour
						.get(j).DistNow;

				if (Math.abs(dist - distEach) < 2000) {
					double confDist = getConfidenceFromFarSpotting(Math
							.abs(dist - distEach));
					double overallConf = Train_Spotting.Train_Spotting_List_Harbour
							.get(i).Confidence * confDist;
					double PosnConf = Train_Spotting.Train_Spotting_List_Harbour
							.get(j).PosnConf;
					PosnConf += (1 - PosnConf) * overallConf;
					Train_Spotting.Train_Spotting_List_Harbour.get(j)
							.setPosnConf(PosnConf);
					int NumUserInputs = Train_Spotting.Train_Spotting_List_Harbour
							.get(j).NumUserInputs;
					Train_Spotting.Train_Spotting_List_Harbour.get(j)
							.setNumUserInputs(NumUserInputs + 1);

				}
			}

		}
		for (int i = 0; i < Train_Spotting.Train_Spotting_List_Harbour.size(); i++) {

			double confAdj4NumUsers = getConfidence4NumUsers(Train_Spotting.Train_Spotting_List_Harbour
					.get(i).NumUserInputs);
			double PosnConf = Train_Spotting.Train_Spotting_List_Harbour.get(i).Confidence;
			PosnConf *= confAdj4NumUsers;
			Train_Spotting.Train_Spotting_List_Harbour.get(i).setPosnConf(
					PosnConf);

		}
		// computeConfidencePeaks.................
		
		
		for (int i = 0; i < Train_Spotting.peakThres; i++) {

			for (int j = 0; j < Train_Spotting.peakThres; j++) {
				if (Train_Spotting.Train_Spotting_List_Harbour.get(i).PosnConf < Train_Spotting.Train_Spotting_List_Harbour
						.get(j).PosnConf) {
					Train_Spotting.Train_Spotting_List_Harbour.get(i).setPeak(
							false);
					break;
				}
			}
		}

		for (int i = Train_Spotting.peakThres; i < Train_Spotting.Train_Spotting_List_Harbour
				.size() - Train_Spotting.peakThres; i++) {

			for (int j = i - Train_Spotting.peakThres; j <= i
					+ Train_Spotting.peakThres; j++) {
				if (Train_Spotting.Train_Spotting_List_Harbour.get(i).PosnConf < Train_Spotting.Train_Spotting_List_Harbour
						.get(j).PosnConf) {
					Train_Spotting.Train_Spotting_List_Harbour.get(i).setPeak(
							false);
					break;
				}
			}
		}
		for (int i = Train_Spotting.Train_Spotting_List_Harbour.size()
				- Train_Spotting.peakThres; i < Train_Spotting.Train_Spotting_List_Harbour
				.size(); i++) {

			for (int j = Train_Spotting.Train_Spotting_List_Harbour.size()
					- Train_Spotting.peakThres; j < Train_Spotting.Train_Spotting_List_Harbour
					.size() -  Train_Spotting.peakThres; j++) {
				if (Train_Spotting.Train_Spotting_List_Harbour.get(i).PosnConf < Train_Spotting.Train_Spotting_List_Harbour
						.get(j).PosnConf) {
					Train_Spotting.Train_Spotting_List_Harbour.get(i).setPeak(
							false);
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