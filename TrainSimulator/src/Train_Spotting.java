import java.util.ArrayList;

public class Train_Spotting {
	public int Id;
	public double Timestamp;
	public String Direction, Station;

	public Train_Spotting(int id, double timestamp, String direction,
			String station) {
		super();
		Id = id;
		Timestamp = timestamp;
		Direction = direction;
		Station = station;
	}

	static ArrayList<Train_Spotting> Train_Spotting_List_Western = new ArrayList<Train_Spotting>(
			2000);
	static ArrayList<Train_Spotting> Train_Spotting_List_Central = new ArrayList<Train_Spotting>(
			2000);
	static ArrayList<Train_Spotting> Train_Spotting_List_Harbour = new ArrayList<Train_Spotting>(
			2000);

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public double getTimestamp() {
		return Timestamp;
	}

	public void setTimestamp(double timestamp) {
		Timestamp = timestamp;
	}

	public String getDirection() {
		return Direction;
	}

	public void setDirection(String direction) {
		Direction = direction;
	}

	public String getStation() {
		return Station;
	}

	public void setStation(String station) {
		Station = station;
	}

}
