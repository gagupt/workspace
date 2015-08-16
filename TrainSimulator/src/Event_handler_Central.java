import java.io.IOException;

public class Event_handler_Central {

	static void Arrival(int TrainNo, String station, double timestamp,
			String Direction) throws IOException {
		// TODO Auto-generated method stub

		MainActivity.SimTime = timestamp;

		System.out.println(Math.round(MainActivity.SimTime * 1000 / 1000)
				+ "\t" + TrainNo + "\t" + "Arrival_Central" + "\t" + station
				+ "\t" + Direction);
		MainActivity.EventList.add(new Event(TrainNo, "Stopped_Central",
				station, MainActivity.SimTime, Direction));

	}

	static void Stopped(int TrainNo, String station, double timestamp,
			String Direction) throws IOException {
		// TODO Auto-generated method stub

		MainActivity.SimTime = timestamp;
		// Finding index of current station
		int k1 = 0;
		int countWalkIn = 0;

		for (k1 = 0; k1 < Station.StationList_Central.size(); k1++) {
			if (station.equals(Station.StationList_Central.get(k1)
					.getStationName()))
				break;

		}
		// checking all passenger which have this source station

		for (int i = 0; i < Passenger.TotalNumOfPassenger_Central; i++) {
			// Finding index of source station
			int k2 = 0;
			if(Passenger.ListOfPassenger_Central.get(i).TrainNo==TrainNo)
				Passenger.ListOfPassenger_Central.get(i).setCurrStation(station);

			for (k2 = 0; k2 < Station.StationList_Central.size(); k2++) {
				if (Passenger.ListOfPassenger_Central
						.get(i)
						.getDest()
						.equals(Station.StationList_Central.get(k2)
								.getStationName()))
					break;

			}
			// SANITY CHECK Trains.TrainMovingCounter_Central[TrainNo - 1]=k1;
		//	System.out.println("k1= "+k1+"k2= "+k2);
			if (Passenger.ListOfPassenger_Central.get(i).getSrc()
					.equals(station)&&Passenger.ListOfPassenger_Central.get(i).TrainNo==-1
					&& (k2 > k1) && Direction == "up") {
				// add that passenger to train list
				Passenger.ListOfPassenger_Central.get(i).setTrainNo(TrainNo);
				Passenger.ListOfPassenger_Central.get(i).setStatus("InTrain");
				Trains.Trainlist_Central.get(TrainNo - 1).Train
						.add(Passenger.ListOfPassenger_Central.get(i));
				countWalkIn++;
			} else if (Passenger.ListOfPassenger_Central.get(i).getSrc()
					.equals(station)&&Passenger.ListOfPassenger_Central.get(i).TrainNo==-1
					&& (k2 < k1) && Direction == "down") {
				// add that passenger to train list
				Passenger.ListOfPassenger_Central.get(i).setTrainNo(TrainNo);
				Passenger.ListOfPassenger_Central.get(i).setStatus("InTrain");
				Trains.Trainlist_Central.get(TrainNo - 1).Train
						.add(Passenger.ListOfPassenger_Central.get(i));
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
		for (int i = 0; i < Trains.Trainlist_Central.get(TrainNo - 1).Train
				.size();i++) {
					
			if (station.equals(Trains.Trainlist_Central.get(TrainNo - 1).Train
					.get(i).getDest())&&Trains.Trainlist_Central.get(TrainNo - 1).Train
					.get(i).TrainNo!=-1) {
			//	System.out.println("train num= "+Trains.Trainlist_Central.get(TrainNo - 1).Train
			//			.get(i).TrainNo);
				// System.out.println("Remove "+
				// Trains.Trainlist.get(TrainNo - 1).Train.get(i).id);
				Trains.Trainlist_Central.get(TrainNo - 1).Train
				.get(i).setTrainNo(-1);
				// Passenger.ListOfPassenger_Central.get(i).setStatus("OnStation");
				// Passenger.ListOfPassenger_Central.get(i).setSrc(station);
				// Assigning next source and destination.....
				Uniform uniSrc = null, uniDest = null;
				int Src, Dest;

				uniSrc = new Uniform(0, 25);
				Src = (int) uniSrc.nextDouble();
				Trains.Trainlist_Central.get(TrainNo - 1).Train
				.get(i).setSrc(Station.StationList_Central.get(Src).StationName);
				
				Trains.Trainlist_Central.get(TrainNo - 1).Train
				.get(i).setCurrStation(
						Station.StationList_Central.get(Src).StationName);

				Trains.Trainlist_Central.get(TrainNo - 1).Train
				.get(i).setArrTime(timestamp);
				Trains.Trainlist_Central.get(TrainNo - 1).Train
				.get(i).setStatus("OnStation");
				uniDest = new Uniform(0, 25);
				Dest = (int) uniDest.nextDouble();
				while (Src == Dest) {
					uniDest = new Uniform(0, 25);
					Dest = (int) uniDest.nextDouble();

				}
				Trains.Trainlist_Central.get(TrainNo - 1).Train
				.get(i).setDest(
						Station.StationList_Central.get(Dest).StationName);

				Trains.Trainlist_Central.get(TrainNo - 1).Train.remove(i);
				countWalkOut++;

			} 
		}

		System.out
				.println(Math.round(MainActivity.SimTime * 1000 / 1000)
						+ "\t"
						+ TrainNo
						+ "\t"
						+ "Stopped_Central"
						+ "\t"
						+ station
						+ "\t"
						+ Direction
						+ "\t"
						+ countWalkIn++
						+ "\t"
						+ countWalkOut
						+ "\t"
						+ Trains.Trainlist_Central.get(TrainNo - 1).Train
								.size());

		MainActivity.EventList.add(new Event(TrainNo, "Departure_Central",
				station, MainActivity.SimTime + Trains.Halt_time_of_Train,
				Direction));

	}

	static void Departure(int TrainNo, String station, double timestamp,
			String Direction) throws IOException {
		// TODO Auto-generated method stub

		MainActivity.SimTime = timestamp;

		System.out.println(Math.round(MainActivity.SimTime * 1000 / 1000)
				+ "\t" + TrainNo + "\t" + "Departure_Central" + "\t" + station
				+ "\t" + Direction);

		// System.out.println(Trains.TrainMovingCounter[TrainNo - 1]);
		if (Direction == "up") {
			if (Trains.TrainMovingCounter_Central[TrainNo - 1] == 25) {
				// System.out.println("Hello");
				Direction = "down";
				double nxtStationDis = Station.StationList_Central
						.get(Trains.TrainMovingCounter_Central[TrainNo - 1]).NextStationDistance;

				double nxtStationTime = nxtStationDis
						/ Trains.Speed_of_The_Train;

				MainActivity.EventList
						.add(new Event(
								TrainNo,
								"Arrival_Central",
								Station.StationList_Central
										.get(Trains.TrainMovingCounter_Central[TrainNo - 1] - 1).StationName,
								MainActivity.SimTime + nxtStationTime, "down"));

			} else {
				Trains.TrainMovingCounter_Central[TrainNo - 1]++;
				double nxtStationDis = Station.StationList_Central
						.get(Trains.TrainMovingCounter_Central[TrainNo - 1]).NextStationDistance;

				double nxtStationTime = nxtStationDis
						/ Trains.Speed_of_The_Train;

				MainActivity.EventList
						.add(new Event(
								TrainNo,
								"Arrival_Central",
								Station.StationList_Central
										.get(Trains.TrainMovingCounter_Central[TrainNo - 1]).StationName,
								MainActivity.SimTime + nxtStationTime, "up"));

			}

		}

		else if (Direction == "down") {
			if (Trains.TrainMovingCounter_Central[TrainNo - 1] == 1) {
				Direction = "up";
				// System.out.println(Station.StationList.get(Trains.
				// TrainMovingCounter[TrainNo - 1]).StationName);
				double nxtStationDis = Station.StationList_Central
						.get(Trains.TrainMovingCounter_Central[TrainNo - 1]).NextStationDistance;

				double nxtStationTime = nxtStationDis
						/ Trains.Speed_of_The_Train;

				MainActivity.EventList
						.add(new Event(
								TrainNo,
								"Arrival_Central",
								Station.StationList_Central
										.get(Trains.TrainMovingCounter_Central[TrainNo - 1]).StationName,
								MainActivity.SimTime + nxtStationTime, "up"));

			} else {
				Trains.TrainMovingCounter_Central[TrainNo - 1]--;
				double nxtStationDis = Station.StationList_Central
						.get(Trains.TrainMovingCounter_Central[TrainNo - 1]).NextStationDistance;

				double nxtStationTime = nxtStationDis
						/ Trains.Speed_of_The_Train;

				MainActivity.EventList
						.add(new Event(
								TrainNo,
								"Arrival_Central",
								Station.StationList_Central
										.get(Trains.TrainMovingCounter_Central[TrainNo - 1] - 1).StationName,
								MainActivity.SimTime + nxtStationTime, "down"));

			}
		}

	}

	public static void Initialization() {
		// scheduling first arrival

		for (int i = 0, j = 0; i < Trains.NumOfTrains_Central; j = j + 50) {
			MainActivity.EventList
					.add(new Event(
							i + 1,
							"Arrival_Central",
							Station.StationList_Central
									.get(Trains.TrainMovingCounter_Central[i]).StationName,
							0 + j, "up"));
			i++;
			if (Trains.NumOfTrains_Central == 1)
				break;
			Trains.TrainMovingCounter_Central[i] = 25;
			MainActivity.EventList
					.add(new Event(
							i + 1,
							"Arrival_Central",
							Station.StationList_Central
									.get(Trains.TrainMovingCounter_Central[i]).StationName,
							0 + j, "down"));
			i++;

		}
	}

	public static void AssignStationToPassenger() {
		// adding stations to passenger randomly
		// ...........
		for (int i = 0; i < Passenger.TotalNumOfPassenger_Central; i++) {

			Uniform uniSrc = null, uniDest = null;
			int Src, Dest;

			uniSrc = new Uniform(0, 25);
			Src = (int) uniSrc.nextDouble();

			Passenger.ListOfPassenger_Central.get(i).setSrc(
					Station.StationList_Central.get(Src).StationName);
			Passenger.ListOfPassenger_Central.get(i).setCurrStation(
					Station.StationList_Central.get(Src).StationName);

			Passenger.ListOfPassenger_Central.get(i).setArrTime(0);
			Passenger.ListOfPassenger_Central.get(i).setStatus("OnStation");
			uniDest = new Uniform(0, 25);
			Dest = (int) uniDest.nextDouble();
			while (Src == Dest) {
				uniDest = new Uniform(0, 25);
				Dest = (int) uniDest.nextDouble();

			}
			Passenger.ListOfPassenger_Central.get(i).setDest(
					Station.StationList_Central.get(Dest).StationName);

		}

	}

}