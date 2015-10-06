import java.io.IOException;
import java.util.ArrayList;

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
					&& (k2 > k1) && Direction == "up"&& timestamp>Passenger.ListOfPassenger_Harbour.get(i).nextJourneyTime) {
				// add that passenger to train list
				Passenger.ListOfPassenger_Harbour.get(i).setTrainNo(TrainNo);
				Passenger.ListOfPassenger_Harbour.get(i).setStatus("InTrain");
				Trains.Trainlist_Harbour.get(TrainNo - 1).Train
						.add(Passenger.ListOfPassenger_Harbour.get(i));
				//Checking if the passenger is faulty
				boolean faulty=false;
				for(int b=0;b<Passenger.faultyPassenger.length;b++){
					if(Passenger.faultyPassenger[b]==Passenger.ListOfPassenger_Harbour.get(i).id){
						faulty=true;
						break;
					}
				
				}
				// Passengers telling us that they are boarding train
				//faulty=true;
				
				if(faulty){
				Uniform inp = null, inperr = null;
				int val, valadded;

			//	inp = new Uniform(0, 2);
			//	val = (int) inp.nextDouble();
				val=0;
				inperr = new Uniform(0, 600);
				valadded = (int) inperr.nextDouble();
				// System.out.println("VALUE="+valadded);
				 if(val==0)
					{
			/*		 			 if(station=="CST"||station=="Masjid Bunder"||
								 station=="Sandhurst Road"||station=="Kurla")
							 Train_Spotting.Train_Spotting_List_Central
								.add(new Train_Spotting(
										Passenger.ListOfPassenger_Harbour.get(i).id,
										timestamp, "up", station, distFromOriginMeter,
										0, 0));
					
						 else  */
							 Train_Spotting.Train_Spotting_List_Harbour
								.add(new Train_Spotting(
										Passenger.ListOfPassenger_Harbour.get(i).id,
										timestamp, "up", station, distFromOriginMeter,
										0, 0));
						
					} 
					 
				 else 
 					 Train_Spotting.Train_Spotting_List_Harbour
						.add(new Train_Spotting(
								Passenger.ListOfPassenger_Harbour.get(i).id,
								timestamp, "up", station, distFromOriginMeter,
								0, 0));
				}
				else{
					Train_Spotting.Train_Spotting_List_Harbour
					.add(new Train_Spotting(
							Passenger.ListOfPassenger_Harbour.get(i).id,
							timestamp, "up", station, distFromOriginMeter,
							0, 0));
				}
				// ........................
				countWalkIn++;
			} else if (Passenger.ListOfPassenger_Harbour.get(i).getSrc()
					.equals(station)
					&& Passenger.ListOfPassenger_Harbour.get(i).TrainNo == -1
					&& (k2 < k1) && Direction == "down"&& timestamp>Passenger.ListOfPassenger_Harbour.get(i).nextJourneyTime) {
				// add that passenger to train list
				Passenger.ListOfPassenger_Harbour.get(i).setTrainNo(TrainNo);
				Passenger.ListOfPassenger_Harbour.get(i).setStatus("InTrain");
				Trains.Trainlist_Harbour.get(TrainNo - 1).Train
						.add(Passenger.ListOfPassenger_Harbour.get(i));

				// Including probability to give input by passengers
				
				boolean faulty=false;
				for(int b=0;b<Passenger.faultyPassenger.length;b++){
					if(Passenger.faultyPassenger[b]==Passenger.ListOfPassenger_Harbour.get(i).id){
						faulty=true;
						break;
					}
				
				}
				// Passengers telling us that they are boarding train
				//faulty=true;
				
				if(faulty){

				Uniform inp = null, inperr = null;
				int val, valadded;

			//	inp = new Uniform(0, 2);
			//	val = (int) inp.nextDouble();
				val=0;
				inperr = new Uniform(0, 600);
				valadded = (int) inperr.nextDouble();
				// System.out.println("VALUE="+valadded);
				 if(val==0)
					{
			/*			 if(station=="CST"||station=="Masjid Bunder"||
								 station=="Sandhurst Road"||station=="Kurla")
							 Train_Spotting.Train_Spotting_List_Central
								.add(new Train_Spotting(
										Passenger.ListOfPassenger_Harbour.get(i).id,
										timestamp, "down", station, distFromOriginMeter,
										0, 0));
					
						 else */
							 Train_Spotting.Train_Spotting_List_Harbour
								.add(new Train_Spotting(
										Passenger.ListOfPassenger_Harbour.get(i).id,
										timestamp, "down", station, distFromOriginMeter,
										0, 0));
						
					} 
			
				else
				 Train_Spotting.Train_Spotting_List_Harbour
				 .add(new Train_Spotting(
				 Passenger.ListOfPassenger_Harbour.get(i).id,
				 timestamp, "down", station,
				 distFromOriginMeter, 0, 0));
				}
				else{
					 Train_Spotting.Train_Spotting_List_Harbour
					 .add(new Train_Spotting(
					 Passenger.ListOfPassenger_Harbour.get(i).id,
					 timestamp, "down", station,
					 distFromOriginMeter, 0, 0));
						
				}
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
//setting next journey time
				Trains.Trainlist_Harbour.get(TrainNo - 1).Train.get(i).
				setNextJourneyTime(timestamp+43200);
				
				Trains.Trainlist_Harbour.get(TrainNo - 1).Train.remove(i);
				countWalkOut++;

			}
		}

		System.out.println(Math.round(MainActivity.SimTime * 1000 / 1000)
				+ "\t" + TrainNo + "\t" + "Stopped_Harbour" + "\t" + station
				+ "\t" + distFromOriginMeter + "\t" + Direction + "\t"
				+ countWalkIn++ + "\t" + countWalkOut + "\t"
				+ Trains.Trainlist_Harbour.get(TrainNo - 1).Train.size());
		// Inserting Random Delays
		Uniform inp = null;
		int val;

		inp = new Uniform(0, 3600);
		val = (int) inp.nextDouble();

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

		for (int i = 0, j = 0; i < Trains.NumOfTrains_Harbour; j = j + 1800) {
			MainActivity.EventList
					.add(new Event(
							i + 1,
							"Arrival_Harbour",
							Station.StationList_Harbour
									.get(Trains.TrainMovingCounter_Harbour[i]).StationName,
							0 + j, "up"));
			i++;
			if (Trains.NumOfTrains_Harbour == i)
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
		double diffSec = nowtime - t;
		// System.out.println("difftime=" + diffSec);
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
		/*
		 * System.out.println("New PRINT"); for (int jj = 0; jj <
		 * Train_Spotting.Train_Spotting_List_Harbour .size(); jj++) {
		 * //if(Train_Spotting
		 * .Train_Spotting_List_Harbour.get(j).Direction=="down"){
		 * 
		 * System.out.print(Train_Spotting.Train_Spotting_List_Harbour.get(jj).
		 * DistFromOriginMeter);
		 * System.out.println(Train_Spotting.Train_Spotting_List_Harbour
		 * .get(jj).Direction);
		 * 
		 * //} }
		 * 
		 * System.out.println("New PRINT END");
		 */
		ArrayList<Train_Spotting> Train_Spotting_List_Harbour_t = new ArrayList<Train_Spotting>(
				10000);
		
		
		for(Train_Spotting p : Train_Spotting.Train_Spotting_List_Harbour) {
			Train_Spotting_List_Harbour_t.add(p.clone(p.Id,p.Timestamp,p.DistFromOriginMeter,p.Confidence,p.DistNow,p.Direction,p.Station));
		}
		
			for (int i = 0; i < Train_Spotting.Train_Spotting_List_Harbour.size(); i++) {

			double t = Train_Spotting.Train_Spotting_List_Harbour.get(i).Timestamp;

			Train_Spotting.Train_Spotting_List_Harbour.get(i).setConfidence(
					getConfidenceFromPast(t, nowtime));

			// calculating number of stations between nowTime and t
			String station = Train_Spotting.Train_Spotting_List_Harbour.get(i).Station;
			int k1 = 0;
			for (k1 = 0; k1 < Station.StationList_Harbour.size(); k1++) {
				if (station.equals(Station.StationList_Harbour.get(k1)
						.getStationName()))
					break;

			}
			String dir = Train_Spotting.Train_Spotting_List_Harbour.get(i).Direction;
			double timeBetweenStations;
			double distOffset = 0;
			double timetoTravel = nowtime - t;
			// System.out.println("timetoTravel="+timetoTravel);
			int m = 0;
		//	int dir_flag = 0;
		
			while (true) {
				if (dir == "up") {
					for (m = k1 + 1; m <= 24; m++) {
						double dist = Station.StationList_Harbour.get(m).NextStationDistance;
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
					if (m > 24) {
					//	dir_flag++;
						dir = "down";
						k1 = m - 1;
					} else {
						break;
					}
				}
				
				//System.out.println("k="+k1);
				if (dir == "down") {
					for (m = k1; m >= 0; m--) {
						double dist = Station.StationList_Harbour.get(m).NextStationDistance;
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
			// int dir_flag=(int)total_run_dist/49000;
		//	 System.out.println("m="+m);
			int ii = 0;
			double distNow = 0;
			for (ii = 0; ii < Station.StationList_Harbour.size(); ii++) {
				distNow = distNow
						+ Station.StationList_Harbour.get(ii)
								.getNextStationDistance();
				if (m == ii)
					break;
			}
			//
			distNow += distOffset;
			
			/*
			for(int kk=0;kk<Station.StationList_Harbour.size();kk++){
				System.out.print(Station.StationList_Harbour.get(kk).getNextStationDistance());
			}
			System.out.println();
			*/
			//System.out.println("dist="+distNow+" "+"dir="+dir);
			Train_Spotting.Train_Spotting_List_Harbour.get(i).setDistNow(
					distNow);
			// if(dir_flag%2==1){
			// if(Train_Spotting.Train_Spotting_List_Harbour.get(i).Direction=="up"){

			Train_Spotting.Train_Spotting_List_Harbour.get(i).setDirection(dir);

			// }
			// else{
			// Train_Spotting.Train_Spotting_List_Harbour.get(i).setDirection("up");

			// }
			// }
		}		
		
		

		// computePosnConf..................
		
		PosnConf.PosnConfidnce_List_Harbour_Up.clear();
		PosnConf.PosnConfidnce_List_Harbour_Down.clear();
		
		double dist = 0;
		double inrc = 100;
		double Posnconf_Up, Posnconf_Down;
		int NumUserInputs_Up, NumUserInputs_Down;
		while (dist < 49000) {

			Posnconf_Up = 0;
			Posnconf_Down = 0;
			NumUserInputs_Up = 0;
			NumUserInputs_Down = 0;
			for (int j = 0; j < Train_Spotting.Train_Spotting_List_Harbour
					.size(); j++) {
				double distEach = Train_Spotting.Train_Spotting_List_Harbour
						.get(j).DistNow;

				if (Math.abs(dist - distEach) < 2000) {
					if (Train_Spotting.Train_Spotting_List_Harbour.get(j)
							.getDirection().equals("up")) {
						double confDist = getConfidenceFromFarSpotting(Math
								.abs(dist - distEach));
						double overallConf = Train_Spotting.Train_Spotting_List_Harbour
								.get(j).Confidence
								* confDist
								* Passenger.reputation[Train_Spotting.Train_Spotting_List_Harbour
									.get(j).Id];
						Posnconf_Up += (1 - Posnconf_Up) * overallConf;
						NumUserInputs_Up++;
					} else if (Train_Spotting.Train_Spotting_List_Harbour
							.get(j).getDirection().equals("down")) {
						double confDist = getConfidenceFromFarSpotting(Math
								.abs(dist - distEach));
						double overallConf = Train_Spotting.Train_Spotting_List_Harbour
								.get(j).Confidence
								* confDist
								* Passenger.reputation[Train_Spotting.Train_Spotting_List_Harbour
										.get(j).Id];
						Posnconf_Down += (1 - Posnconf_Down) * overallConf;
						NumUserInputs_Down++;

					}
				}

			}
			if (Posnconf_Up != 0) {

				PosnConf.PosnConfidnce_List_Harbour_Up.add(new PosnConf(dist,
						Posnconf_Up, NumUserInputs_Up, true));
			}
			if (Posnconf_Down != 0) {

				PosnConf.PosnConfidnce_List_Harbour_Down.add(new PosnConf(dist,
						Posnconf_Down, NumUserInputs_Down, true));
			}
			dist += inrc;
		}

		for (int i = 0; i < PosnConf.PosnConfidnce_List_Harbour_Up.size(); i++) {

			double confAdj4NumUsers = getConfidence4NumUsers(PosnConf.PosnConfidnce_List_Harbour_Up
					.get(i).NumUserInputs);
			double posnconf = PosnConf.PosnConfidnce_List_Harbour_Up.get(i).PosnConfidence;
			posnconf *= confAdj4NumUsers;
			PosnConf.PosnConfidnce_List_Harbour_Up.get(i).setPosnConfidence(
					posnconf);

		}
		for (int i = 0; i < PosnConf.PosnConfidnce_List_Harbour_Down.size(); i++) {

			double confAdj4NumUsers = getConfidence4NumUsers(PosnConf.PosnConfidnce_List_Harbour_Down
					.get(i).NumUserInputs);
			double posnconf = PosnConf.PosnConfidnce_List_Harbour_Down.get(i).PosnConfidence;
			posnconf *= confAdj4NumUsers;
			PosnConf.PosnConfidnce_List_Harbour_Down.get(i).setPosnConfidence(
					posnconf);

		}
		
		// computeConfidencePeaks.................
		int jStart, jEnd;
		for (int i = 0; i < PosnConf.PosnConfidnce_List_Harbour_Up.size(); i++) {
			if (i < PosnConf.peakThres)
				jStart = 0;
			else
				jStart = i - PosnConf.peakThres;
			if (i + PosnConf.peakThres >= PosnConf.PosnConfidnce_List_Harbour_Up
					.size())
				jEnd = PosnConf.PosnConfidnce_List_Harbour_Up.size() - 1;
			else
				jEnd = i + PosnConf.peakThres;

			for (int j = jStart; j <= jEnd; j++) {
				if (Double
						.compare(
								PosnConf.PosnConfidnce_List_Harbour_Up.get(i).PosnConfidence,
								PosnConf.PosnConfidnce_List_Harbour_Up.get(j).PosnConfidence) < 0) {
					PosnConf.PosnConfidnce_List_Harbour_Up.get(i)
							.setPeak(false);
					break;
				}
			}
		}
		for (int i = 0; i < PosnConf.PosnConfidnce_List_Harbour_Down.size(); i++) {
			if (i < PosnConf.peakThres)
				jStart = 0;
			else
				jStart = i - PosnConf.peakThres;
			if (i + PosnConf.peakThres >= PosnConf.PosnConfidnce_List_Harbour_Down
					.size())
				jEnd = PosnConf.PosnConfidnce_List_Harbour_Down.size() - 1;
			else
				jEnd = i + PosnConf.peakThres;

			for (int j = jStart; j <= jEnd; j++) {
				if (Double
						.compare(
								PosnConf.PosnConfidnce_List_Harbour_Down.get(i).PosnConfidence,
								PosnConf.PosnConfidnce_List_Harbour_Down.get(j).PosnConfidence) < 0) {
					PosnConf.PosnConfidnce_List_Harbour_Down.get(i).setPeak(
							false);
					break;
				}
			}
		}
		// updating users reputation
		for (int i = 0; i < Train_Spotting.Train_Spotting_List_Harbour.size(); i++) {
			double max_confidence = 0;
			for (int j = 0; j < PosnConf.PosnConfidnce_List_Harbour_Up.size(); j++) {
				if (PosnConf.PosnConfidnce_List_Harbour_Up.get(j).isPeak()) {
					double pos_index = PosnConf.PosnConfidnce_List_Harbour_Up
							.get(j).DistFromOriginMeter;
					double distAlongRoute = Train_Spotting.Train_Spotting_List_Harbour
							.get(i).DistFromOriginMeter;
					double confDist = getConfidenceFromFarSpotting(Math
							.abs(distAlongRoute - pos_index));
					double confidence = Train_Spotting.Train_Spotting_List_Harbour
							.get(i).Confidence;
					double PosnCnf = PosnConf.PosnConfidnce_List_Harbour_Up
							.get(j).PosnConfidence;
					double this_confidence = confidence * confDist * PosnCnf;
					if (this_confidence > max_confidence) {
						max_confidence = this_confidence;
					}
					int userId = Train_Spotting.Train_Spotting_List_Harbour
							.get(i).Id;
					Passenger.reputation[userId] += Passenger.REPUTATION_INCR_VALUE
							* max_confidence;
					if (Passenger.reputation[userId] > Passenger.MAX_REPUTATION) {
						Passenger.reputation[userId] = Passenger.MAX_REPUTATION;
					}
				}

			}
			for (int j = 0; j < PosnConf.PosnConfidnce_List_Harbour_Down.size(); j++) {
				if (PosnConf.PosnConfidnce_List_Harbour_Down.get(j).isPeak()) {
					double pos_index = PosnConf.PosnConfidnce_List_Harbour_Down
							.get(j).DistFromOriginMeter;
					double distAlongRoute = Train_Spotting.Train_Spotting_List_Harbour
							.get(i).DistFromOriginMeter;
					double confDist = getConfidenceFromFarSpotting(Math
							.abs(distAlongRoute - pos_index));
					double confidence = Train_Spotting.Train_Spotting_List_Harbour
							.get(i).Confidence;
					double PosnCnf = PosnConf.PosnConfidnce_List_Harbour_Down
							.get(j).PosnConfidence;
					double this_confidence = confidence * confDist * PosnCnf;
					if (this_confidence > max_confidence) {
						max_confidence = this_confidence;
					}
					int userId = Train_Spotting.Train_Spotting_List_Harbour
							.get(i).Id;
					Passenger.reputation[userId] += Passenger.REPUTATION_INCR_VALUE
							* max_confidence;
					if (Passenger.reputation[userId] > Passenger.MAX_REPUTATION) {
						Passenger.reputation[userId] = Passenger.MAX_REPUTATION;
					}
				}

			}

		}
		Train_Spotting.Train_Spotting_List_Harbour.clear();
		for(Train_Spotting p : Train_Spotting_List_Harbour_t) {
			Train_Spotting.Train_Spotting_List_Harbour.add(p.clone(p.Id,p.Timestamp,p.DistFromOriginMeter,p.Confidence,p.DistNow,p.Direction,p.Station));
		}
	}

	static double getConfidence4NumUsers(int N) {
		return (1 - 1.0 / Math.pow(2, N - 1)); // this gives 0 confidence for 1
												// user, tending toward 1 for
												// large N
	} // End getConfidence4NumUsers()

}
