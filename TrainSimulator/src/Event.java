public class Event {
	public int TrainNo;
	public double TimeStamp;
	public String TypeOfEvent, StationName, Direction;// Type of event->arrival
														// or departure

	public Event(int TrainNo, String TypeOfEvent, String StationName,
			double TimeStamp, String Direction) {
		this.TrainNo = TrainNo;
		this.TypeOfEvent = TypeOfEvent;
		this.StationName = StationName;
		this.TimeStamp = TimeStamp;
		this.Direction = Direction;
	}

	public int getTrainNo() {
		return TrainNo;
	}

	public String getTypeOfEvent() {
		return TypeOfEvent;
	}

	public String getStationName() {
		return StationName;
	}

	public String getDirection() {
		return Direction;
	}

	public double getTimeStamp() {
		return TimeStamp;
	}
}