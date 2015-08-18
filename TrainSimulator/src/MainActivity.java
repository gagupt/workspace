import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.io.FileReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class MainActivity {
	static BufferedWriter bufferedwrtr, bufferedwrtrpassengerInfo,
			bufferedwrtrpassengerInfoC, bufferedwrtrpassengerInfoH;
	public static double SimTime = 0;
	public static int end_sim_time = 5000, getSpottingsNowTime = 2000;// to stop
																		// the
																		// simulation

	public static Comparator<Event> Comparator = new Comparator<Event>() {

		@Override
		public int compare(Event c1, Event c2) {
			if (c1.TimeStamp < c2.TimeStamp)
				return -1;
			else if (c1.TimeStamp > c2.TimeStamp)
				return 1;
			return 0;
		}
	};

	// priority queue for simulation
	public static Queue<Event> EventList = new PriorityQueue<>(3000, Comparator);

	// Main function
	public static void main(String args[]) throws IOException {

		try {

			File file1 = new File("western.csv");
			if (!file1.exists()) {
				System.out.println("Input file not exist");
				System.exit(0);
			}
			FileReader fileReader1 = new FileReader(file1);
			BufferedReader br1 = new BufferedReader(fileReader1);

			String line1;
			while ((line1 = br1.readLine()) != null) {

				String[] entries1 = line1.split(",");

				Station st1 = new Station(entries1[0],
						Double.parseDouble(entries1[1]));

				Station.StationList_Western.add(st1);
			}
			File file2 = new File("central.csv");
			if (!file2.exists()) {
				System.out.println("Input file not exist");
				System.exit(0);
			}
			FileReader fileReader2 = new FileReader(file2);
			BufferedReader br2 = new BufferedReader(fileReader2);

			String line2;
			while ((line2 = br2.readLine()) != null) {

				String[] entries2 = line2.split(",");

				Station st2 = new Station(entries2[0],
						Double.parseDouble(entries2[1]));

				Station.StationList_Central.add(st2);
			}
			File file3 = new File("harbour.csv");
			if (!file3.exists()) {
				System.out.println("Input file not exist");
				System.exit(0);
			}
			FileReader fileReader3 = new FileReader(file3);
			BufferedReader br3 = new BufferedReader(fileReader3);

			String line3;
			while ((line3 = br3.readLine()) != null) {

				String[] entries3 = line3.split(",");

				Station st3 = new Station(entries3[0],
						Double.parseDouble(entries3[1]));

				Station.StationList_Harbour.add(st3);
			}

			for (int i = 0; i < Trains.NumOfTrains_Western; i++) {
				Trains t = new Trains();
				Trains.Trainlist_Western.add(t);
			}
			for (int i = 0; i < Trains.NumOfTrains_Central; i++) {
				Trains t = new Trains();
				Trains.Trainlist_Central.add(t);
			}
			for (int i = 0; i < Trains.NumOfTrains_Harbour; i++) {
				Trains t = new Trains();
				Trains.Trainlist_Harbour.add(t);
			}
			for (int i = 0; i < Passenger.TotalNumOfPassenger_Western; i++) {
				Passenger p = new Passenger(Passenger.passengerId, -1, -1,
						null, null, null, "outofStation");
				Passenger.ListOfPassenger_Western.add(p);
				Passenger.passengerId++;
			}
			for (int i = 0; i < Passenger.TotalNumOfPassenger_Central; i++) {
				Passenger p = new Passenger(Passenger.passengerId, -1, -1,
						null, null, null, "outofStation");
				Passenger.ListOfPassenger_Central.add(p);
				Passenger.passengerId++;
			}
			for (int i = 0; i < Passenger.TotalNumOfPassenger_Harbour; i++) {
				Passenger p = new Passenger(Passenger.passengerId, -1, -1,
						null, null, null, "outofStation");
				Passenger.ListOfPassenger_Harbour.add(p);
				Passenger.passengerId++;
			}

			SimTime = 0;

			// creating trains object and adding its object to the train
			// list

			// initialization

			Event_handler_Western.Initialization();
			Event_handler_Central.Initialization();
			Event_handler_Harbour.Initialization();
			Event_handler_Western.AssignStationToPassenger();
			Event_handler_Central.AssignStationToPassenger();
			Event_handler_Harbour.AssignStationToPassenger();

			System.out.println("		MUMBAI RAIL");
			System.out.println("");
			System.out.println("SimTime\t" + "TrainNo\t" + "TypeOfEvent\t"
					+ "Station_Name	" + " Direction" + " 	NoOfPassengerWalkIn"
					+ " 	NoOfPassengerWalkOut" + " 	ToatlNoOfPassenger");
			System.out.println("");
			File outPassenger = new File("PassengerInfo.csv");
			if (!outPassenger.exists()) {
				outPassenger.createNewFile();
			}

			FileWriter wrtr = new FileWriter(outPassenger);
			bufferedwrtr = new BufferedWriter(wrtr);
			int flag = 0;
			while (SimTime < end_sim_time) {

				// getSpottingsNow method call
				if (SimTime >= getSpottingsNowTime && flag == 0) {
					Event_handler_Western.getSpottingsNow(SimTime);
					Event_handler_Central.getSpottingsNow(SimTime);
					Event_handler_Harbour.getSpottingsNow(SimTime);
					flag = 1;
				}
				// ....................

				// printing passengers info into csv file

				// ..............................
				// extracting first event from priority queue
				Event e = EventList.poll();
				if (e == null) {
					System.out.print("\nEvent list empty at time " + SimTime);
					break;
				}
				// bufferedwrtr.write("TRAINSIZE="+","+size);
				// bufferedwrtr.newLine();
				int i;
				// bufferedwrtr.write("passenger info Start");
				bufferedwrtr.newLine();
				for (i = 0; i < Passenger.ListOfPassenger_Western.size(); i++) {

					bufferedwrtr
							.write(e.TimeStamp
									+ "\t"
									// +
									// Passenger.ListOfPassenger_Western.get(i).ArrTime+"\t"
									+ Passenger.ListOfPassenger_Western.get(i).id
									+ "\t"
									+ Passenger.ListOfPassenger_Western.get(i).TrainNo
									+ "\t"
									+ Passenger.ListOfPassenger_Western.get(i).Src
									+ "\t"
									+ Passenger.ListOfPassenger_Western.get(i)
											.getDest()
									+ "\t"
									+ Passenger.ListOfPassenger_Western.get(i).Status
									+ "\t"
									+ Passenger.ListOfPassenger_Western.get(i).CurrStation

							);

					bufferedwrtr.newLine();

				}
				bufferedwrtr.newLine();
				for (i = 0; i < Passenger.ListOfPassenger_Central.size(); i++) {

					bufferedwrtr
							.write(e.TimeStamp
									+ "\t"
									// +
									// Passenger.ListOfPassenger_Central.get(i).ArrTime+"\t"
									+ Passenger.ListOfPassenger_Central.get(i).id
									+ "\t"
									+ Passenger.ListOfPassenger_Central.get(i).TrainNo
									+ "\t"
									+ Passenger.ListOfPassenger_Central.get(i).Src
									+ "\t"
									+ Passenger.ListOfPassenger_Central.get(i).Dest
									+ "\t"
									+ Passenger.ListOfPassenger_Western.get(i).Status
									+ "\t"
									+ Passenger.ListOfPassenger_Western.get(i).CurrStation);

					bufferedwrtr.newLine();
				}
				bufferedwrtr.newLine();
				for (i = 0; i < Passenger.ListOfPassenger_Harbour.size(); i++) {

					bufferedwrtr
							.write(e.TimeStamp
									+ "\t"
									// +
									// Passenger.ListOfPassenger_Harbour.get(i).ArrTime+"\t"
									+ Passenger.ListOfPassenger_Harbour.get(i).id
									+ "\t"
									+ Passenger.ListOfPassenger_Harbour.get(i).TrainNo
									+ "\t"
									+ Passenger.ListOfPassenger_Harbour.get(i).Src
									+ "\t"
									+ Passenger.ListOfPassenger_Harbour.get(i).Dest
									+ "\t"
									+ Passenger.ListOfPassenger_Western.get(i).Status
									+ "\t"
									+ Passenger.ListOfPassenger_Western.get(i).CurrStation

							);

					bufferedwrtr.newLine();

				}

				bufferedwrtr.newLine();

				bufferedwrtr.newLine();

				switch (e.TypeOfEvent) {
				case "Arrival_Western":
					Event_handler_Western.Arrival(e.TrainNo, e.StationName,
							e.TimeStamp, e.Direction);

					System.out.println("");
					break;
				case "Stopped_Western":
					Event_handler_Western.Stopped(e.TrainNo, e.StationName,
							e.TimeStamp, e.Direction);

					System.out.println("");
					break;
				case "Departure_Western":
					Event_handler_Western.Departure(e.TrainNo, e.StationName,
							e.TimeStamp, e.Direction);
					System.out.println("");

					break;
				case "Arrival_Central":
					Event_handler_Central.Arrival(e.TrainNo, e.StationName,
							e.TimeStamp, e.Direction);

					System.out.println("");
					break;
				case "Stopped_Central":
					Event_handler_Central.Stopped(e.TrainNo, e.StationName,
							e.TimeStamp, e.Direction);

					System.out.println("");
					break;
				case "Departure_Central":
					Event_handler_Central.Departure(e.TrainNo, e.StationName,
							e.TimeStamp, e.Direction);
					System.out.println("");

					break;
				case "Arrival_Harbour":
					Event_handler_Harbour.Arrival(e.TrainNo, e.StationName,
							e.TimeStamp, e.Direction);

					System.out.println("");
					break;
				case "Stopped_Harbour":
					Event_handler_Harbour.Stopped(e.TrainNo, e.StationName,
							e.TimeStamp, e.Direction);

					System.out.println("");
					break;
				case "Departure_Harbour":
					Event_handler_Harbour.Departure(e.TrainNo, e.StationName,
							e.TimeStamp, e.Direction);
					System.out.println("");

					break;

				}
			}
			File passengerInfo = new File("Train_Spotting_western.csv");
			if (!passengerInfo.exists()) {
				passengerInfo.createNewFile();
			}

			FileWriter wrtrpassengerInfo = new FileWriter(passengerInfo);
			bufferedwrtrpassengerInfo = new BufferedWriter(wrtrpassengerInfo);
			int j;
			for (j = 0; j < Train_Spotting.Train_Spotting_List_Western.size(); j++) {

				bufferedwrtrpassengerInfo
						.write(+Train_Spotting.Train_Spotting_List_Western
								.get(j).Id
								+ "\t"
								+ Train_Spotting.Train_Spotting_List_Western
										.get(j).Timestamp
								+ "\t"
								+ Train_Spotting.Train_Spotting_List_Western
										.get(j).Station
								+ "\t"
								+ Train_Spotting.Train_Spotting_List_Western
										.get(j).DistFromOriginMeter
								+ "\t"
								+ Train_Spotting.Train_Spotting_List_Western
										.get(j).Direction
								+ "\t"
								+ Train_Spotting.Train_Spotting_List_Western
										.get(j).Confidence
								+ "\t"
								+ Train_Spotting.Train_Spotting_List_Western
										.get(j).DistNow
								+ "\t"
								+ Train_Spotting.Train_Spotting_List_Western
										.get(j).PosnConf
								+ "\t"
								+ Train_Spotting.Train_Spotting_List_Western
										.get(j).NumUserInputs
								+ "\t"
								+ Train_Spotting.Train_Spotting_List_Western
										.get(j).isPeak + "\t"

						);

				bufferedwrtrpassengerInfo.newLine();

			}
			File passengerInfoC = new File("Train_Spotting_central.csv");
			if (!passengerInfoC.exists()) {
				passengerInfoC.createNewFile();
			}

			FileWriter wrtrpassengerInfoC = new FileWriter(passengerInfoC);
			bufferedwrtrpassengerInfoC = new BufferedWriter(wrtrpassengerInfoC);

			for (j = 0; j < Train_Spotting.Train_Spotting_List_Central.size(); j++) {

				bufferedwrtrpassengerInfoC
						.write(+Train_Spotting.Train_Spotting_List_Central
								.get(j).Id
								+ "\t"
								+ Train_Spotting.Train_Spotting_List_Central
										.get(j).Timestamp
								+ "\t"
								+ Train_Spotting.Train_Spotting_List_Central
										.get(j).Station
								+ "\t"
								+ Train_Spotting.Train_Spotting_List_Central
										.get(j).DistFromOriginMeter
								+ "\t"
								+ Train_Spotting.Train_Spotting_List_Central
										.get(j).Direction
								+ "\t"
								+ Train_Spotting.Train_Spotting_List_Central
										.get(j).Confidence
								+ "\t"
								+ Train_Spotting.Train_Spotting_List_Central
										.get(j).DistNow
								+ "\t"
								+ Train_Spotting.Train_Spotting_List_Central
										.get(j).PosnConf
								+ "\t"
								+ Train_Spotting.Train_Spotting_List_Central
										.get(j).NumUserInputs
								+ "\t"
								+ Train_Spotting.Train_Spotting_List_Central
										.get(j).isPeak + "\t"

						);

				bufferedwrtrpassengerInfoC.newLine();

			}
			File passengerInfoH = new File("Train_Spotting_harbour.csv");
			if (!passengerInfoH.exists()) {
				passengerInfoH.createNewFile();
			}

			FileWriter wrtrpassengerInfoH = new FileWriter(passengerInfoH);
			bufferedwrtrpassengerInfoH = new BufferedWriter(wrtrpassengerInfoH);

			for (j = 0; j < Train_Spotting.Train_Spotting_List_Harbour.size(); j++) {

				bufferedwrtrpassengerInfoH
						.write(+Train_Spotting.Train_Spotting_List_Harbour
								.get(j).Id
								+ "\t"
								+ Train_Spotting.Train_Spotting_List_Harbour
										.get(j).Timestamp
								+ "\t"
								+ Train_Spotting.Train_Spotting_List_Harbour
										.get(j).Station
								+ "\t"
								+ Train_Spotting.Train_Spotting_List_Harbour
										.get(j).DistFromOriginMeter
								+ "\t"
								+ Train_Spotting.Train_Spotting_List_Harbour
										.get(j).Direction
								+ "\t"
								+ Train_Spotting.Train_Spotting_List_Harbour
										.get(j).Confidence
								+ "\t"
								+ Train_Spotting.Train_Spotting_List_Harbour
										.get(j).DistNow
								+ "\t"
								+ Train_Spotting.Train_Spotting_List_Harbour
										.get(j).PosnConf
								+ "\t"
								+ Train_Spotting.Train_Spotting_List_Harbour
										.get(j).NumUserInputs
								+ "\t"
								+ Train_Spotting.Train_Spotting_List_Harbour
										.get(j).isPeak + "\t"

						);

				bufferedwrtrpassengerInfoH.newLine();

			}

			bufferedwrtrpassengerInfo.close();
			bufferedwrtrpassengerInfoC.close();
			bufferedwrtrpassengerInfoH.close();
			bufferedwrtr.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}