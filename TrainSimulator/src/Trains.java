import java.util.ArrayList;
import java.util.LinkedList;

public class Trains {
	static int NumOfTrains_Western = 6, NumOfTrains_Central = 6,
			NumOfTrains_Harbour = 6;
	static double Halt_time_of_Train = 20;// seconds
	static double Speed_of_The_Train = 14;// meter per second
	static int[] TrainMovingCounter_Western = new int[NumOfTrains_Western];
	static int[] TrainMovingCounter_Central = new int[NumOfTrains_Central];
	static int[] TrainMovingCounter_Harbour = new int[NumOfTrains_Harbour];

	static ArrayList<Trains> Trainlist_Western = new ArrayList<Trains>(
			NumOfTrains_Western);
	static ArrayList<Trains> Trainlist_Central = new ArrayList<Trains>(
			NumOfTrains_Central);
	static ArrayList<Trains> Trainlist_Harbour = new ArrayList<Trains>(
			NumOfTrains_Harbour);
	// list
	// for
	// containing
	// objects
	// of
	// trains
	public LinkedList<Passenger> Train = new LinkedList<Passenger>();// list for
																		// each
	// train object

}