import java.util.ArrayList;

public class Station {
	static int NumofStations_Western = 100, NumofStations_Central = 100,
			NumofStations_Harbour = 100;
	static ArrayList<Station> StationList_Western = new ArrayList<Station>(
			NumofStations_Western);
	static ArrayList<Station> StationList_Central = new ArrayList<Station>(
			NumofStations_Central);
	static ArrayList<Station> StationList_Harbour = new ArrayList<Station>(
			NumofStations_Harbour);

	String StationName;
	double NextStationDistance;

	public Station(String StationName, double NextStationDistance) {
		this.StationName = StationName;
		this.NextStationDistance = NextStationDistance;
	}

	public String getStationName() {
		return StationName;
	}

	public double getNextStationDistance() {
		return NextStationDistance;
	}

}
